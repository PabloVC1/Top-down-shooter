package figuras;

import java.awt.Color;
import java.util.List;

import stdlib.StdDraw;

public class Poligono extends Figura {

    private double xPoly[] ;
    private double yPoly[] ;

    public Poligono(Color color, Punto centroide, List<Punto> vertices) {
        super(color, centroide);
        xPoly = new double[vertices.size()];
        yPoly = new double[vertices.size()];
        this.boundingBox[0] = new Punto(centroide.x(), centroide.y());
        this.boundingBox[1] = new Punto(centroide.x(), centroide.y());
        
        for (int i=0;i<vertices.size();i++){
            xPoly[i] = vertices.get(i).x();
            yPoly[i] = vertices.get(i).y();
        }
        initArea();
    }

    @Override
    public void mover(double incX, double incY) {
        super.mover(incX, incY);
        for (int i=0;i<xPoly.length;i++){
            xPoly[i] = xPoly[i] + incX;
            yPoly[i] = yPoly[i] + incY;
        }
        initArea();
    }

    @Override
    public void pintar() {
        StdDraw.setPenColor(color);
        StdDraw.filledPolygon(xPoly, yPoly);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(xPoly, yPoly);
    }

    @Override
    protected void initArea() {
        this.boundingBox[0] = new Punto(centroide.x(), centroide.y());
        this.boundingBox[1] = new Punto(centroide.x(), centroide.y());
        int[] ixPoly = new int[xPoly.length];
        int[] iyPoly = new int[yPoly.length];
        for (int i=0;i<ixPoly.length;i++){
            ixPoly[i] = (int)Math.round(xPoly[i]*Figura.ESCALA);
            iyPoly[i] = (int)Math.round(yPoly[i]*Figura.ESCALA);
            if (xPoly[i]<this.boundingBox[0].x())
                this.boundingBox[0].setX(xPoly[i]);
            if (xPoly[i]>this.boundingBox[1].x())
                this.boundingBox[1].setX(xPoly[i]);
            if (yPoly[i]<this.boundingBox[0].y())
                this.boundingBox[0].setY(yPoly[i]);
            if (yPoly[i]>this.boundingBox[1].y())
                this.boundingBox[1].setY(yPoly[i]);
        }
        java.awt.Polygon  polygon = new java.awt.Polygon(ixPoly,iyPoly,yPoly.length);
        area = new java.awt.geom.Area(polygon);
    }
    
}
