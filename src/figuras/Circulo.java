package figuras;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import stdlib.StdDraw;

public class Circulo extends Figura {
    private double radio;

    public Circulo(Color color, Punto centroide, double radio) {
        super(color, centroide);
        this.radio = radio;
        initArea();
    }

    @Override
    public void pintar() {
        StdDraw.setPenColor(color); 
        StdDraw.filledEllipse(centroide.x(), centroide.y(), radio, radio);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.ellipse(centroide.x(), centroide.y(), radio, radio);
    }

    @Override
    public void mover(double incX, double incY) {
        super.mover(incX, incY);
        initArea();
    }

    @Override
    protected void initArea() {
        area = new java.awt.geom.Area(new Ellipse2D.Double(centroide.x()*Figura.ESCALA, centroide.y()*Figura.ESCALA, radio*Figura.ESCALA, radio*Figura.ESCALA));
        this.boundingBox[0] = new Punto(centroide.x()-radio, centroide.y()-radio);
        this.boundingBox[1] = new Punto(centroide.x()+radio, centroide.y()+radio);
        
    }

    public double getRadio() {
        return radio;
    }

    
}
