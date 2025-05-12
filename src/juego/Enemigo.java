package juego;
import figuras.*;

public class Enemigo extends Entidad{
    private Punto posicion;
    private int vida;
    private int vidaMax;
    private static String[] ESTADO = {"IDLE", "PERSECUCION", "ATAQUE", "HUIDA"};
    
    public Enemigo(Punto posicion, int vidaMax){
        this.posicion = posicion;
        this.vidaMax = vidaMax;
        vida = vidaMax;

    }
}