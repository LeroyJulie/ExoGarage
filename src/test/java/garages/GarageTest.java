package garages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GarageTest  {
	private Garage g1, g2;
	private Voiture v1;
		
	@Before
	public void setUp() throws Exception {
		v1 = new Voiture("123 XX 456");
		g1 = new Garage("ISIS Castres");
		g2 = new Garage("Universite Champollion Albi");
	}
	
	@Test
	public void testInitialisationVoiture() {
		
		assertFalse("Une voiture neuve ne doit pas être dans un garage",
			v1.estDansUnGarage());		
		assertTrue("L'ensemble des garages visités par une voiture neuve doit être vide",
			v1.garagesVisites().isEmpty());
	}
	
	@Test
	public void testEntreeGarage() throws Exception {
		
		v1.entreAuGarage(g1);
		
		assertTrue("L'entrée au garage n'a pas fonctionné",
			v1.estDansUnGarage());
		
		assertTrue( "Le nouveau garage visité n'est pas correctement enregistré",
			v1.garagesVisites().contains(g1));	
	}
	
	@Test
	public void testSortieGarage() throws Exception {
		v1.entreAuGarage(g1);
		v1.sortDuGarage();
		
		assertFalse("La sortie au garage n'a pas fonctionné",
			v1.estDansUnGarage());
		
		
	}
	
	@Test
	public void testDoubleSortie() throws Exception {
		v1.entreAuGarage(g1);
		v1.sortDuGarage(); 
               
		try {
			v1.sortDuGarage(); 
			fail("On ne peut pas sortir d'un garage deux fois");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testDoubleEntree() throws Exception {
		v1.entreAuGarage(g1);
		try {
			v1.entreAuGarage(g2); 
			fail("On ne peut pas entrer dans un garage deux fois");
		} catch (Exception e) {
			
		}
	}

	
	@Test
	public void testCorrectPrintFormat() throws Exception {
		v1.entreAuGarage(g1);
		v1.sortDuGarage();
		v1.entreAuGarage(g2);
		v1.sortDuGarage();
		v1.entreAuGarage(g1);

		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		
		v1.imprimeStationnements(ps);
		
		String output = os.toString("UTF8");

		assertEquals( g1.toString() + " doit apparaître une fois",
			1,
			countSubstring(output, g1.toString())
		);

		assertEquals( g2.toString() + " doit apparaître une fois",
			1,
			countSubstring(output, g2.toString())
		);
		
		assertEquals("On doit imprimer trois stationnements",
			3,
			countSubstring(output, "Stationnement")
		);

	
	}
}
