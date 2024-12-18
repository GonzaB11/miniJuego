package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Autos {
	double x;
	double y;
	int direccion;
	Image [] img;
	
	
	public Autos (double x, double y,int direccion) {
		this.x = x;
		this.y = y;
		this.direccion = direccion;
		this.img = new Image [4];
		for(int i=0 ; i<img.length; i++) {
			img [i]= Herramientas.cargarImagen("auto"+i+".png");
		}
	}
	
	public void dibujarse(Entorno entorno){
		    Image imagenAuto = null;
		    if (direccion == 0) {
		        imagenAuto = img[1]; // Imagen para la dirección arriba
		    } else if (direccion == 1) {
		        imagenAuto = img[2]; // Imagen para la dirección derecha
		    } else if (direccion == 2) {
		        imagenAuto = img[0]; // Imagen para la dirección abajo
		    } else if (direccion == 3) {
		        imagenAuto = img[3]; // Imagen para la dirección izquierda
		    }

		    if (imagenAuto != null) {
		        entorno.dibujarImagen(imagenAuto, this.x, this.y, 0, 0.1);
		    }
		}

	public void moverAuto() {

		if (direccion == 0) {
	        y+=3; // Mueve hacia arriba
	    } else if (direccion == 1) {
	        x+=3; // Mueve hacia la derecha
	    } else if (direccion == 2) {
	        y-=3; // Mueve hacia abajo
	    } else if (direccion == 3) {
	        x-=3; // Mueve hacia la izquierda
	    }
		if(this.x < -20) {
			x=850;
		}
		
		if(this.x > 850) {
			x = -20;
		}
		
		if(this.y > 650) {
			y=-20;
		}
		
		if(this.y < -20) {
			y = 650;
		}	
	}
}

