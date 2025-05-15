package model;

import model.base.IFigura;
import stdlib.StdDraw;

public abstract class ObjetoGraficoDeUsuario extends ObjetoGrafico {
    private int teclaSubir = -1;
    private int teclaBajar = -1;
    private int teclaDerecha = -1;
    private int teclaIzquierda = -1;
    private boolean usarRaton = false; 
    public static final int STEP_MOVE = 5;


    public ObjetoGraficoDeUsuario(IFigura figura){
        super(figura, 1000);
        this.usarRaton = true;
    }

    public ObjetoGraficoDeUsuario(IFigura figura, int teclaSubir, int teclaBajar, int teclaDerecha, int teclaIzquierda){
        super(figura, 1000);
        this.teclaBajar=teclaBajar;
        this.teclaDerecha=teclaDerecha;
        this.teclaSubir=teclaSubir;
        this.teclaIzquierda=teclaIzquierda;
    }

    public int getTeclaSubir() {
        return teclaSubir;
    }

    public void setTeclaSubir(int teclaSubir) {
        this.teclaSubir = teclaSubir;
    }

    public int getTeclaBajar() {
        return teclaBajar;
    }

    public void setTeclaBajar(int teclaBajar) {
        this.teclaBajar = teclaBajar;
    }

    public int getTeclaDerecha() {
        return teclaDerecha;
    }

    public void setTeclaDerecha(int teclaDerecha) {
        this.teclaDerecha = teclaDerecha;
    }

    public int getTeclaIzquierda() {
        return teclaIzquierda;
    }

    public void setTeclaIzquierda(int teclaIzquierda) {
        this.teclaIzquierda = teclaIzquierda;
    }

    public void eventoUsuarioTeclaMover(int tecla){
        int incX = 0;
        int incY = 0;
        if (tecla==teclaSubir){
            incY = -STEP_MOVE;
        }
        else if (tecla==teclaBajar){
            incY = STEP_MOVE;
        }
        else if (tecla==teclaDerecha){
            incX = STEP_MOVE;
        }
        else if (tecla==teclaIzquierda){
            incX = -STEP_MOVE;
        }
        else {

        }
        efectuarMovimiento(incX, incY);
    }

    public void eventoUsuarioRatonMover(){
        double nextX = StdDraw.mouseX();
        double nextY = StdDraw.mouseY();
        if (nextX<0 || nextY<0 || nextX>100 || nextY>100){
            return;
        }
        efectuarMovimiento(nextX-getFigura().getCentroide().getX(),
            nextY-getFigura().getCentroide().getY());
    }

    public abstract void efectuarMovimiento(double vX, double vY);

    public boolean isUsarRaton() {
        return usarRaton;
    }

    

}
