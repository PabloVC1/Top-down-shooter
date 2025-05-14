package bullet_hell.model;

import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.Punto;
import java.awt.Color;

public class Enemigo extends ObjetoGraficoMovil {
    private static final double VELOCIDAD = 0.3;
    private Jugador jugador;
    private int vida;
    private int daño;
    private long ultimoAtaqueTime = 0;

    //Se genera el enemigo en una posición aleatoria
    public Enemigo(Jugador jugador, int vida, int daño) {
    }

    @Override
    public void avanzar() {
    }

    private void actualizarDireccion() {
    }

    private static Punto generarPosicionAleatoria() {
    }

    public int hacerDano(long t){

    }
}
