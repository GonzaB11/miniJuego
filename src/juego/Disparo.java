package juego;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	double x,y;
	int direccion, velocidad;
	Image [] rayo;
	Image explosion;

	
	public Disparo(double x, double y,int direccion) {
		this.x=x;
		this.y=y;
		this.velocidad = 4;
		this.direccion=direccion;
		this.explosion= Herramientas.cargarImagen("explosion.png");
		this.rayo = new Image [4];
		for (int i=0; i < rayo.length ; i++) {
			rayo[i] = Herramientas.cargarImagen("rayoLaser"+i+".png");
		}
	}

	public void dibujarse(Entorno entorno, double x, double y){
		Image imagenRayo = null;

		if (direccion == 0) {
			imagenRayo = rayo[0]; // Imagen para la dirección arriba
		}
		if (direccion == 1) {
			imagenRayo = rayo[1]; // Imagen para la dirección derecha
		} 
		if (direccion == 2) {
			imagenRayo = rayo[2]; // Imagen para la dirección abajo
		}
		if (direccion == 3) {
			imagenRayo = rayo[3]; // Imagen para la dirección izquierda
		}

		if (imagenRayo != null) {
			entorno.dibujarImagen(imagenRayo, this.x, this.y, 0, 0.1);
		}
	}
	
	public void dibujarExplosion(Entorno entorno, double x, double y) {
		entorno.dibujarImagen(explosion, x, y, 0, 0.5);
		
}
	
	
	public void mover() {
		if (direccion == 0) {
			y-=velocidad; // Mueve hacia arriba
		}
		if (direccion == 1) {
			x+=velocidad; // Mueve hacia la derecha
		}
		if (direccion == 2) {
			y+=velocidad; // Mueve hacia abajo
		}
		if (direccion == 3) {
			x-=velocidad; // Mueve hacia la izquierda
		}
	}

}



