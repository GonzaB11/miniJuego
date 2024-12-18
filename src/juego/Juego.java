package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	private boolean enMenu = true;
	private int direccion, puntos, eliminadas, vidas, vidaJefe,cantComida;	
	private Image fondo,ganaste,fondoFinal, menuImage;	
	private Autos [] autitos;	
	private Autos[] autitosIniciales;
	private Plantas [] plantitas;
	private Plantas[] plantitasIniciales;
	private JefeFinal plantaGrande;
	private long tiempoRTranscurrido = System.currentTimeMillis();
	private long tiempoEspera = 3000;
	Disparo rayito;
	DisparoDeFuego [] fueguito;
	DisparoDeFuegoJefe fueguitoJefe;
	Layka perrito;	
	Manzana [] manzanita;
	Item[] items;


	public Juego() {
		// Inicializa el objeto entorno
			
		this.entorno = new Entorno(this, "Plantas Invasoras", 800, 600);

		// Inicializar lo que haga falta para el juego
		// ...
		this.menuImage = Herramientas.cargarImagen("main.png");
		this.fondo= Herramientas.cargarImagen("fondo.png");	
		this.ganaste= Herramientas.cargarImagen("ganaste1.jpg");	
		this.fondoFinal= Herramientas.cargarImagen("fondoFinal.jpg");	

		puntos=0;
		eliminadas=0;
		vidas=3;
		vidaJefe = 100;

		autitos= new Autos [4];
		autitos[0] = new Autos(60,75,0);
		autitos [1]= new Autos(745,600,2);
		autitos [2]= new Autos(770,300,3);
		autitos [3]= new Autos(750,510,3); 
		autitosIniciales = new Autos[4];
		for (int i = 0; i < autitos.length; i++) {
			if (autitos[i] != null) {
				autitosIniciales[i] = new Autos(autitos[i].x, autitos[i].y, autitos[i].direccion);
			}
		}

		plantitas = new Plantas[5];
		plantitas [0] = new Plantas(20,20,0);
		plantitas [1] = new Plantas(250,620,2);
		plantitas [2] = new Plantas(0,250,1);
		plantitas [3] = new Plantas(770,575,3);
		plantitas [4] = new Plantas(550,0,0);

		items = new Item[3];
		items[0] = new Item(400,20);
		items[1] = new Item(30,500);
		items[2] = new Item(700,300);

		plantitasIniciales = new Plantas[5];
		for (int i = 0; i < plantitas.length; i++) {
			if (plantitas[i] != null) {
				plantitasIniciales[i] = new Plantas(plantitas[i].x, plantitas[i].y, plantitas[i].direccion);
			}
		}

		plantaGrande = new JefeFinal(600,350,3);

		fueguito= new DisparoDeFuego [plantitas.length];
		fueguito [0] = new DisparoDeFuego (20,20,0);
		fueguito [1] = new DisparoDeFuego (250,620,2);
		fueguito[2] = new DisparoDeFuego (0,250,1);
		fueguito [3] = new DisparoDeFuego (770,575,3);
		fueguito [4] = new DisparoDeFuego (550,0,0);

		fueguitoJefe= new DisparoDeFuegoJefe(plantaGrande.x,plantaGrande.y,plantaGrande.direccion);

		perrito=new Layka(400,550);
		manzanita = new Manzana[6];

		this.entorno.iniciar();
		}
	
	
	public void iniciarJuego() {
	    this.enMenu = false; // Cambiamos enMenu a false para que el juego comience
	}
	
	public int colisionMultiple(Manzana [] m, Layka p) {
		for (int i = 0; i<m.length; i++) {
			if (colision(m[i],p) != 5) {
				return colision(m[i],p);
			}
		}
		return 5;
	}
	public int colision(Manzana m , Layka p) {

	
		double zona1 = m.x - (m.ancho  / 2) ; //coordenada x del borde izquierdo de la manzana.

		double zona3 = m.x + (m.ancho  / 2) ; //coordenada x del borde derecho de la manzana.

		double zona2 = m.y - (m.alto   / 2) ;//coordenada y del borde superior de la manzana.

		double zona0 = m.y + (m.alto   / 2) ;//coordenada y del borde inferior de la manzana.

		if ((p.y > zona2 && p.y < zona0) && (p.x > zona1-30  && p.x < zona3 )) {
			return 1 ;
		}
		if ((p.x > zona1 && p.x < zona3) && (p.y > zona2-30  && p.y < zona0 )) {
			return 2 ;
		}

		if ((p.x > zona1 && p.x < zona3) && (p.y > zona2  && p.y < zona0 + 30 )) {
			return 0 ;
		}

		if ((p.x > zona1 && p.x < zona3 + 30 ) && (p.y > zona2 && p.y < zona0 )) {
			return 3 ;
		}

		return 5;
	}
	public void dibujarManzanas(Manzana[] manzanas) {
		for(int i=0; i<manzanita.length; i++) {
			manzanita[i].dibujarse(this.entorno);
		}
	}
	public void dibujarItem(Item [] items2) {
		for(int i = 0; i<items2.length; i++) {
			if (items2[i] != null) {
				items2[i].dibujarComida(this.entorno);
			}
		}
	}
	public void dibujarLayka(Layka perrito2) {
		if (perrito2 != null) {
			perrito2.dibujarse(entorno);
		} else {
			reiniciarLayka();
		}

	}
	public void dibujarJefe(JefeFinal plantaGrande2) {
		if (plantaGrande2 != null) {
			plantaGrande2.dibujarse(entorno);
		}
	}
	public void dibujarPlantas (Plantas [] plantitas) {
		for (int i=0; i<plantitas.length; i++) {
			if(plantitas[i]!= null) {
				plantitas[i].dibujarse(entorno);
				plantitas[i].moverPlanta();				
			} else {   					
				reiniciarPlanta(i);
			}
		}
	}
	public void dibujarAutos(Autos [] autitos) {
		for (int i=0; i<autitos.length; i++) {
			if(autitos[i]!= null) {
				autitos[i].dibujarse(entorno);
				autitos[i].moverAuto();	
			} else {
				reiniciarAuto(i);
			}
		}
	}
	public void reiniciarLayka() {
		while (perrito == null && vidas > 0) {
			perrito=new Layka(400,550);
		}
	}
	public void reiniciarPlanta(int i) {	
		if (i >= 0 && i < plantitas.length && plantitasIniciales[i] != null) {
			Plantas plantaInicial = plantitasIniciales[i];
			plantitas[i] = new Plantas(plantaInicial.x, plantaInicial.y, plantaInicial.direccion);
			
		}
	}
	public void reiniciarAuto(int i) {
		if (i >= 0 && i < autitos.length && autitosIniciales[i] != null) {
			Autos autoInicial = autitosIniciales[i];
			autitos[i] = new Autos(autoInicial.x, autoInicial.y, autoInicial.direccion);
			tiempoRTranscurrido = System.currentTimeMillis() + tiempoEspera;
		}
	}
	public void disparoAPlanta (Plantas [] p, Disparo rayito2, Layka perrito2) {
		for (int i=0; i< plantitas.length; i++) {
			if  (rayito!= null && plantitas[i]!= null && rayito.x > plantitas[i].x -15 && rayito.x <plantitas[i].x + 15 && rayito.y > plantitas[i].y-15 &&   rayito.y < plantitas[i].y + 15) {
				rayito.dibujarExplosion(entorno, rayito.x, rayito.y);
				plantitas[i]= null;
				rayito= null;
				puntos+=5;
				eliminadas++;
				
			}
		}
	}
	public void disparoAJefe (JefeFinal p, Disparo rayito2, Layka perrito2) {
		if  (rayito!= null && plantaGrande!= null && rayito.x > plantaGrande.x -100 && rayito.x <plantaGrande.x + 100 && rayito.y > plantaGrande.y-100 &&   rayito.y < plantaGrande.y + 100) {
			rayito.dibujarExplosion(entorno, rayito.x, rayito.y);
			vidaJefe-=20;
			rayito= null;
			puntos+=5;
		}
	}
	public void disparoAuto(Disparo rayito2, Autos[] autitos2) {
		for (int i=0; i< autitos.length; i++) {
			if  (rayito!= null && plantitas[i]!= null && autitos[i]!=null && rayito.x > autitos[i].x -7 && rayito.x <autitos[i].x + 7 && rayito.y > autitos[i].y-7 &&   rayito.y < autitos[i].y + 7) {
				rayito.dibujarExplosion(entorno, rayito.x, rayito.y);
				rayito= null;
			}
		}
	}
	public void rayoFueraPantalla(Disparo rayito2) {
		if (rayito.x>800 || rayito.x<0 || rayito.y> 600 || rayito.y<0) {
			rayito= null;
		}
	}
	public void cantDeManzanas() {
		int posXManzana= 150;

		int posYManzana= 150;

		for(int i = 0; i<manzanita.length; i++) {
			manzanita[i] = new Manzana(posXManzana,posYManzana);
			if(posXManzana <= entorno.ancho()-100) {
				posXManzana += 250;
			}
			if(posXManzana > entorno.ancho()-100) {
				posXManzana = 150;
				posYManzana += 250;
			}
		}
	}
	public void colisionConPlantas(Plantas[] plantitas2, Layka perrito2) {
		for (int i=0; i< plantitas.length; i++) {
			if (plantitas[i]!=null && perrito != null &&  perrito.x > plantitas[i].x -22 && perrito.x <plantitas[i].x + 22 && perrito.y > plantitas[i].y-22 &&   perrito.y < plantitas[i].y + 22) {
				perrito = null;
				vidas--;
			}
		}
	}
	public void colisionConJefe(JefeFinal plantaGrande2, Layka perrito2) {
		if (plantaGrande!=null && perrito != null &&  perrito.x > plantaGrande.x -120 && perrito.x <plantaGrande.x + 120 && perrito.y > plantaGrande.y-150 &&   perrito.y < plantaGrande.y + 150) {
			perrito = null;
			vidas--;
		}
	}
	public void colosionConAutos (Autos [] autitos2, Layka perrito2) {		
		for (int i=0; i< autitos.length; i++) {
			if (perrito != null && autitos[i]!=null && perrito.x > autitos[i].x -15 && perrito.x < autitos[i].x + 15 && perrito.y > autitos[i].y-15 &&   perrito.y < autitos[i].y + 15) {
				perrito = null;
				vidas--;
			}
		}
	}
	public void disparoFuego (DisparoDeFuego [] disparo2) {
		for (int i=0; i < disparo2.length; i++) {
			if (disparo2[i]!= null) {
				disparo2[i].dibujarse(entorno, plantitas [i].x, plantitas [i].y, plantitas[i].direccion);
				disparo2[i].mover();
				if (disparo2[i].x>860 || disparo2[i].x<-60 || disparo2[i].y> 660 || disparo2[i].y<-60) {
					disparo2[i].x =  plantitas [i].x; // reemplaza con la nueva coordenada X
					disparo2[i].y =  plantitas [i].y; // reemplaza con la nueva coordenada Y
					disparo2[i].direccion= plantitas[i].direccion;
					disparo2[i].dibujarse(entorno, disparo2 [i].x, disparo2 [i].y, disparo2[i].direccion);
					disparo2[i].mover();
				}
			}
		}
	}
	public void disparoFuego2 (DisparoDeFuegoJefe disparo2) {
		if (disparo2!= null) {
			disparo2.dibujarse(entorno, plantaGrande.x, plantaGrande.y, plantaGrande.direccion);
			disparo2.mover();
			if (disparo2.x>860 || disparo2.x<-60 || disparo2.y> 660 || disparo2.y<-60) {
				disparo2.x =  plantaGrande.x; // reemplaza con la nueva coordenada X
				disparo2.y =  plantaGrande.y; // reemplaza con la nueva coordenada Y
				disparo2.direccion= plantaGrande.direccion;
				disparo2.dibujarse(entorno, disparo2.x, disparo2.y, disparo2.direccion);
				disparo2.mover();
			}
		}
	}
	public void colisionFuegoYRayo (DisparoDeFuego [] disparo2) {
		for (int i=0; i < disparo2.length; i++) {
			if(rayito!=null && disparo2[i]!=null && rayito.x+15> disparo2[i].x-15 && rayito.x -15 < disparo2[i].x+15 && rayito.y +15> disparo2[i].y-15 && rayito.y -15 < disparo2[i].y+15) {		
				disparo2[i].x=plantitas [i].x;
				disparo2[i].y=plantitas [i].y;
				disparo2[i].direccion=plantitas [i].direccion;
				disparo2[i].dibujarse(entorno, disparo2[i].x, disparo2[i].y, disparo2[i].direccion);				
				rayito.dibujarExplosion(entorno, rayito.x, rayito.y);
				rayito= null;

			}
		}
	}
	public void colisionFuegoYAuto (DisparoDeFuego [] disparo2, Autos [] autitos) {
		for (int i=0; i < disparo2.length; i++) {
			for ( int j=0; j< autitos.length;j++) {
				if (autitos[j]!= null && disparo2[i].x > autitos[j].x -15 && disparo2[i].x < autitos[j].x + 15 && disparo2[i].y > autitos[j].y-15 &&   disparo2[i].y < autitos[j].y + 15) {
					disparo2[i].dibujarExplosion(entorno, disparo2[i].x, disparo2[i].y);
					autitos[j] = null;
				}
			}
		}
	}
	public void colisionFuegoYLayka (DisparoDeFuego [] disparo2, Layka perrito2) {
		for (int i=0; i< disparo2.length; i++) {
			if (perrito != null && perrito.x > disparo2[i].x -22 && perrito.x < disparo2[i].x + 22 && perrito.y > disparo2[i].y-22 &&   perrito.y < disparo2[i].y + 22) {
				perrito = null;		
				vidas--;
			}
		}
	}
	public void colisionFuegoJefeYLayka (DisparoDeFuegoJefe disparo2, Layka perrito2) {
		if (perrito != null && perrito.x > disparo2.x -100 && perrito.x < disparo2.x + 100 && perrito.y > disparo2.y-100 &&   perrito.y < disparo2.y + 100) {
			perrito = null;		
			vidas--;
		}		
	}
	public void colisionLaykaComida(Item[] items2, Layka perrito2) {
		for (int i=0; i< items2.length; i++) {
			if (perrito2 != null && items2[i]!=null && perrito2.x > items2[i].x -15 && perrito2.x < items2[i].x +15 && perrito2.y > items2[i].y-15 &&   perrito2.y < items2[i].y +15) {				
				items2[i] =null;
				cantComida++;
			}
		}
	}
	public int aumentoVelocidadRayo() {
		int cont = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				cont += 1;
			}	
		}
		return cont;
	}
	public void aumentarVelocidad ( Plantas [] plantitas2){
		for (int i=0; i < plantitas.length; i++) {
			tiempoRTranscurrido = System.currentTimeMillis() + tiempoEspera;
			plantitas [i].velocidad+=0.7;
			fueguito[i].velocidad+= 0.7;
		}
	}
	public void cambioCampoBatalla() {
		if (puntos > 20 && perrito!= null  && cantComida==3 ) {

			for (int i = 0; i < items.length; i++) {
				items[i] = null;
			}

			for (int i = 0; i < plantitas.length; i++) {
				plantitas[i] = null;
			}
			for (int i = 0; i < autitos.length; i++) {
				autitos[i] = null;
			}

			dibujarJefe(plantaGrande);
			plantaGrande.moverPlanta();
			perrito.dibujarse(entorno);
			colisionConJefe(plantaGrande,perrito);
			colisionFuegoJefeYLayka (fueguitoJefe, perrito);
			disparoFuego2(fueguitoJefe);
				
			entorno.cambiarFont("Arial", 25, Color.orange);
			entorno.escribirTexto("Vida del jefe final: " + vidaJefe+"%", 250, 580);

			// crea el rayo
			if (perrito != null && entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
				rayito= new Disparo (perrito.x, perrito.y, direccion);
			}
			// movimiento del rayo
			if (perrito != null && rayito!=null) {
				rayito.dibujarse(entorno, perrito.x, perrito.y);
				rayito.mover();
				rayoFueraPantalla(rayito);
				disparoAJefe(plantaGrande, rayito, perrito);
			}
		}
	}
	public void Perdiste() {
		if (vidas == 0) {	
			perrito = null;
			rayito = null;
			entorno.dibujarImagen(fondoFinal, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.cambiarFont("Arial", 100, Color.red);
			entorno.escribirTexto("Perdiste", 200, 300);
		}
	}
	public void Ganaste() {
		if (vidaJefe == 0) {
			perrito = null;
			rayito = null;
			entorno.dibujarImagen(ganaste, entorno.ancho() / 2, entorno.alto() / 2, 0);
			entorno.cambiarFont("Arial", 100, Color.white);
			entorno.escribirTexto("Ganaste", 200, 300);
		}
	}
	/**

	 *Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		
		
		
		if (enMenu) {
	        // Mostrar la imagen del menú
	        entorno.dibujarImagen(menuImage, entorno.ancho() / 2, entorno.alto() / 2, 0);
	        
	        // Detectar si se presiona la tecla "ENTER" para comenzar el juego
	        if (entorno.estaPresionada(entorno.TECLA_ENTER)) {
	            enMenu = false; // Salir del menú
	            // Inicializar el juego 
	            iniciarJuego();
	            
	        }
	    } else {
	     

		    entorno.dibujarImagen(fondo, entorno.ancho()/2, entorno.alto()/2, 0);
			cantDeManzanas();
			dibujarManzanas(manzanita);		
			long tiempoActual= System.currentTimeMillis();
			if (tiempoActual-tiempoRTranscurrido>=tiempoEspera) {
				aumentarVelocidad(plantitas);
				tiempoRTranscurrido=tiempoActual;
			}
			dibujarItem(items);
			dibujarPlantas (plantitas);
			dibujarAutos(autitos);
			dibujarLayka(perrito);
			disparoFuego(fueguito);
			colisionFuegoYRayo(fueguito);

			// crea el rayo
			if (perrito != null && entorno.estaPresionada(entorno.TECLA_ESPACIO)) {
				rayito= new Disparo (perrito.x, perrito.y, direccion);
				rayito.velocidad+= aumentoVelocidadRayo();
			}
			// movimiento del rayo
			if (perrito != null && rayito!=null) {
				rayito.dibujarse(entorno, perrito.x, perrito.y);
				rayito.mover();
				rayoFueraPantalla(rayito);
				disparoAPlanta (plantitas, rayito, perrito);
				disparoAuto (rayito, autitos);
			}

			// movimiento de perro
			if (this.perrito!= null) {
				perrito.dibujarse(this.entorno);

				if (entorno.estaPresionada(entorno.TECLA_DERECHA) && (perrito.x < 780) && (colisionMultiple(manzanita, perrito) !=1)) {
					perrito.mover(1);
					direccion=1;
				}
				if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && (perrito.y > 10) && (colisionMultiple(manzanita, perrito) !=0)) {
					perrito.mover(0);
					direccion=0;

				}	

				if (entorno.estaPresionada(entorno.TECLA_ABAJO) && (perrito.y < 580) && (colisionMultiple(manzanita, perrito) !=2)) {
					perrito.mover(2);
					direccion=2;

				}

				if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && (perrito.x > 20) && (colisionMultiple(manzanita, perrito) !=3)) {
					perrito.mover(3);
					direccion=3;

				}
				colosionConAutos (autitos, perrito);
				colisionConPlantas(plantitas,perrito);
			}

			entorno.cambiarFont("Arial", 18, Color.red);
			entorno.escribirTexto("Vidas: " + vidas, 710, 580);
			entorno.escribirTexto("Puntos: " + puntos, 710, 20);
			entorno.escribirTexto("Plantas eliminadas: " + eliminadas, 10, 20);		
			colisionFuegoYAuto(fueguito, autitos);
			colisionFuegoYLayka (fueguito, perrito);
			colisionLaykaComida(items, perrito);
			cambioCampoBatalla();
			Perdiste();	
			Ganaste();
		}
	    	
	    	
	    	
	    	
}
	
		
		
		// Procesamiento de un instante de tiempo
		// ...	

	
		

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

//Extras
/* Jefe final, items+velocidadRayo, velocidadPlanta, fondos ganar y perder, vida del jefe, vida del perro */
