//package Test;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//import java.util.Arrays;
//import Model.CatalogueFactory;
//import Model.I_Catalogue;
//import Model.I_CatalogueManager;
//
//public class CatalogueManagerTest {
//
//	I_CatalogueManager cat;
//	
//	@Before
//	public void setUp() {
//		cat = CatalogueFactory.createCatalogueManager();
//		cat.clear();
//	}
//	
//	
//	@Test
//	public void testConstructeurCatalogue() {
//		assertNotNull("catalogueManagerPasNull", cat);
//	}
//	
//	@Test
//	public void testAjouterUnCatalogue(){
//		I_Catalogue catalogue = CatalogueFactory.createCatalogue("test");
//		assertEquals(cat.ajouterCatalogue(catalogue.getNom()),true);
//	}
//	
//	@Test
//	public void testAjouterDeuxCataloguesAvecLeMemeNom(){
//		I_Catalogue catalogue1 = CatalogueFactory.createCatalogue("test");
//		I_Catalogue catalogue2 = CatalogueFactory.createCatalogue("test");
//		cat.ajouterCatalogue(catalogue1.getNom());
//		assertEquals(cat.ajouterCatalogue(catalogue2.getNom()),false);
//	}
//	
//	@Test
//	public void testSupprimerUnCatalogue(){
//		I_Catalogue catalogue = CatalogueFactory.createCatalogue("test");
//		cat.ajouterCatalogue(catalogue.getNom());
//		assertEquals(cat.supprimerCatalogue(catalogue.getNom()),true);
//	}
//	
//	@Test
//	public void testSupprimerUnCatalogueQuiExistePas(){
//		assertEquals(cat.supprimerCatalogue("Jean Michel"),false);
//	}
//	
//	@Test
//	public void testSupprimerUnCatalogueDeuxFois(){
//		I_Catalogue catalogue1 = CatalogueFactory.createCatalogue("test");
//		cat.ajouterCatalogue(catalogue1.getNom());
//		cat.supprimerCatalogue(catalogue1.getNom());
//		assertEquals(cat.supprimerCatalogue(catalogue1.getNom()),false);
//	}
//	
//	@Test
//	public void testRetourZeroCatalogue(){
//		assertEquals(cat.getNombreCatalogues(),0);
//	}
//	
//	@Test
//	public void testRetourPlusieursCatalogue(){
//		I_Catalogue catalogue1 = CatalogueFactory.createCatalogue("test1");
//		I_Catalogue catalogue2 = CatalogueFactory.createCatalogue("test2");
//		cat.ajouterCatalogue(catalogue1.getNom());
//		cat.ajouterCatalogue(catalogue2.getNom());
//		assertEquals(cat.getNombreCatalogues(),2);
//	}
//	
//	@Test
//	public void testRetourUnNomCatalogue(){
//		I_Catalogue catalogue = CatalogueFactory.createCatalogue("test");
//		cat.ajouterCatalogue(catalogue.getNom());
//		assertEquals(Arrays.asList(cat.getNomCatalogues()),Arrays.asList(catalogue.getNom()));
//	}
//	
//	@Test
//	public void testRetourZeroNomCatalogue(){
//		assertEquals(Arrays.asList(cat.getNomCatalogues()),Arrays.asList());
//	}
//	
//	@Test
//	public void testRetourDeuxNomsCatalogues(){
//		I_Catalogue catalogue1 = CatalogueFactory.createCatalogue("test1");
//		I_Catalogue catalogue2 = CatalogueFactory.createCatalogue("test2");
//		cat.ajouterCatalogue(catalogue1.getNom());
//		cat.ajouterCatalogue(catalogue2.getNom());
//		assertEquals(Arrays.asList(cat.getNomCatalogues()),Arrays.asList(catalogue1.getNom(),catalogue2.getNom()));
//	}
//	
//	@Test
//	public void testCatalogueExiste(){
//		I_Catalogue catalogue = CatalogueFactory.createCatalogue("test");
//		cat.ajouterCatalogue(catalogue.getNom());
//		assertEquals(cat.isCatalogueExist(catalogue.getNom()),true);
//	}
//
//	@Test
//	public void testCatalogueExistePas(){
//		assertEquals(cat.isCatalogueExist("Jean"),false);
//	}
//	
//	@Test
//	public void testRetourListeDeUnCatalogueEtSesProduits(){
//		cat.ajouterCatalogue("test");
//		I_Catalogue catalogue = cat.selectionnerCatalogue("test");
//		catalogue.addProduit("salut", 10.0, 2);
//		catalogue.addProduit("Bonjour", 10.0, 2);
//		assertEquals(Arrays.asList(cat.getNomCataloguesEtNombreProduits()),Arrays.asList(catalogue.getNom() + " : 2 produits"));
//	}
//	
//	@Test
//	public void testRetourListeDeZeroCatalogueEtDeSesZeroProduits(){
//		assertEquals(Arrays.asList(cat.getNomCataloguesEtNombreProduits()),Arrays.asList());
//	}
//	
//	@Test
//	public void testRetourListeDeDeuxCataloguesEtLeursProduits(){
//		cat.ajouterCatalogue("test");
//		cat.ajouterCatalogue("test2");
//		I_Catalogue catalogue = cat.selectionnerCatalogue("test");
//		I_Catalogue catalogue2 = cat.selectionnerCatalogue("test2");
//		catalogue.addProduit("salut", 10.0, 2);
//		catalogue.addProduit("Bonjour", 10.0, 2);
//		catalogue2.addProduit("Hello", 20.0, 2);
//		assertEquals(Arrays.asList(cat.getNomCataloguesEtNombreProduits()),Arrays.asList(catalogue.getNom()+" : 2 produits",catalogue2.getNom() + " : 1 produits"));
//	}
//	
//	@Test
//	public void testClear(){
//		cat.ajouterCatalogue("test");
//		cat.ajouterCatalogue("test2");
//		I_Catalogue catalogue = cat.selectionnerCatalogue("test");
//		I_Catalogue catalogue2 = cat.selectionnerCatalogue("test2");
//		catalogue.addProduit("salut", 10.0, 2);
//		catalogue.addProduit("Bonjour", 10.0, 2);
//		catalogue2.addProduit("Hello", 20.0, 2);
//		cat.clear();
//		assertEquals(cat.getNombreCatalogues(),0);
//		
//	}
//	
//	
//}