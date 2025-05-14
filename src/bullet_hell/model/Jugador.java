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

    
    //De la lista va uno a uno y coge al que más cerca esté y que no esté en cooldown
    public void atacar(IList<Enemigo> enemigos) {
    }

    //Resta su vida
    public void recibirImpacto(ObjetoGrafico Enemigo){

    }


    private double distanciaA(Enemigo enemigo) {
    }
}