package juego;

import figuras.*;
import stdlib.StdDraw;
import stdlib.StdRandom;
import tads.*;


public class Mundo{;
    private static final int MAX = 1000;
    private static final int ESCALA = 300;
    private static final double GROSORPINCEL = 0.01;

    private final int numObstaculos = 10;
    private IList<IFigura> figuras = new ArrayList<>();

    public Mundo(){
        Circulo o1 = new Circulo(new Punto(random(150), random(150)),random(20), StdDraw.BLACK);
        Circulo o2 = new Circulo(new Punto(random(150), random(150)),random(20), StdDraw.BLACK);
        Circulo o3 = new Circulo(new Punto(random(150), random(150)),random(20), StdDraw.BLACK);
        Circulo o4 = new Circulo(new Punto(random(150), random(150)),random(20), StdDraw.BLACK);

        Rectangulo o5 = new Rectangulo(new Punto(random(150), random(150)), random(150), random(150), StdDraw.BLACK);
        Rectangulo o6 = new Rectangulo(new Punto(random(150), random(150)), random(150), random(150), StdDraw.BLACK);
        Rectangulo o7 = new Rectangulo(new Punto(random(150), random(150)), random(150), random(150), StdDraw.BLACK);
        Rectangulo o8 = new Rectangulo(new Punto(random(150), random(150)), random(150), random(150), StdDraw.BLACK);

        IPunto[]puntos = new IPunto[5];
        for(int i = 0; i < puntos.length; i++){
            puntos[i] = new Punto(random(150), random(150));
        }
        Poligono o9 = new Poligono(puntos, StdDraw.BLACK);

        IPunto[]puntos2 = new IPunto[5];
        for(int i = 0; i < puntos.length; i++){
            puntos[i] = new Punto(random(150), random(150));
        }
        Poligono o10 = new Poligono(puntos2,StdDraw.BLACK);

        figuras.add(o1);
        figuras.add(o2);
        figuras.add(o3);
        figuras.add(o4);
        figuras.add(o5);
        figuras.add(o6);
        figuras.add(o7);
        figuras.add(o8);
        figuras.add(o9);
        figuras.add(o10);
    }

    // devuelve un numero random
    private int random(int max){
        return StdRandom.uniformInt(max);
    }
    private int random(int ini, int fin){
        return StdRandom.uniformInt(ini,fin);
    }
    public void generar(){
        StdDraw.setCanvasSize(MAX,MAX);
        StdDraw.clear(StdDraw.DARK_GRAY);
        StdDraw.setScale(-ESCALA,ESCALA);
        StdDraw.setPenRadius(GROSORPINCEL);
        StdDraw.setPenColor(StdDraw.RED);

        for(int i = 0; i < numObstaculos; i++){
            figuras.get(i).pintar();
        }
    }

    public boolean colision(Figura fig){
        return false;
    }
}