package bullet_hell.model;

import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.Figura;
import model.base.Punto;
import java.awt.Color;

public class Enemigo extends ObjetoGraficoMovil {
    private static final double VELOCIDAD = 0.3;
    private Jugador jugador;
    private int daño;
    private long ultimoAtaqueTime = 0;

    //Se genera el enemigo en una posición aleatoria (recordar el super)
    public Enemigo(Jugador jugador, int daño) {
    }

    //linea recta hacia jugador y colisión con paredes.
    @Override
    public void avanzar() {
    }

    private void actualizarDireccion() {
    }

    private static Punto generarPosicionAleatoria() {
    }

    public void daño(){

    }
    public void ultimoAtaqueTime(){
    }
    public int getDaño(){
        return daño;
    }
    //muere
    public void recibirImpacto(){

    }


}
