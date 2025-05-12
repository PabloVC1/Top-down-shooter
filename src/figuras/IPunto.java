package figuras;
/** 
 * Especificación de Punto
 */
public interface IPunto {
  /**
   * POST: Coordenada x de este Punto
   */
  public double x ();

  /**
   * POST: Coordenada y de este Punto
   */
  public double y ();

  /**
   * POST: Este Punto convertido en texto
   */
  public String toString ();
  
  /**
   * POST: "este Punto es igual a o"
   */
  public boolean equals (Object o);
  
  /**
   * POST: Distancia de este Punto a p
   */
  public double distancia (IPunto p);
  
  /**
   * Mueve este Punto según (dx, dy)
   */
  public void mover (double dx, double dy);
    
}

