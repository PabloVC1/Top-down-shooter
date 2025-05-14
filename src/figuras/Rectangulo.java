package figuras;
/** 
 * Representa un rect�ngulo formado por su centro, 
 * su anchura y su altura.
 * Rectangulo extends Figura
 *   . El centro de Rectangulo est� representado
 *     por la posicion de Figura.  
 */
import java.awt.Color;
import java.awt.geom.Rectangle2D;

import stdlib.StdDraw;

public class Rectangulo extends Figura{
  private double anchura;
  private double altura;
  
  public Rectangulo (Punto centro, double anchura, double altura, Color color) {
    super(color, centro);
    this.anchura = anchura;
    this.altura = altura;
  }

  public double anchura () {
    return anchura;
  }
  
  public double altura (){
    return altura;
  }

  public String toString () {
    return "Rectángulo[ancho= "+anchura+", alto= "+altura+", "+ super.toString()+"]";
  }

  public boolean equals (Object o) {
    if(!(o instanceof Rectangulo))
      return false;
    Rectangulo rectangulo = (Rectangulo) o;
    return altura == rectangulo.altura && anchura == rectangulo.anchura && super.equals(rectangulo);
  }

  public double perimetro (){
    return anchura * 2 + altura * 2;
  }

  @Override
protected void initArea() {
    area = new java.awt.geom.Area(new Rectangle2D.Double(
        centroide.x() * Figura.ESCALA,
        centroide.y() * Figura.ESCALA,
        anchura * Figura.ESCALA,
        altura * Figura.ESCALA
    ));

    this.boundingBox[0] = new Punto(centroide.x() - anchura / 2, centroide.y() - altura / 2);
    this.boundingBox[1] = new Punto(centroide.x() + anchura / 2, centroide.y() + altura / 2);
}


  public void pintar () {
    StdDraw.setPenColor(color);
    StdDraw.filledRectangle(centroide.x(), centroide.y(), anchura/2, altura/2);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.filledRectangle(centroide.x(), centroide.y(), anchura/2, altura/2);
  }
  
}
