package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class JefeFinal {
	double x;
	double y;
	int direccion, velocidad;
	Image [] img;
	Image bom;
	
	
	public JefeFinal (double x, double y,int direccion) {
		this.x = x;
		this.y = y;
		this.velocidad=2;
		this.direccion = direccion;
		this.img = new Image [4];		
		for(int i=0 ; i<img.length; i++) {
			img [i]= Herramientas.cargarImagen("plantaGrande"+i+".png");
		}
	}
	
	public void dibujarse(Entorno entorno){
		    Image imagenPlanta = null;

		    if (direccion == 0) {
		        imagenPlanta = img[2]; // Imagen para la dirección arriba
		    } else if (direccion == 1) {
		        imagenPlanta = img[1]; // Imagen para la dirección derecha
		    } else if (direccion == 2) {
		        imagenPlanta = img[0]; // Imagen para la dirección abajo
		    } else if (direccion == 3) {
		        imagenPlanta = img[3]; // Imagen para la dirección izquierda
		    }

		    if (imagenPlanta != null) {
		        entorno.dibujarImagen(imagenPlanta, this.x, this.y, 0, 1);
		    }
		}

	public void moverPlanta() {
		if (direccion == 0) {
	        y+=velocidad; // Mueve hacia abajo
	    } else if (direccion == 1) {
	        x+=velocidad; // Mueve hacia la derecha
	    } else if (direccion == 2) {
	        y-=velocidad; // Mueve hacia arriba
	    } else if (direccion == 3) {
	        x-=velocidad; // Mueve hacia la izquierda
	    }
		if(this.x < 0) {
			direccion = 1;
		}
		
		if(this.x > 800) {
			direccion = 3;
		}
		
		if(this.y > 600) {
			direccion = 2;
		}
		
		if(this.y < 0) {
			direccion = 0;
		}	
	}
}
