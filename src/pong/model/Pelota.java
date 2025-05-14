package pong.model;

import model.base.Circulo;
import model.ObjetoGrafico;
import model.ObjetoGraficoMovil;

public class Pelota extends ObjetoGraficoMovil {

    public static final double VEL_MAX = 6.5;
    public static final double VEL_MULT = 1.15;
    public static final String PELOTA_IMAGE = "./ball.png";

    public Pelota(Circulo figura, int capa, double incX, double incY) {
        super(figura, capa, incX, incY);
        setImage(PELOTA_IMAGE);
    }

    public void avanzar(){
        super.avanzar();
        if (getFigura().getCentroide().getX()<0 || getFigura().getCentroide().getX()>100 )
            setIncX(-getIncX());
        if (getFigura().getCentroide().getY()>100)
            setIncY(-getIncY());
    }   

    @Override
    public void recibirImpacto(ObjetoGrafico otraFigura) {
        setIncY(-getIncY());
        setIncX(getIncX()+ (Math.random()-0.5));
    }

    public void incrementarVelocidad(){
        if (getIncY()<VEL_MAX)
        setIncY(getIncY()*VEL_MULT);
        
    }
    
}
