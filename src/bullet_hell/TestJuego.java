package bullet_hell;

import javax.swing.JOptionPane;

/**
 * Clase principal para iniciar el juego Bullet Hell.
 * Permite al usuario jugar múltiples veces y finaliza al elegir no volver a jugar.
 */
public class TestJuego {
 public static void main(String[] args) {
    boolean volveraJugar=true;
    while(volveraJugar){
      BulletHellGame j = new BulletHellGame(2);
      j.jugar();
    int respuesta = JOptionPane.showConfirmDialog(null,"¿Quieres volver a jugar?","¡Hasta pronto!",JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.NO_OPTION) {
      volveraJugar = false;
      }
    }  
    System.exit(0);
  }
}
