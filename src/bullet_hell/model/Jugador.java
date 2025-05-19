package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoDeUsuario;
import model.base.Circulo;
import model.base.IFigura;
import model.base.Punto;
import tads.IList;

import java.awt.Color;

import juego.Juego2DBase;

public class Jugador extends ObjetoGraficoDeUsuario {
    private static final double RADIO_ATAQUE = 15; //este será el área de daño 
    private static final double RADIO= 5;
    private long ultimoAtaqueTime = 0;
    private int vida = 100;
    private final long cooldown=3000; //en milisegundos

    //constructor del jugador 
    public Jugador(){
        super(new Circulo(Color.BLUE, new Punto(50, 50), RADIO), 'W', 'S', 'D', 'A');
    }

    //Si no está en cooldown, de la lista va uno a uno y coge al que más cerca 
    //esté dentro de ese rango de ataque y lo mata (enemigo.recibirImpacto())
    public void atacar(IList<Enemigo> enemigos){
        if (!puedeAtacar()) 
            return;
        //jugador no esta en cooldown 
        Enemigo objetivo = null;
        double distanciaMin = Double.MAX_VALUE;
        Punto centro = getFigura().getCentroide();
        for (int i = 0; i < enemigos.size(); i++){ //repite hasta terminar con todos los enemigos
            Enemigo enemigo = enemigos.get(i); //obtiene el elemento de la lista Enemigo
            double distancia = distanciaA(enemigo); //calcula la distancia a enemigo
            if (distancia <= RADIO_ATAQUE && distancia < distanciaMin){ //si el enemigo está dentro del radio y es la minima distancia 
                distanciaMin = distancia; //nueva minima distancia
                objetivo = enemigo; //nuevo objetivo ya que atacamos al más cercano
            }
        }
        if (objetivo != null) { //si hay objetivo (el enemigo mas cercano dentro del area de ataque) 
            objetivo.recibirImpacto(this);//dispara al enemigo
            ultimoAtaqueTime = System.currentTimeMillis(); //iguala el tiempo del ultimo ataque
        }

    }
    
    public boolean puedeAtacar(){
        return System.currentTimeMillis()-ultimoAtaqueTime<=cooldown;//comprueba si el jugador esta en cooldown
    }

    //Resta su vida
    public void recibirImpacto(ObjetoGrafico enemigo){
        if(enemigo instanceof Enemigo){
            Enemigo otro = (Enemigo) enemigo;
            vida -= otro.hacerDano(); //resta el daño correspondiente al tipo de enemigo
        }
    }

    //calcula la distancia a un enemigo
    private double distanciaA(Enemigo enemigo){
        Punto p1=getFigura().getCentroide();//centro del jugador
        Punto p2=enemigo.getFigura().getCentroide();//centro del enemigo
        return Math.sqrt(Math.pow(p1.getX()-p2.getX(), 2)+Math.pow(p1.getY()-p2.getY(),2));//distancia entre centros
    }

    //devuelve un booleano si su vida es mayor a cero
    public boolean estaVivo(){
        return vida>0;
    }

    //comprueba que el jugador permanece en el terreno de juego
    public boolean hayColision(){
            Circulo jugador = (Circulo) getFigura();
            double x= jugador.getCentroide().getX(); //coordenada x del centro
            double y=jugador.getCentroide().getY(); //coordenanda y del centro
            double radiojug=RADIO; //radio jugador
            boolean dentroX=(x-radiojug>=0)&&(x+radiojug<=Juego2DBase.ESCALA); //comprueba que no este dentro del terreno
            boolean dentroY=(y-radiojug>=0)&&(y+radiojug<=Juego2DBase.ESCALA);
            return dentroX && dentroY;  
    }

    //desplaza al jugador
    @Override
    public void efectuarMovimiento(double vX, double vY) {
        //comprueba si esta dentro del terreno
        if(!(hayColision()))
            return;
        //lo mueve
        else{
            getFigura().mover(vX, vY);
        }
    }
}