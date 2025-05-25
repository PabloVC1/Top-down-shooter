package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoDeUsuario;
import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.IFigura;
import model.base.Punto;
import tads.IList;

import java.awt.Color;

import juego.Juego2DBase;

/**
 * Un jugador que puede atacar enemigos y moverse por el mapa.
 */
public class Jugador extends ObjetoGraficoDeUsuario {
    private static final double RADIO_ATAQUE = 15; //este será el área de daño
    private static final double RADIO= 5; //radio del jugador
    private long ultimoAtaqueTime = 0;
    private int vida = 100; 
    private final long cooldown=3000; //en milisegundos

    /**
     * Constructor del jugador.
     * Inicializa el jugador con la imagen y el radio y le asigna controles de movimiento.
     */
    public Jugador(){
        super(new Circulo(Color.BLUE, new Punto(50, 50), RADIO), 'S', 'W', 'D', 'A');
        setImage("src/resources/Caballero.png");

    }

    /**
     * Recorre la lista de enemigos y selecciona al más cercano en el radio de ataque.
     * Luego, lo ataca y actualiza el tiempo del último ataque.
     * Si está en cooldown, no hace nada.
     *
     * @param enemigos Lista de enemigos a los que el jugador puede atacar.
     */
    public void atacar(IList<Enemigo> enemigos){
        if(!puedeAtacar()) return; //si no puede atacar no hace nada
        Enemigo objetivo = null;
        double distanciaMin = Double.MAX_VALUE;
        for (int i = 0; i < enemigos.size(); i++){ //repite hasta terminar con todos los enemigos
            Enemigo enemigo = enemigos.get(i);
            double distancia = distanciaA(enemigo); 
            if (distancia <= RADIO_ATAQUE && distancia <= distanciaMin){ //si el enemigo está dentro del radio y es la minima distancia 
                distanciaMin = distancia;
                objetivo = enemigo; 
            }
        }
        if (objetivo != null) { 
            objetivo.recibirImpacto(this);
            ultimoAtaqueTime = System.currentTimeMillis();
        }

    }

    /**
     * Comprueba si el jugador no está en cooldown (puede atacar).
     * @return true si el jugador puede atacar, false si está en cooldown.
     */
    public boolean puedeAtacar(){
        return System.currentTimeMillis()-ultimoAtaqueTime >= cooldown;
    }

    /**
     * Recibe un impacto del enemigo o de un proyectil (otro objeto gráfico móvil)
     * y reduce la vida del jugador (con el daño fijo del otro).
     * 
     * @param otro El objeto que impacta (puede ser un enemigo o un proyectil).
     */
    @Override
    public void recibirImpacto(ObjetoGrafico otro) {
        if (otro instanceof Enemigo) {
            vida -= ((Enemigo) otro).hacerDano();
        } else if (otro instanceof ObjetoGraficoMovil) {
            vida -= 5; // Daño fijo por proyectil, puedes ajustar este número
        }
    }


    /**
     * Devuelve la distancia entre un enemigo y el jugador.
     * @return La distancia entre el jugador (centro p1) y el enemigo (centro p2).
     */
    private double distanciaA(Enemigo enemigo){
        Punto p1=getFigura().getCentroide();
        Punto p2=enemigo.getFigura().getCentroide();
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2)+Math.pow(p1.getY()-p2.getY(),2));
    }

    /**
     * Comprueba si el jugador está vivo (tiene vida > 0).
     * @return true si el jugador está vivo, false si no.
     */
    public boolean estaVivo(){
        return vida>0;
    }

    /**
     * Mueve al jugador en la dirección indicada por los parámetros vX y vY,
     * asegurándose de que no se salga de los límites del mapa.
     * Prueba a moverlo en el eje X y Y (nuevoX/Y), y si no es posible (dentroX/Y),
     * lo mueve solo en uno de los ejes.
     * 
     * @param vX Velocidad en el eje X.
     * @param vY Velocidad en el eje Y.
     */
   @Override
    public void efectuarMovimiento(double vX, double vY) {
        Circulo jugador = (Circulo) getFigura();
        Punto centro = jugador.getCentroide();
        double nuevoX = centro.getX() + vX;
        double nuevoY = centro.getY() + vY;
        double radio = RADIO;

        // Verificamos si el nuevo centro estará dentro de los límites del mapa
        boolean dentroX = (nuevoX - radio >= 0) && (nuevoX + radio <= Juego2DBase.ESCALA);
        boolean dentroY = (nuevoY - radio >= 0) && (nuevoY + radio <= Juego2DBase.ESCALA);

        if (dentroX && dentroY) {
            getFigura().mover(vX, vY);
         } else if (dentroX) {
            getFigura().mover(vX, 0);
        } else if (dentroY) {
            getFigura().mover(0, vY);
        }
    }

    /**
     * Devuelve la vida del jugador.
     * @return La vida actual del jugador.
     */
    public double vida(){
        return (double) vida;
    }

    /**
     * Devuelve el cooldown del jugador.
     * Si no puede atacar, devuelve el tiempo restante del cooldown.
     * 
     * @return El cooldown actual del jugador.
     */
    public float cooldown(){
        if(!puedeAtacar()) return (float) ((System.currentTimeMillis() - ultimoAtaqueTime)/10)*10;
        return cooldown;
    }
}