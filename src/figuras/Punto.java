package figuras;
public class Punto implements IPunto {
    private double x;
    private double y;

    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double x(){
        return x;
    }

    public double y (){
        return y;
    }

    public String toString(){
        return "Punto[x=" + x + ", y="+y+"]";
    }
    
    public boolean equals(Object o){
        if(!(o instanceof Punto)){
            return false;
        }
        Punto punto = (Punto)o;
        return x == punto.x && y == punto.y;
    }

    public double distancia(IPunto p){
        return Math.sqrt(
            Math.pow(x-p.x(), 2) +
            Math.pow(y-p.y(),2)
        );
    }

    public void mover(double dx, double dy){
        x += dx;
        y += dy;
    }
    public void mover(Vector v){
        x += v.coord(0);
        y += v.coord(1);
    }
}