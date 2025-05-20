package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.Punto;
import tads.IList;

import java.awt.Color;
import java.util.Random;

/**
 * Representa a un enemigo en el juego que persigue al jugador
 * y evita solaparse con otros enemigos aplicando una fuerza de separación.
 */
public class Enemigo extends ObjetoGraficoMovil {
    private static final double VELOCIDAD = 0.2;
    private static final double RADIO = 3;
    private static final long COOLDOWN_ATAQUE = 1000;
    private static final double FUERZA_SEPARACION = 0.8;

    private Jugador jugador;
    private int daño;
    private long ultimoAtaqueTime = 0;
    private int vida = 1;

    public Enemigo(Jugador jugador, int daño) {
        super(new Circulo(Color.RED, generarPosicionAleatoria(), RADIO), 1, 0, 0);
        this.jugador = jugador;
        this.daño = daño;
        setImage("src/resources/vecteezy_an-8-bit-retro-styled-pixel-art-illustration-of-a-goblin_19017599.png");
    }

    /**
     * Mueve el enemigo hacia el jugador mientras aplica una fuerza de separación
     * para evitar solaparse con otros enemigos.
     */
    public void avanzar(IList<Enemigo> enemigos) {
        Punto miCentro = getFigura().getCentroide();
        Punto centroJugador = jugador.getFigura().getCentroide();

        // Dirección hacia el jugador
        double dx = centroJugador.getX() - miCentro.getX();
        double dy = centroJugador.getY() - miCentro.getY();
        double distanciaJugador = Math.sqrt(dx * dx + dy * dy);

        double dirX = 0;
        double dirY = 0;

        if (distanciaJugador > 0) {
            dirX += dx / distanciaJugador;
            dirY += dy / distanciaJugador;
        }

        // Separación con otros enemigos
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo otro = enemigos.get(i);
            if (otro == this) continue;

            Punto otroCentro = otro.getFigura().getCentroide();
            double distX = miCentro.getX() - otroCentro.getX();
            double distY = miCentro.getY() - otroCentro.getY();
            double distancia = Math.sqrt(distX * distX + distY * distY);

            if (distancia < RADIO * 2 && distancia > 0.01) {
                // Fuerza de separación inversa al cuadrado de la distancia
                double fuerza = FUERZA_SEPARACION / (distancia * distancia);
                dirX += (distX / distancia) * fuerza;
                dirY += (distY / distancia) * fuerza;
            }
        }

        // Normalizar y aplicar velocidad
        double magnitud = Math.sqrt(dirX * dirX + dirY * dirY);
        if (magnitud != 0) {
            setIncX(VELOCIDAD * (dirX / magnitud));
            setIncY(VELOCIDAD * (dirY / magnitud));
        } else {
            setIncX(0);
            setIncY(0);
        }

        super.avanzar(); // aplica el incX / incY y actualiza la posición
    }

    private static Punto generarPosicionAleatoria() {
        Random rand = new Random();
        double x = 10 + rand.nextDouble() * 80;
        double y = 10 + rand.nextDouble() * 80;
        return new Punto(x, y);
    }

    public int hacerDano() {
        long tiempoActual = System.currentTimeMillis();
        if (hayColision(jugador) && tiempoActual - ultimoAtaqueTime >= COOLDOWN_ATAQUE) {
            ultimoAtaqueTime = tiempoActual;
            return daño;
        }
        return 0;
    }

    @Override
    public void recibirImpacto(ObjetoGrafico otro) {
        this.vida = 0;
    }

    public boolean estaMuerto() {
        return vida <= 0;
    }
}
