package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
	Intervention cm, td, tp, inter_java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");
		cm = new Intervention(new Date(), 10, 2, TypeIntervention.CM, uml, new Salle("101", 30), untel );	
		td = new Intervention(new Date(), 10, 3, TypeIntervention.TD, uml, new Salle("101", 30), untel );
		tp = new Intervention(new Date(), 10, 4, TypeIntervention.TP, uml , new Salle("101", 30), untel);
		inter_java = new Intervention(new Date(), 10, 4 , TypeIntervention.TP, java ,new Salle("101", 30), untel);
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
	
	@Test
	public void testSousService() {
		assertEquals(true, untel.enSousService());
	}
	
	
	@Test
	public void testHeuresPrevu(){
		untel.ajouteEnseignement(uml, 0, 50, 0);
		untel.ajouteEnseignement(java, 0, 50, 0);
		assertEquals(100, untel.heuresPrevues());
	}
	
	@Test
	public void testResteAPlaniffiee(){
		untel.ajouteEnseignement(uml, 0, 20, 0);
		
		//untel.ajouterIntervention( new Intervention(new Date(), 10, 2, TypeIntervention.CM, uml, new Salle("101", 30), untel ));
		untel.ajouterIntervention( new Intervention(new Date(), 11, 10, TypeIntervention.TD, uml, new Salle("101", 30), untel ));
		//untel.ajouterIntervention( new Intervention(new Date(), 10, 4, TypeIntervention.TP, uml , new Salle("101", 30), untel));
		
		//assertEquals(18, untel.resteAPlanifier(uml, TypeIntervention.CM));
		assertEquals(9, untel.resteAPlanifier(uml, TypeIntervention.TD));
		//assertEquals(16, untel.resteAPlanifier(uml, TypeIntervention.TP));
		try {
			untel.resteAPlanifier(java, TypeIntervention.CM);
			fail("Cet appel doit lever une exception");
		} catch (Exception e) {
			
		}
	}
	
}
