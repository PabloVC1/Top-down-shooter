package figuras;
/** 
 * Representa un rect�ngulo formado por su centro, 
 * su anchura y su altura.
 * Rectangulo extends Figura
 *   . El centro de Rectangulo est� representado
 *     por la posicion de Figura.  
 */
import java.awt.Color;
import stdlib.StdDraw;

public class Rectangulo extends Figura{
  private double anchura;
  private double altura;
  
  public Rectangulo (IPunto centro, double anchura, double altura, Color color) {
    super(centro, color);
    this.anchura = anchura;
    this.altura = altura;
  }

  public Rectangulo (IPunto centro, double anchura, double altura) {
    super(centro);
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

  public void pintar () {
    if (rellena()){
      StdDraw.setPenColor(color());
      StdDraw.filledRectangle(posicion().x(), posicion().y(), anchura/2, altura/2);
    }else{
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.rectangle(posicion().x(), posicion().y(), anchura/2, altura/2);
    }
  }
  
}
