package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class mainMenu {
	private Image backgroundImage;

    public mainMenu() {
        this.backgroundImage = Herramientas.cargarImagen("main.png");
    }

    public void mostrarMenu(Entorno entorno) {
        boolean enterPressed = false;

        while (!enterPressed) {
            entorno.dibujarImagen(backgroundImage, entorno.ancho() / 2, entorno.alto() / 2, 0);
            

            if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
                enterPressed = true;
            }
        }
    }
}
