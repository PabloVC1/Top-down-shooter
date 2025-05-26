package bullet_hell;

import juego.Juego2DBase;
import stdlib.StdDraw;
import tads.IList;
import tads.LinkedList;
import bullet_hell.model.Jugador;
import bullet_hell.model.Enemigo;
import model.ObjetoGraficoMovil;

/**
 * Juego de Bullet Hell donde el jugador debe esquivar enemigos y dispararles (auto).
 * El juego aumenta en dificultad a medida que se eliminan enemigos (añadiendo
 * más enemigos y aumentando su daño).
 */

public class BulletHellGame extends Juego2DBase {
    private static int score = 0;
    private int dificultad = 0;
    private Jugador player = new Jugador();
    private IList<Enemigo> enemigos = new LinkedList<>(); // Lista de enemigos en el juego
    
    /**
     * Constructor del juego Bullet Hell.
     * Inicializa el jugador y agrega enemigos según la dificultad (variable
     * que define la cantidad de enemigos que aparecen).
     */
    public BulletHellGame(int dificultad){
        this.dificultad = dificultad;
        agregarEnemigos(dificultad * 3, dificultad*5);
    }

    /**
     * Método principal que inicia el juego.
     * Configura la ventana de dibujo, el bucle del juego y las condiciones de finalización.
     */
    private void agregarEnemigos(int num, int daño){
        for(int i = 0; i < num; i++){
            enemigos.add(new Enemigo(player, daño));
        }
    }

    /**
     * Método para cuando finaliza el juego.
     * Limpia la lista de enemigos, pinta un mensaje y muestra
     * la puntuación final (enemigos derrotados).
     */
    protected void finalizarJuego() {
      enemigos = new LinkedList<>();
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(50, 50, 12, 4);
      StdDraw.filledRectangle(50, 60, 16, 4);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.text(50, 50, "Juego Terminado");
      StdDraw.text(50, 60, "Enemigos derrotados : " + score);
      StdDraw.show();
    }

    /**
     * Comprobar si el jugador sigue vivo.
     * Si el jugador no está vivo, se finaliza el juego.
     */
    protected boolean comprobarCondicionesSeguirJugando(){
        return !player.estaVivo();
    }

    /**
     * Genera los gráficos del juego.
     * Pinta el fondo y los objetos del juego (jugador y enemigos).
     * Además, es el que se ocupa de eliminar a los enemigos que están muertos.
     */
    protected void pintarObjetos(){
        StdDraw.picture(50,50,"src/resources/background.jpg",100,100);
        Enemigo muerto = null;
        for(int i = enemigos.size()-1; i >= 0; i--){
            Enemigo enemigo = enemigos.get(i);
            enemigo.pintar();// Pinta cada enemigo en la lista y sus proyectiles
            if(enemigo.estaMuerto()){// Si el enemigo está muerto, lo elimina de la lista y aumenta la puntuación
                    enemigos.remove(i);
                    score++;
                    muerto = enemigo;
                    if(score % 2 == 0){
                        agregarEnemigos(++dificultad , dificultad*5);
                    }
            }
        }
        player.pintar();
        animacionEnemigoMuerto(muerto);
    }

    /**
     * Método que pinta la interfaz del juego.
     * Muestra la barra de vida del jugador y el cooldown de su ataque.
     */
    protected void pintarInterfaz(){
        double anchoMax = 50;
        double alto = 3;
        double margenInferior = 5;
        double xCentro = 30;
        double porcentajeVida = player.vida()/100;
        if(porcentajeVida < 0) porcentajeVida = 0;
        double anchoVida = anchoMax * porcentajeVida;
        String cooldown = player.cooldown()/1000 + "s";


        //fondo de la barra de vida
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.filledRectangle(xCentro, margenInferior, anchoMax / 2, alto / 2);

        // Cambia el color de la barra de vida según el porcentaje de vida
        if(porcentajeVida > 0.6){
            StdDraw.setPenColor(StdDraw.GREEN);
        }else if(porcentajeVida > 0.2){
            StdDraw.setPenColor(StdDraw.ORANGE);
        }else{
            StdDraw.setPenColor(StdDraw.RED);
        }
        // Dibuja la barra de vida
        StdDraw.filledRectangle(xCentro - (anchoMax - anchoVida) / 2, margenInferior, anchoVida / 2, alto / 2);

        // Dibuja el borde de la barra de vida
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.rectangle(xCentro, margenInferior, anchoMax / 2, alto / 2);
        
        //Dibuja el texto del cooldown
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(xCentro + anchoMax, margenInferior, cooldown);
    }

    /**
     * Método que se ejecuta en cada iteración del bucle del juego.
     * Comprueba las colisiones y mueve los objetos.
     */
    protected void comprobarColisiones(){
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            if (player.hayColision(enemigo)) {
                player.recibirImpacto(enemigo);
            }

            // Proyectiles del enemigo
            for (int j = enemigo.getProyectiles().size() - 1; j >= 0; j--) {
                ObjetoGraficoMovil proyectil = enemigo.getProyectiles().get(j);
                if (player.hayColision(proyectil)) {
                    player.recibirImpacto(proyectil);
                    enemigo.getProyectiles().remove(j);
                }
            }
        }

        // El jugador ataca a los enemigos
        player.atacar(enemigos);
    }

    /**
     * Método que mueve a los enemigos a la siguiente posición 
     * y el jugador según las teclas presionadas.
     */
    protected void moverObjetos(){
        player.sumarVida(1);
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

    /**
     * Método que muestra una animación simple cuando un enemigo es eliminado.
     * Para brevemente el juego para pintar una imagen de muerte que aumenta
     * en el tiempo.
     * 
     * @param enemigo El enemigo que ha sido eliminado.
     */
    public void animacionEnemigoMuerto(Enemigo enemigo) {
        if(enemigo == null) return;//caso extremo
        for (int i = 0; i < 8; i++) {
            StdDraw.picture(enemigo.getFigura().getCentroide().getX(), enemigo.getFigura().getCentroide().getY(), "src/resources/muerte.png", 5 + i, 5 + i);
            StdDraw.show();
            StdDraw.pause(50);
        }
    }
}
