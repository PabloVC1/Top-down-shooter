package figuras;
/**
 * Circulo.
 * Representa un círculo formado por un centro y un radio.
 * Circulo hereda de Figura:
 *   - El centro de Circulo está representado
 *     por la posicion de Figura.  
 */
import java.awt.Color;
import stdlib.StdDraw;

public class Circulo extends Figura{
  private int radio;
  
  public Circulo (IPunto centro, int radio, Color color) {
    super(centro, color);
    this.radio = radio;
  }

  public Circulo (IPunto centro, int radio) {
    super(centro);
    this.radio = radio;
  }

  public int radio (){
    return radio;
  }

  @Override
  public String toString (){
    return "Círculo[radio= "+ radio+ ", "+ super.toString()+"]";
  }

  @Override
  public boolean equals (Object o) {
    if(!(o instanceof Circulo)){
      return false;
    }
    Circulo circulo = (Circulo) o;
    return radio == circulo.radio && super.equals(circulo);
  }

  public double perimetro (){
    return 2 * Math.PI * radio;
  }

  public void pintar (){
    if(rellena()){
      StdDraw.setPenColor(color());
      StdDraw.filledCircle(super.posicion().x(), super.posicion().y(), radio);
    }else{
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.circle(super.posicion().x(), super.posicion().y(), radio);
    }
  }
}