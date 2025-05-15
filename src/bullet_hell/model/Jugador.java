package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoDeUsuario;
import model.base.Circulo;
import model.base.Punto;
import tads.IList;

import java.awt.Color;

public class Jugador extends ObjetoGraficoDeUsuario {
    private static final double RADIO_ATAQUE = 15;
    private long ultimoAtaqueTime = 0;
    private int vida = 100;

    public Jugador() {
    }

    
    //Si no está en cooldown, de la lista va uno a uno y coge al que más cerca 
    //esté dentro de ese rango de ataque y lo mata (enemigo.recibirImpacto())
    public void atacar(IList<Enemigo> enemigos) {
    }

    //Resta su vida
    public void recibirImpacto(ObjetoGrafico Enemigo){

    }

    //calcula la distancia a un enemigo
    private double distanciaA(Enemigo enemigo) {
    }

    //devuelve un booleano si su vida es mayor a cero
    public boolean estaVivo(){
    }

    public boolean hayColision(Enemigo enemigo){

    }
}