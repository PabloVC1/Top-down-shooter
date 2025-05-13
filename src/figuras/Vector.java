package figuras;

public class Vector {
    private double[] coords;

    public Vector(double x, double y){
        coords = new double[2];
        coords[0] = x;
        coords[1] = y;
    }

    public Vector(IPunto p1, IPunto p2){
        coords = new double[2];
        coords[0] = p2.x() - p1.x();
        coords[1] = p2.y() - p1.y();
    }

    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;

        return coords[0] == vector.coord(0) && coords[1] == vector.coord(1);
    }

    public Vector sum (Vector v){
        return new Vector(coords[0]+v.coord(0), coords[1]+v.coord(1));
    }
    
    public Vector dif (Vector v){
        return new Vector(coords[0]-v.coord(0), coords[1]-v.coord(1));
    }

    public double prod (Vector v){
        return coords[0]*v.coord(0) + coords[1]*v.coord(1);
    }

    public Vector prod (double k){
        return new Vector(coords[0]*k, coords[1]*k);
    }

    public double mod (){
        return Math.sqrt(prod(this));
    }

    public Vector dir (){
        double mod = mod();
        return new Vector(coords[0]/mod, coords[1]/mod);
    }

    public double dist (Vector v){
        return dif(v).mod();
    }

    public double coord(int i){
        return coords[i];
    }

}
