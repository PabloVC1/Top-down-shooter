package juego;

import java.awt.Color;
import stdlib.StdDraw;

/**
 * Hemos reutilizado todo lo del pong de ejemplo,
 * todos los cambios están documentados (en su mayoría están
 * en la carpeta bullet_hell).
 */
public abstract class Juego2DBase {
    public static final int XMAX = 600;
    public static final int YMAX = 600;
    public static final int BORDE= 10;
    public static final int ESCALA = 100;
    public static final Color DEFAULT_COLOR = StdDraw.BLACK;
    public static final int ESPACIO = 32; 
    public static final int PASO_MS = 50; 

    private boolean haPerdido = false;

    public void iniciarGraficos () 
    {  
      StdDraw.enableDoubleBuffering();
      StdDraw.setCanvasSize(XMAX, YMAX);
      StdDraw.setScale(0, ESCALA);
      // StdDraw.setPenRadius(5);
      StdDraw.setPenColor(DEFAULT_COLOR);
    } 

    public void pausa ()
    {
      do {} while (!StdDraw.isKeyPressed(ESPACIO));
      StdDraw.pause(PASO_MS);
    }

    /**
     * Bucle principal del juego.
     * Inicializa los gráficos, mueve los objetos, comprueba colisiones,
     * pinta los objetos y la interfaz, y comprueba si se ha perdido (para
     * finalizar el juego).
     * 
     * El único cambio respecto al ejemplo de pong es que
     * se ha añadido un método para pintar la interfaz del juego.
     */
    public void jugar(){
      iniciarGraficos();
      while (! haPerdido)  { 
        // clear the screen
        clear();

        // move objects.
        moverObjetos();

        // check interactions
        comprobarColisiones();

        // render the ball and the paddle.
        pintarObjetos();

        // render UHD
        pintarInterfaz();

        // check game over
        haPerdido = comprobarCondicionesSeguirJugando();
        
        // display and pause for 10 ms 
        try{
            Thread.sleep(10);
        }catch(Exception e){}
        StdDraw.show();
    }

    // finish game
    finalizarJuego();
    }

      
    private void clear() {
        StdDraw.clear(StdDraw.LIGHT_GRAY);
        StdDraw.rectangle(XMAX, YMAX, ESPACIO, ESCALA);
    }

    abstract protected void finalizarJuego();
  
    abstract protected boolean comprobarCondicionesSeguirJugando();
  
    abstract protected void pintarObjetos();

    abstract protected void pintarInterfaz(); // Pintar la interfaz del juego, como barras de vida, puntajes, etc. Único cambio
  
    abstract protected void comprobarColisiones();
  
    abstract protected void moverObjetos();
}
