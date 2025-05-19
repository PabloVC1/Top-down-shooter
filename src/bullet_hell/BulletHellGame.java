package bullet_hell;

import juego.Juego2DBase;
import model.base.Circulo;
import stdlib.StdDraw;
import tads.IList;
import tads.LinkedList;
import bullet_hell.model.Jugador;
import bullet_hell.model.Enemigo;

public class BulletHellGame extends Juego2DBase {
    private static int score = 0;
    private int dificultad = 0;
    private Jugador player = new Jugador();
    private IList<Enemigo> enemigos = new LinkedList<>();
    
    public BulletHellGame(int dificultad){
        this.dificultad += dificultad;
        agregarEnemigos(dificultad * 3, dificultad*5);
    }

    private void agregarEnemigos(int num, int daño){
        for(int i = 0; i < num; i++){
            enemigos.add(new Enemigo(player, daño));
        }
    }

    protected void finalizarJuego() {
      StdDraw.text(50, 50, "Juego Terminado");
      StdDraw.text(50, 60, "Enemigos derrotados : " + score);
      for(int i=0;i<enemigos.size();i++)
        enemigos.get(i).avanzar();
      StdDraw.show();
    }

    protected boolean comprobarCondicionesSeguirJugando(){
        return !player.estaVivo();
    }

    protected void pintarObjetos(){
        player.pintar();
        for(int i = 0; i < enemigos.size(); i++){
            enemigos.get(i).pintar();
        }
    }

    
    protected void pintarInterfaz(){
        double anchoMax = 50;
        double alto = 3;
        double margenInferior = 5;
        double xCentro = 30;
        double porcentajeVida = player.vida()/100;
        double anchoVida = anchoMax * porcentajeVida;



        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(xCentro, margenInferior, anchoMax / 2, alto / 2);

        if(porcentajeVida > 0.6){
            StdDraw.setPenColor(StdDraw.GREEN);
        }else if(porcentajeVida > 0.2){
            StdDraw.setPenColor(StdDraw.ORANGE);
        }else{
            StdDraw.setPenColor(StdDraw.RED);
        }

        StdDraw.filledRectangle(xCentro - (anchoMax - anchoVida) / 2, margenInferior, anchoVida / 2, alto / 2);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(xCentro, margenInferior, anchoVida/2, alto/2);
    }

    protected void comprobarColisiones(){
        for(int i=enemigos.size()-1; i>=0; i--){
            enemigos.get(i).avanzar();  // Cambiado <= por >=
            if(player.hayColision(enemigos.get(i))){
                player.recibirImpacto(enemigos.get(i));
                if(enemigos.get(i).estaMuerto()){
                    enemigos.remove(i);
                }
            }
        }
        player.atacar(enemigos);
    }

    protected void moverObjetos(){
        for(int i = 0; i < enemigos.size(); i++){
            enemigos.get(i).avanzar(enemigos);
        }
        if (StdDraw.isKeyPressed(player.getTeclaSubir())) {
            player.eventoUsuarioTeclaMover(player.getTeclaSubir());
        }
        if (StdDraw.isKeyPressed(player.getTeclaBajar())) {
            player.eventoUsuarioTeclaMover(player.getTeclaBajar());
        }
        if (StdDraw.isKeyPressed(player.getTeclaDerecha())) {
            player.eventoUsuarioTeclaMover(player.getTeclaDerecha());
        }
        if (StdDraw.isKeyPressed(player.getTeclaIzquierda())) {
            player.eventoUsuarioTeclaMover(player.getTeclaIzquierda());
        }

    }
}
