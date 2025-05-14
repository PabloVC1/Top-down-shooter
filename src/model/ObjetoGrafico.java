package model;

import model.base.Figura;
import model.base.IFigura;
import stdlib.StdDraw;
import tads.IList;
import tads.LinkedList;

public abstract class ObjetoGrafico {
    private  IList<IFigura> figura = new LinkedList<>();
    private int capa = 0;
    private String imageFilename = null;

    public ObjetoGrafico(IFigura figura, int capa){
        this.figura.add(figura); 
        this.capa = capa;
    }

    public void setImage(String filename){
        imageFilename = filename;
    }

    public void pintar(){
        if (capa>=0)
            if (imageFilename!=null)
                try{
                    double ancho = figura.get(0).getWidth();
                    double alto = figura.get(0).getHeight();
                    StdDraw.picture(figura.get(0).getCentroide().getX(), 
                        figura.get(0).getCentroide().getY(),
                        imageFilename,
                        ancho,
                        alto); 
                    }
                    catch(Exception e){
                        System.err.println(e.getLocalizedMessage());
                        figura.get(0).pintar();
                    }
                else
                    figura.get(0).pintar();
    }

    public void colocar(double x, double y){
        figura.get(0).mover(x, y);
    }

    public int getCapa(){
        return capa;
    }

    public void setCapa(int capa){
        this.capa = capa;
    }

    public abstract void recibirImpacto(ObjetoGrafico otraFigura);

    public boolean hayColision(ObjetoGrafico otraFigura){
        return ((Figura)getFigura()).colisiona((Figura)otraFigura.getFigura());
    }

    public IFigura getFigura(){
        return figura.get(0);
    }

}
