package pong;

import stdlib.StdDraw;
import tads.IList;
import tads.LinkedList;
import juego.Juego2DBase;
import model.base.Circulo;
import model.base.Punto;
import pong.model.Pelota;
import pong.model.Portero;

public class JuegoPortero extends Juego2DBase {
    static double vx = .8, vy = -.8;     // velocity

    private static int score = 0;
    private Portero paleta = new Portero();
    private IList<Pelota> pelotas = new LinkedList<>();

    public JuegoPortero(){
      for (int i=0;i<6;i++){
        pelotas.add(new Pelota(
          new Circulo(StdDraw.BLUE, new Punto(50, 50), 2), 
          1, vx, vy));
      }
      
    }

    protected void finalizarJuego() {
      StdDraw.text(50, 50, "Juego Terminado");
      StdDraw.text(50, 60, "Puntos : " + score);
      for(int i=0;i<pelotas.size();i++)
        pelotas.get(i).avanzar();
      StdDraw.show();
    }

    protected boolean comprobarCondicionesSeguirJugando() {
      boolean todosFuera = true;
      for(int i=0;i<pelotas.size();i++){
        todosFuera = todosFuera && pelotas.get(i).getFigura().getCentroide().getY()<0;
      }
      return todosFuera;
    }

    protected void pintarObjetos() {
      paleta.pintar();
      for(int i=0;i<pelotas.size();i++){
         pelotas.get(i).pintar();
      }
    }

    protected void comprobarColisiones() {
      for(int i=0;i<pelotas.size();i++){
        if (paleta.hayColision(pelotas.get(i))){
          pelotas.get(i).recibirImpacto(paleta);
          score +=100;
          if (score%200==0)
          pelotas.get(i).incrementarVelocidad();
          
          pelotas.get(i).avanzar();
        }
      }
    }

    protected void moverObjetos() {
      for(int i=0;i<pelotas.size();i++){
        pelotas.get(i).avanzar();
      }
      paleta.eventoUsuarioRatonMover();
    }

}
