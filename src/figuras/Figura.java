package figuras;
/** 
 * Implementaci�n de una figura geom�trica en el plano 
 * especificada en IFigura.
 */
import java.awt.Color;
import stdlib.StdRandom;

public abstract class Figura implements IFigura 
{ 
  private IPunto posicion;
  private Color color;
  private boolean rellena; 
  
  public Figura (IPunto posicion, Color color) {
    this.posicion = posicion;
    this.color = color;
    rellena = true;
  }

  // alea -> aleatorio
  public static Color colorAlea () {
    int r = StdRandom.uniformInt(256);
    int g = StdRandom.uniformInt(256);
    int b = StdRandom.uniformInt(256);
    return new Color(r,g,b);
  }

  public Figura (IPunto posicion) {
    this.posicion = posicion;
    color = colorAlea();
    rellena = false;
  }

  public IPunto posicion (){
    return posicion;
  }

  public Color color (){
    return color;
  }

  public boolean rellena (){
    return rellena;
  }

  public String toString (){
    return "Figura[posicion= "+posicion.toString()+", color= "+ color+", relleno= "+ rellena+"]";
  }

  public boolean equals (Object o) {
    if(!(o instanceof Figura)){
      return false;
    }
    Figura figura = (Figura) o;
    return posicion == figura.posicion && color == figura.color && rellena == figura.rellena;
  }

  public double distancia (IFigura f){
    return posicion.distancia(f.posicion());
  }

  public void mover (double dx, double dy) {
    posicion.mover(dx,dy);
  }

  public abstract double perimetro ();
  
  public abstract void pintar ();
}
