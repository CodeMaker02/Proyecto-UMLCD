package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Clases.Abstracta;
import Clases.Clase;
import Clases.Concreta;
import Clases.Diagrama;

public class DiagramaTestCase {
 private Diagrama diagrama;
	@Before
	public void setUp() throws Exception {
		diagrama = Diagrama.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		diagrama = null;
	}
	//Prueba caja blanca Buscar M�todo
	@Test
	public void testBuscarClaseCaminoBasico1() throws Exception {
		diagrama.addClase(new Abstracta("Animal",0,0));
		diagrama.addClase(new Concreta("Perro",0,0));
		diagrama.addClase(new Concreta("Le�n",0,0));
		
		assertEquals(null,diagrama.buscarClase("Robot") );
		
	}

	@Test
	public void testBuscarClaseCaminoBasico2() throws Exception {	
		assertEquals(null,diagrama.buscarClase("Robot") );
		
	}
	
	@Test
	public void testBuscarClaseCaminoBasico3() throws Exception {
		diagrama.addClase(new Abstracta("Animal",0,0));
		diagrama.addClase(new Concreta("Perro",0,0));
		diagrama.addClase(new Concreta("Le�n",0,0));
		
		assertEquals(this.diagrama.getClases().get(1),diagrama.buscarClase("Perro") );
		
	}
	
	@Test
	public void testBuscarClaseBucle1() throws Exception {
		diagrama.addClase(new Abstracta("Animal",0,0));
		diagrama.addClase(new Concreta("Perro",0,0));
		diagrama.addClase(new Concreta("Le�n",0,0));
		
		assertEquals(this.diagrama.getClases().get(0),diagrama.buscarClase("Animal") );
		
	}
	
	@Test
	public void testBuscarClaseBucle2() throws Exception {
		diagrama.addClase(new Abstracta("Animal",0,0));
		diagrama.addClase(new Concreta("Perro",0,0));
		diagrama.addClase(new Concreta("Le�n",0,0));
		assertEquals(this.diagrama.getClases().get(2),diagrama.buscarClase("Le�n") );
		
	}
	
	//Fin de prueba
	
	//Pruebas Caja Negra 
	@Test
	public void testAgregarClaseEscenario1()  {
		boolean veredicto = true;
		String valorReal = null;
		try {
			this.diagrama.addClase(new Abstracta("Animal",0,0));
		} catch (Exception e) {
			veredicto = false;
			
		}
		
		if(veredicto)
			valorReal = "Clase a�adida exitosamente";
		
		assertTrue("Clase a�adida exitosamente".equals(valorReal));
		
	}
	
	@Test
	public void testAgregarClaseEscenario2() throws Exception  {
		this.diagrama.addClase(new Abstracta("Animal",0,0));
		boolean veredicto = true;
		String valorReal = null;
		try {
			this.diagrama.addClase(new Abstracta("Animal",0,0));
		} catch (Exception e) {
			veredicto = false;
			valorReal = "Ya existe una clase con el mismo nombre";
		}
		
		if(veredicto)
			valorReal = "Clase a�adida exitosamente";
		
		assertTrue("Ya existe una clase con el mismo nombre".equals(valorReal));
		
	}
	
	@Test
	public void testEliminarClaseEscenario1() throws Exception  {
		this.diagrama.addClase(new Concreta("Perro",0,0));
			
		this.diagrama.eliminarClase("Perro");
			
		assertTrue(true);
		
	}
	
	
	

}
