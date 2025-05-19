package bullet_hell.model;

import model.ObjetoGrafico;
import model.ObjetoGraficoMovil;
import model.base.Circulo;
import model.base.Punto;
import java.awt.Color;
import java.util.Random;

public class Enemigo extends ObjetoGraficoMovil {
    private static final double VELOCIDAD = 0.3;
    private static final double RADIO = 5;
    private static final long COOLDOWN_ATAQUE = 1000;

    private Jugador jugador;
    private int daño;
    private long ultimoAtaqueTime = 0;
    private int vida = 1;

    public Enemigo(Jugador jugador, int daño) {
        super(new Circulo(Color.RED, generarPosicionAleatoria(), RADIO), 1, 0, 0);
        this.jugador = jugador;
        this.daño = daño;
    }

    @Override
    public void avanzar() {
        actualizarDireccion();
        super.avanzar();
        // Puedes agregar colisión con paredes si es necesario aquí.
    }

    private void actualizarDireccion() {
        Punto miCentro = getFigura().getCentroide();
        Punto centroJugador = jugador.getFigura().getCentroide();

        double dx = centroJugador.getX() - miCentro.getX();
        double dy = centroJugador.getY() - miCentro.getY();
        double distancia = Math.sqrt(dx * dx + dy * dy);

        if (distancia != 0) {
            setIncX(VELOCIDAD * (dx / distancia));
            setIncY(VELOCIDAD * (dy / distancia));
        } else {
            setIncX(0);
            setIncY(0);
        }
    }
    private static Punto generarPosicionAleatoria() {
        Random rand = new Random();
        double x = rand.nextDouble() * 100;
        double y = rand.nextDouble() * 100;
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

