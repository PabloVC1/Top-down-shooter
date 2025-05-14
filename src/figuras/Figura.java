package figuras;

import java.awt.Color;

public abstract class Figura implements IFigura {
    protected Color color;
    protected Punto centroide;
    protected Punto[] boundingBox = new Punto[2];
    protected java.awt.geom.Area area;
    protected static int ESCALA = 1000;

    public Figura(Color color, Punto centroide) {
        this.color = color;
        this.centroide = centroide;
    }

    protected abstract void initArea();

    public abstract void pintar() ;
    
    public void mover(double incX, double incY) {
        centroide.setX(centroide.x() + incX);
        centroide.setY(centroide.y() + incY);
    }

    public boolean colisiona(Figura otraFigura){
        if (!(boundingBox[1].x() < otraFigura.boundingBox[0].x() || 
              boundingBox[0].x() > otraFigura.boundingBox[1].x() || 
              boundingBox[1].y() < otraFigura.boundingBox[0].y() || 
              boundingBox[0].y() > otraFigura.boundingBox[1].y())){
            java.awt.geom.Area intersection = new java.awt.geom.Area(area);
            intersection.intersect(otraFigura.area);
            return !intersection.isEmpty();
        }
        return false;
    }

    public Punto getCentroide(){
        return centroide;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getWidth(){
        return boundingBox[1].x()-boundingBox[0].x();
    }

    public double getHeight(){
        return boundingBox[1].y()-boundingBox[0].y();
    }
}
