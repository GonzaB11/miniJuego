package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class DisparoDeFuego {
	double x,y;
	int direccion, velocidad;
	Image fuego,explosion;
	
	public DisparoDeFuego(double x, double y, int direccion) {
		this.x = x;
		this.y = y;
		this.velocidad = 4;
		this.direccion = direccion;
		this.explosion= Herramientas.cargarImagen("explosion.png");
		this.fuego= Herramientas.cargarImagen("fuego.png");
		}
	
	public void dibujarse(Entorno entorno, double x, double y, int direccion){
		entorno.dibujarImagen(fuego, this.x, this.y, 0, 0.1);
	}
	
	public void dibujarExplosion(Entorno entorno, double x, double y) {
		entorno.dibujarImagen(explosion, x, y, 0, 0.5);
		
}

	public void mover() {
		if (direccion == 0) {
			y+=velocidad; // Mueve hacia arriba
		}
		if (direccion == 1) {
			x+=velocidad; // Mueve hacia la derecha
		}
		if (direccion == 2) {
			y-=velocidad; // Mueve hacia abajo
		}
		if (direccion == 3) {
			x-=velocidad; // Mueve hacia la izquierda
		}
	}
}