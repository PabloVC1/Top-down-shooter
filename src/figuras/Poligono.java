package figuras;

import java.awt.Color;
import stdlib.StdDraw;
import tads.IList;
import tads.ArrayList;
/** 
 * Polígono.
 * Representa un polígono formado por una colección 
 * de Punto(s)
 * Poligono hereda de Figura:
 *   - El primer punto de puntos está representado
 *     por la posicion de Figura.  
 */
public class Poligono extends Figura{
  private IList<IPunto> vertices;

  /**
   * Poligono (puntos : Punto[]) 
   * PRE: longitud(puntos) >= 2
   * POST: Crea un Poligono relleno de color azul
   *       con un array de puntos enteros puntos. Deja 
   *       el primer punto de puntos como posicion de 
   *       Figura.
   */
  public Poligono (IPunto[] puntos, Color color)  {
    super(puntos[0],color);
    vertices = new ArrayList<>();
    for(int i = 0; i < puntos.length; i++){
      vertices.add(puntos[i]);
    }
  }

  public Poligono (IPunto[] puntos)  {
    super(puntos[0]);
    vertices = new ArrayList<>();
    for(int i = 0; i < puntos.length; i++){
      vertices.add(puntos[i]);
    }
  }

  public int size (){
    return vertices.size();
  }

  public IPunto get (int i){
    return vertices.get(i);
  }

  @Override
  public String toString (){
    return "Polígono[Vértices= "+ vertices.toString()+ ", "+ super.toString()+"]";
  }

  @Override
  public boolean equals (Object o) {
    if(!(o instanceof Poligono))
      return false;
    Poligono poligono = (Poligono) o;

  return vertices.equals(poligono.vertices) && super.equals(poligono);

  }

  @Override
  public void mover (double dx, double dy) {
    super.mover(dx,dy);
  }

  public double perimetro (){
    double perimetro = 0;
    for(int i = 0 ; i < vertices.size()-1; i++){
      perimetro += vertices.get(i).distancia(vertices.get(i+1));
    }
    return perimetro;
  }

  public void pintar ()
  {
    double[] abscisas = new double[vertices.size()];
    double[] ordenadas = new double[vertices.size()];
    for (int i=0; i<vertices.size(); i++)
    {
      abscisas[i] = vertices.get(i).x();
      ordenadas[i] = vertices.get(i).y();
    }
    StdDraw.setPenColor(color());
    if (rellena())
      StdDraw.filledPolygon(abscisas, ordenadas);
    else
      StdDraw.polygon(abscisas, ordenadas);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.point(super.posicion().x(), super.posicion().y());
  }
  
}