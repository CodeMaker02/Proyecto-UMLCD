package Interfaz;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;



import Clases.Atributo;
import Clases.Clase;
import Clases.Metodo;
import Logica.Operaciones;
import util.Flecha;
import util.LabelAtributo;
import util.Linea;
import util.PanelClase;

public class Lienzo extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Graphics2D g2;
	private transient Thread gameThread;
	private int FPS = 60;
	private ArrayList<Flecha> herencias;
	private ArrayList<Linea> asociaciones;
	private ArrayList<Linea> claseAsociacion;
	//private Point inicio, finale;
	private Flecha aux;
	
	
	
	public Lienzo() {
		
		this.herencias = new ArrayList<Flecha>();
		this.asociaciones = new ArrayList<Linea>();
		this.claseAsociacion = new ArrayList<Linea>();
	}

	public void startGameThread() 
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void paintComponent(Graphics g){
		this.g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 2000, 2000);
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
		pintarFlecha(g2);
		pintarFlechaTemporal(g2);
		//pintarAsociacion(g2);
		// pintarClaseAsociacion(g2);


	}
	public void run() //metodo para llamar a las funciones update y paintComponent
	{
		double drawInterval = 1000000000/FPS ; // 0.016666 segundos 
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) 
		{
			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;

			lastTime = currentTime;

			if(delta >= 1) 
			{
				// 1 UPDATE: Actualiza la informacion 
				update();
				// 2  REPAINT: Dibuja la pantalla con la informacion actualizada 
				repaint(); // Esta es la funcion paintComponent
				delta--;
			}

		}
	}

	public void update(){
		//calcularFlechas();
	}

	public void pintarFlecha(Graphics g){
		if(herencias != null){
			for (Flecha flecha : herencias) {
				g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getLineaP2().x, flecha.getLineaP2().y);
				g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getArista1P().x, flecha.getArista1P().y);
				g.drawLine(flecha.getLineaP1().x, flecha.getLineaP1().y, flecha.getArista2P().x, flecha.getArista2P().y);
				g.drawLine(flecha.getArista1P().x, flecha.getArista1P().y, flecha.getArista2P().x, flecha.getArista2P().y);
			}

		}
	}

	public void pintarFlechaTemporal(Graphics g){
		if(aux != null){

			g.drawLine(aux.getLineaP1().x, aux.getLineaP1().y, aux.getLineaP2().x, aux.getLineaP2().y);
			g.drawLine(aux.getLineaP1().x, aux.getLineaP1().y, aux.getArista1P().x, aux.getArista1P().y);
			g.drawLine(aux.getLineaP1().x, aux.getLineaP1().y, aux.getArista2P().x, aux.getArista2P().y);
			g.drawLine(aux.getArista1P().x, aux.getArista1P().y, aux.getArista2P().x, aux.getArista2P().y);


		}

	}

	public void pintarAsociacion(Graphics g){
		if(asociaciones != null){
			for(Linea l: asociaciones){
				g.drawLine(l.getLineaP1().x, l.getLineaP1().y,l.getLineaP2().x ,l.getLineaP2().y);
			}

		}
	}

	public void pintarClaseAsociacion(Graphics g){
		if(claseAsociacion != null){
			for(Linea l: claseAsociacion){
				g.drawLine(l.getLineaP1().x, l.getLineaP1().y,l.getLineaP2().x ,l.getLineaP2().y);

			}

		}
	}

	public void calcularFlechas(){
		for (Flecha flecha : herencias) {
			flecha.calcularFlecha();
		}
	} 

	public void addAsociacion(Point inicial, Point finale){
		asociaciones.add(new Linea(inicial, finale));
	}

	public void addHerencia(Flecha flecha){
		aux=null;
		herencias.add(flecha);
	}
	public void addFlechaTemporal(Point inicial, Point finale){
		aux = new Flecha(inicial,finale);
	}

	public void anadirClaseAsociacion(Point inicial, Point finale){
		claseAsociacion.add(new Linea(inicial, finale));
	}
	public void actualizarHerencia(){

	}

	//public boolean padreOhijo(String nombre){
	//	boolean x = false;

	//	for(Flecha f: herencias){

	//	}


	//}

	public void buscarRelacionesHerencia(String nombre, Point xy){
		if(herencias.size() != 0){
			for (Flecha f : herencias) {
				if(f.getHijo().equals(nombre)){
					f.setLineaP1(xy);
					f.calcularFlecha();
				}
				else if(f.getPadre().equals(nombre)){
					f.setLineaP2(xy);
					f.calcularFlecha();
				}
			}
		}
	}

	public void eliminarRelaciones(String nombre){
		int i = 0;
		boolean x = false;
		if(herencias.size() != 0){

			while(i<this.herencias.size()){
				x = false;
				if(this.herencias.get(i).getHijo().equals(nombre)||this.herencias.get(i).getPadre().equals(nombre)){
					herencias.remove(i);
					x = true;
				}
				i++;
				if(x){
					i = 0;
				}
			}
		}
	}

	public void cancelarHerencia(){
		aux = null;
	}

	public void modificarFlecha(String clase, String nuevoNombre){

		for(int i = 0;  i < this.herencias.size() ; i++){
			if(this.herencias.get(i).getPadre().equals(clase)){
				this.herencias.get(i).setPadre(nuevoNombre);

			}
			else if (this.herencias.get(i).getHijo().equals(clase)){
				this.herencias.get(i).setHijo(nuevoNombre);

			}
		}
	}

	public void addPanelClase(Clase clase, Principal p){
		PanelClase panelClase = new  PanelClase(p);
		panelClase.setClaseDiagrama(clase);
		panelClase.getLblNombreclase().setText(clase.getNombre());
		this.llenarAtributos(clase.getAtributos(), panelClase);
		this.llenarMetodos(clase.getMetodos(), panelClase);
		panelClase.setBounds(clase.getPosicionX(), clase.getPosicionY(), panelClase.getPreferredSize().width+50, panelClase.getPreferredSize().height+50);
		panelClase.setMidPoint();
		add(panelClase);

	}
	
	

	private void llenarAtributos(ArrayList<Atributo> atributos, PanelClase panelClase ){
		for (Atributo a : atributos) {
			panelClase.getPanelAtributos().add(new LabelAtributo(Operaciones.obtenerAtributoDiagrama(a), panelClase));
		}

	}

	private void llenarMetodos(ArrayList<Metodo> metodos, PanelClase panelClase ){
		for (Metodo m : metodos) {
			panelClase.getPanelMetodos().add(new LabelAtributo(Operaciones.obtenerMetodoDiagrama(m), panelClase));
		}

	}

	public ArrayList<Flecha> getHerencias() {
		return herencias;
	}

	public void setHerencias(ArrayList<Flecha> herencias) {
		this.herencias = herencias;
	}
	
	
}
