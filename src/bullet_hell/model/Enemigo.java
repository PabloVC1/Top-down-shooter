package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.Punto;
import tads.IList;
import tads.LinkedList;

import java.awt.Color;
import java.util.Random;

/**
 * Un enemigo que persigue al jugador o dispara si está lejos.
 */
public class Enemigo extends ObjetoGraficoMovil {
    private static final double VELOCIDAD = 0.3;
    private static final double RADIO = 5;
    private static final long COOLDOWN_ATAQUE = 1000;
    private static final double DISTANCIA_MINIMA = 12;
    private static final double FUERZA_SEPARACION = 0.8;

    private static final double RANGO_DISPARO = 30;
    private static final long COOLDOWN_DISPARO = 3000;

    private Jugador jugador;
    private int daño;
    private long ultimoAtaqueTime = 0;
    private long ultimoDisparoTime = 0;
    private int vida = 1;    
    private IList<ObjetoGraficoMovil> proyectiles = new LinkedList<>(); // Lista de proyectiles generados por este enemigo

    public Enemigo(Jugador jugador, int daño) {
        super(new Circulo(Color.RED, generarPosicionAleatoria(), RADIO), 1, 0, 0);
        setImage("src\\resources\\vecteezy_an-8-bit-retro-styled-pixel-art-illustration-of-a-goblin_19017599.png");
        this.jugador = jugador;
        this.daño = daño;
      
    }

    /**
     * Avanza el enemigo: si está lejos dispara, si está cerca se mueve y aplica separación.
     */
    public void avanzar(IList<Enemigo> enemigos) {
        Punto miCentro = getFigura().getCentroide();
        Punto centroJugador = jugador.getFigura().getCentroide();
        double dx = centroJugador.getX() - miCentro.getX();
        double dy = centroJugador.getY() - miCentro.getY();
        double distanciaJugador = Math.sqrt(dx * dx + dy * dy);

        if (distanciaJugador > RANGO_DISPARO) {
            // Enemigo lejos: se queda quieto y dispara
            setIncX(0);
            setIncY(0);

            long tiempoActual = System.currentTimeMillis();
            if (tiempoActual - ultimoDisparoTime >= COOLDOWN_DISPARO) {
                disparar(miCentro, centroJugador);
                ultimoDisparoTime = tiempoActual;
            }
        } else {
            // Enemigo cerca: se mueve hacia el jugador y se separa de otros
            double dirX = dx;
            double dirY = dy;

            for (int i = 0; i < enemigos.size(); i++) {
                Enemigo otro = enemigos.get(i);
                if (otro == this || otro.estaMuerto()) continue;

                Punto otroCentro = otro.getFigura().getCentroide();
                double distX = miCentro.getX() - otroCentro.getX();
                double distY = miCentro.getY() - otroCentro.getY();
                double dist = Math.sqrt(distX * distX + distY * distY);

                if (dist < DISTANCIA_MINIMA && dist > 0.01) {
                    double fuerza = FUERZA_SEPARACION / (dist * dist);
                    dirX += (distX / dist) * fuerza;
                    dirY += (distY / dist) * fuerza;
                }
            }

            double magnitud = Math.sqrt(dirX * dirX + dirY * dirY);
            if (magnitud > 0) {
                setIncX(VELOCIDAD * dirX / magnitud);
                setIncY(VELOCIDAD * dirY / magnitud);
            } else {
                setIncX(0);
                setIncY(0);
            }
        }

        // Avanza y mueve proyectiles
        super.avanzar();
        moverProyectiles();
    }

    /**
     * Genera un nuevo proyectil que se dirige hacia el jugador.
     */
    private void disparar(Punto origen, Punto destino) {
        double velocidad = 0.9; // Velocidad del proyectil
        double dx = destino.getX() - origen.getX();
        double dy = destino.getY() - origen.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        if (dist == 0) return;

        double incX = velocidad * dx / dist;
        double incY = velocidad * dy / dist;

        ObjetoGraficoMovil proyectil = new ObjetoGraficoMovil(
            new Circulo(Color.WHITE, new Punto(origen.getX(), origen.getY()), 0.6),
            2, incX, incY
        ) {
            @Override
            public void recibirImpacto(ObjetoGrafico otro){
            // Proyectiles no reciben daño.
            }
        };

        proyectiles.add(proyectil);
    }

    /**
     * Mueve todos los proyectiles disparados por este enemigo y los elimina si salen del área.
     */
    private void moverProyectiles(){
        for (int i = proyectiles.size() - 1; i >= 0; i--) {
            ObjetoGraficoMovil p = proyectiles.get(i);
            p.avanzar();
            Punto c = p.getFigura().getCentroide();
            if (c.getX() < 0 || c.getX() > 100 || c.getY() < 0 || c.getY() > 100){
                proyectiles.remove(i);
            }
        }
    }

    /**
     * Pinta al enemigo y a sus proyectiles.
     */
    @Override
    public void pintar(){
        super.pintar();
        for (int i = 0; i < proyectiles.size(); i++){
            proyectiles.get(i).pintar();
        }
    }

    /**
     * Devuelve la lista de proyectiles del enemigo.
     */
    public IList<ObjetoGraficoMovil> getProyectiles(){
        return proyectiles;
    }

    /**
     * Genera una posición aleatoria dentro del área.
     */
    private static Punto generarPosicionAleatoria(){
        Random rand = new Random();
        double x = 10 + rand.nextDouble() * 80;
        double y = 10 + rand.nextDouble() * 80;
        return new Punto(x, y);
    }

    /**
     * Si hay colisión y el tiempo desde el último ataque es mayor que el cooldown,
     * realiza el ataque y devuelve el daño infligido.
     */
    public int hacerDano(){
        long tiempoActual = System.currentTimeMillis();
        if (hayColision(jugador) && tiempoActual - ultimoAtaqueTime >= COOLDOWN_ATAQUE) {
            ultimoAtaqueTime = tiempoActual;
            return daño;
        }
        return 0;
    }

    /**
     * Recibe un impacto del jugador (disparo y muerte).
     * 
     * @param otro El objeto que impacta (jugador).
     */
    @Override
    public void recibirImpacto(ObjetoGrafico otro) {
        this.vida = 0;
    }

    /**
     * Devuelve true si el enemigo está muerto.
     */
    public boolean estaMuerto() {
        return vida <= 0;
    }
}
