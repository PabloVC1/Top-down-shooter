package juego;

import figuras.*;

public class Entidad {
    private Punto posicion;
    private Vector velocidad;
    private int vidaMax;
    private int vida;

    public Entidad(Punto posicion, Vector velocidad, int vidaMax){
        this.posicion = posicion;
        this.velocidad = velocidad;
        this.vidaMax = vidaMax;
        vida = vidaMax;
    }

    public Entidad(Punto posicion, int vidaMax){
        this.posicion = posicion;
        velocidad = new Vector(0,0);
        this.vidaMax = vidaMax;
        vida = vidaMax;
    }

    public Punto getPosicion(){
        return posicion;
    }

    public Vector getVelocidad(){
        return velocidad;
    }

    public int vidaMax(){
        return vidaMax;
    }

    public int getVida(){
        return vida;
    }

    public boolean existe(){
        return vida > 0;
    }

//Positivo para curar, Negativo hace daño
    public void changeVida(int daño){
        if(vida+daño < vidaMax)
            vida+=daño;
        else vida= vidaMax;
    }

    public void mover(){
    }

//dispuesto mejorar entidades
    public void setVelocidad(Vector v){
        velocidad = v;
    }

//funcion de colision con otra entidad usando lo del otro juego (initArea)


}
