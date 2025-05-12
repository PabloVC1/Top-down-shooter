package figuras;
/** 
 * Representa un píxel en la pantalla
 * Dot hereda de Figura:
 *   - La posición de Dot está representada
 *     por la posicion de Figura.  
 */
import java.awt.Color;
import stdlib.StdDraw;

public class Dot extends Figura{
  public Dot (IPunto posicion, Color color) {
    super(posicion,color);
  }

  public Dot (IPunto posicion) {
    super(posicion);
  }

  @Override
  public String toString (){
    return "Dot["+super.toString()+"]";
  }

  @Override
  public boolean equals (Object o){
    if(!(o instanceof Dot))
      return false;

    Dot dot = (Dot) o;
    return super.equals(dot);
  }

  public double perimetro (){
    return 0;
  }

  public void pintar (){
    StdDraw.setPenColor(color());
    StdDraw.point(posicion().x(),posicion().y());
  }
}
