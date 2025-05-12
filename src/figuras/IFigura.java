package figuras;
/** 
 * Especificación de una figura geométrica en el plano.
 * 
 * Formada por la posicion en la que está su centro geométrico.
 */
import java.awt.Color;
 
public interface IFigura
{
  /**
   * POST: Posición donde está la Figura
   */
  public IPunto posicion ();
  
  /**
   * POST: Color de la Figura
   */
  public Color color ();
  
  /**
   * POST: Figura rellena o no
   */
  public boolean rellena ();
  
  /**
   * POST: Convierte esta Figura a texto
   */
  public String toString ();
  
  /**
   * POST: "esta Figura es igual a o"
   */
  public boolean equals (Object o);
  
  /**
   * Mueve esta Figura según (dx, dy)
   */
  public void mover (double dx, double dy);
  
  /**
   * POST: Distancia de esta Figura a la Figura f
   */
  public double distancia (IFigura f);
  
  /**
   * POST: Perímetro de esta Figura
   */
  public double perimetro ();
    
  /**
   * Pinta esta Figura con un color y en modo relleno o no
   */
  public void pintar ();

}
