package juego;
import figuras.*;

public class Enemigo extends Entidad{
    private int daño;

    public Enemigo(Punto posicion, int vidaMax, int daño){
        super(posicion, vidaMax);
        this.daño = daño;

    }

    public int getDaño(){
        return daño;
    }

    public void calcularProximoMovimiento(){

    }

    public void atacar(Jugador j){
        j.changeVida(-daño);
    }


}