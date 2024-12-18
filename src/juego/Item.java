package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Item {

	Image comida;
	double x, y;
	
	public Item(double x, double y) {
		
		this.x = x;
		this.y = y;
		this.comida = Herramientas.cargarImagen("item.png");
	}
	
	public void dibujarComida(Entorno entorno) {
		entorno.dibujarImagen(comida, x, y, 0, 0.08);
		}
}
