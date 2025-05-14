package pong.model;

import java.util.Arrays;

import model.ObjetoGrafico;
import model.ObjetoGraficoDeUsuario;
import model.base.Poligono;
import model.base.Punto;

public class Portero extends ObjetoGraficoDeUsuario{

    public static final String PORTERO_IMAGE = "./portero.png";

    public static final double PORTERO_HEIGHT = 3;
    public static final double PORTERO_WIDTH = PORTERO_HEIGHT * 4;
    
    public static final double PORTERO_X = 50;
    public static final double PORTERO_Y = 5;

    public Portero() {
        super(new Poligono(java.awt.Color.DARK_GRAY,
                    new Punto(PORTERO_X,PORTERO_Y),
                    Arrays.asList(new Punto[]{new Punto(PORTERO_X-PORTERO_WIDTH,PORTERO_Y-PORTERO_HEIGHT),
                                new Punto(PORTERO_X+PORTERO_WIDTH,PORTERO_Y-PORTERO_HEIGHT),
                                new Punto(PORTERO_X+PORTERO_WIDTH,PORTERO_Y+PORTERO_HEIGHT),
                                new Punto(PORTERO_X-PORTERO_WIDTH,PORTERO_Y+PORTERO_HEIGHT)}
                )));
        //Usamos el mouse

        setImage(PORTERO_IMAGE);
    }

    @Override
    public void efectuarMovimiento(double vX, double vY) {
        colocar(vX, 
                0); // no usamos el vector de la paleta en Y
    }

    @Override
    public void recibirImpacto(ObjetoGrafico otraFigura) {
        // en este juego no debería pasar nada aunque algo impacte en la paleta
    }
    
}
