package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property ="type")
@JsonSubTypes({@Type(value = Concreta.class, name = "Concreta"),
	@Type( value = Abstracta.class, name = "Abstracta"),
	@Type(value = Asociacion.class, name ="Asociacion")
})
public abstract class Clase implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected ArrayList<Metodo> metodos;
	protected ArrayList<Atributo> atributos;
	protected String color;
	protected int posicionX;
	protected int posicionY;
	protected int dimensionX;
	protected int dimensionY;



	public Clase(String nombre, int posicionX, int posicionY, int dimensionX, int dimensionY) throws Exception {

		this.setNombre(nombre);
		this.atributos = new ArrayList<Atributo>();
		this.metodos = new ArrayList<Metodo>();
		this.color = "Gris";
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
	}

	public Clase(){}



	public String getColor() {
		return color;
	}



	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public ArrayList<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos(ArrayList<Metodo> metodos) {
		this.metodos = metodos;
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<Atributo> atributos) {
		this.atributos = atributos;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}



	public void setColor(String color) {
		this.color = color;
	}


	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) throws Exception {

		if(nombre!=null && !nombre.equals(""))
			this.nombre = nombre;
		else
			throw new Exception("Error");
	}

	
	public void addMetodo(Metodo metodo) throws Exception{
		if(this.validarMetodo(metodo)){
			this.metodos.add(metodo);

		}
		else{
			throw new Exception("No cumple sobreCarga");
		}
	}

	public void addAtributo(Atributo atributo) throws Exception{
		if(validarAtributo(atributo)){
			this.atributos.add(atributo);

		}
		else
			throw new Exception();

	}

	public boolean validarAtributo(Atributo atributo){
		boolean x = true;

		if(atributo==null)
			x= false;
		else{
			for(int i=0;i<this.atributos.size()&&x==true;i++){
				if(atributo.getNombre().equals(this.atributos.get(i).getNombre())) 
					x = false;
			}
		}

		return x;
	}

	
	protected boolean validarMetodo(Metodo metodo){
		boolean x=true;


		for(int i=0; i<this.metodos.size() && x==true;i++){
			if(metodo.getNombre().equals(this.metodos.get(i).getNombre())&&metodo.cantParametros()==this.metodos.get(i).cantParametros()&&
					this.tiposDatosParametros(metodo,this.metodos.get(i))){			
				x=false;			
			}
		}

		return x;

	}

	protected boolean validarMetodo(Metodo metodo, int posicionIgnorar){
		boolean x = true;
		System.out.println(posicionIgnorar);
		for(int i=0; i<this.metodos.size() && x==true;i++){
			if(posicionIgnorar!=i && metodo.getNombre().equals(this.metodos.get(i).getNombre())&&metodo.cantParametros()==this.metodos.get(i).cantParametros()&&
					this.tiposDatosParametros(metodo,this.metodos.get(i)) ){			
				x=false;			
			}
		}

		return x;

	}


	private boolean tiposDatosParametros(Metodo metodo, Metodo a){
		boolean x=true;

		for(int i=0; i<metodo.cantParametros() && x==true;i++){
			if(!metodo.getParametros().get(i).getTipoDato().equals(a.getParametros().get(i).getTipoDato())){

				x=false;			
			}
		}

		return x;
	}

// METODO A IMPLEMENTAR EN LA CLASE DIAGRAMA 
	/*
	public ArrayList<Atributo> ObtenerAtributos(){
		ArrayList<Atributo> atributos = new ArrayList<Atributo>();

		if(this.padre!=null){
			atributos.addAll(this.padre.ObtenerAtributos());
		}
		atributos.addAll(this.atributos);

		return atributos;
	}


	public ArrayList<Metodo> ObtenerMetodos(){
		ArrayList<Metodo> metodos = new ArrayList<Metodo>();

		if(this.padre!=null){
			metodos.addAll(this.padre.ObtenerMetodos());
		}
		metodos.addAll(this.metodos);

		return metodos;
	}

	public int cantAtributos(){
		int cant=0;

		if(this.padre!=null){
			cant+=this.padre.cantAtributos();
		}
		cant+=this.cantAtributosActual();

		return cant;

	}

	public int cantMetodos(){
		int cant=0;

		if(this.padre!=null){
			cant+=this.padre.cantMetodos();
		}
		cant+=this.cantMetodosActual();

		return cant;

	}

	public int totalParametros(){
		int cant = 0;

		if(this.padre!=null){
			cant+=this.padre.totalParametros();
		}
		cant+=this.cantParametrosActual();

		return cant;

	}



	public int cantMetodosAbstractos(){
		int cant=0;

		if(this.padre!=null){
			cant+=this.padre.cantMetodosAbstractos();
		}
		cant+=this.cantMetodosAbstractosActual();

		return cant;

	}

	public int cantMetodosConcretos(){
		int cant=0;

		if(this.padre!=null){
			cant+=this.padre.cantMetodosConcretos();
		}
		cant+=this.cantMetodosConcretosActual();

		return cant;

	}

	public int cantProgenitores(){
		int count = 0;

		if(this.padre!=null){
			count++;
			count += padre.cantProgenitores();
		}

		return count;
	}

	public int cantProgenie(){
		int cant = 0;
		System.out.println(this.nombre+""+this.cantHijos());
		if(this.hijos.size()!=0){
			cant = this.cantHijos();
			for(Clase a: this.hijos){			
				cant += a.cantProgenie();
			}
		}

		System.out.println("");

		return cant;
	}

	public int cantRelaciones(){
		System.out.println( this.nombre+" "+this.cantProgenitores() +" "+ this.cantProgenie());
		return this.cantProgenitores() + this.cantProgenie();	
	}

	public int cantHijos(){
		return this.hijos.size();
	}


	private int  cantParametrosActual(){
		int cant = 0;

		for(Metodo a : this.metodos){
			cant+=a.cantParametros();
		}

		return cant;

	}

	private int cantMetodosAbstractosActual(){
		int cant = 0;

		for(Metodo a : this.metodos){
			if(a.isAbstracto())
				cant++;
		}

		return cant;
	}

	private int cantMetodosConcretosActual(){
		int cant = 0;

		for(Metodo a : this.metodos){
			if(!a.isAbstracto())
				cant++;
		}

		return cant;
	}

	private int cantAtributosActual(){
		return this.atributos.size();
	}

	private int cantMetodosActual(){
		return this.metodos.size();
	}

	

	public void eliminarPadre(){
		this.padre = null;
	}

	public void eliminarHijo(Clase clase){
		this.hijos.remove(clase);
	}

	

	
	public boolean isDescendiente(Clase clase){
		boolean veredicto = false;

		if(this.hijos.size()!=0){

			for(int i = 0; i < this.hijos.size() && veredicto == false; i++){			
				if(this.hijos.get(i).equals(clase)){
					veredicto = true;
				}
				else{
					veredicto = this.hijos.get(i).isDescendiente(clase);
				}
			}

		}


		return veredicto;

	}

	*/
	
	// METODOS PARA COMPROBAR IGUALDAD EN EL SISTEMA DE GUARDADO

	public boolean equals(Clase c){
		boolean verificador = false;

		if(this.equalsNombre(c) && this.equalsAtributos(c) && this.equalsMetodos(c) && this.equalsColor(c))
			verificador = true;

		return verificador;

	}
	
	public void elminarMetodo(Metodo metodo){
		this.metodos.remove(metodo);
	}
	
	public void cambiarMetodosAbstractosAConcretos(){

		for(Metodo a: this.metodos){
			if(a.isAbstracto())
				a.setAbstracto(false);
		}

	}
	
	public void eliminarAtributo(Atributo atributo){
		this.atributos.remove(atributo);
	}
	
	private boolean equalsNombre(Clase c){
		boolean verificador = false;

		if(c.getNombre().equals(this.nombre))
			verificador = true;

		return verificador;

	}

	private boolean equalsMetodos(Clase c){
		boolean verificador = true;
		ArrayList<Metodo> m = c.getMetodos(); 

		if(this.metodos.size() == m.size()){
			for (int i = 0; i < this.metodos.size() && verificador; i++) {
				if(!this.metodos.get(i).equals(m.get(i)))
					verificador = false;	
			}
		}
		else
			verificador = false;

		return verificador;

	}

	private boolean equalsAtributos(Clase c){
		boolean verificador = true;
		ArrayList<Atributo> a = c.getAtributos(); 

		if(this.atributos.size() == a.size()){
			for (int i = 0; i < this.atributos.size() && verificador; i++) {
				if(!this.atributos.get(i).equals(a.get(i)))
					verificador = false;	
			}
		}
		else
			verificador = false;

		return verificador;

	}

	private boolean equalsColor(Clase c){
		boolean verificador = false;

		if(this.color.equalsIgnoreCase(c.getColor()))
			verificador = true;

		return verificador;

	}

	// FIN DE METODOS PARA COMPROBAR IGUALDAD EN EL SISTEMA DE GUARDADO

}
