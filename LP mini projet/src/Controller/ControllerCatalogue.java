package Controller;

import Model.I_Catalogue;
import Model.I_CatalogueManager;
import Model.CatalogueFactory;
import Vue.FenetreAffichage;
import Vue.FenetrePrincipale;

public class ControllerCatalogue {
	
	private I_CatalogueManager catalogueManager;
	
	public ControllerCatalogue () {
		catalogueManager = CatalogueFactory.createCatalogueManager();
	}
	
	public boolean ajouterCatalogue (String nomCatalogue) {
		if (nomCatalogue.trim().isEmpty()) {
			new FenetreAffichage("Le nom du catalogue ne peut pas être vide !");
			return false;
		}
		return catalogueManager.ajouterCatalogue(nomCatalogue);
	}
	
	public boolean supprimerCatalogue (String nomCatalogue) {
		return catalogueManager.supprimerCatalogue(nomCatalogue);
	}
	
	public boolean selectionnerCatalogue (String nomCatalogue) {
		if (catalogueManager.isCatalogueExist(nomCatalogue)) {			
			I_Catalogue catalogue = catalogueManager.selectionnerCatalogue(nomCatalogue);
			ControllerProduit cProduit = new ControllerProduit(catalogue);
			ControllerAchatVente cAchatVente = new ControllerAchatVente(catalogue);
			ControllerStock cStock = new ControllerStock(catalogue);
			ControllerCategorie cCategorie = new ControllerCategorie();
			new FenetrePrincipale(cCategorie,cProduit,cAchatVente,cStock);
			return true;
		}
		return false;
	}
	
	public String[] getNomCataloguesEtNombreProduits () {
		return catalogueManager.getNomCataloguesEtNombreProduits();
	}
	
	public int getNombreCatalogues() {
		return catalogueManager.getNombreCatalogues();
	}
	
	public String[] getNomCatalogues() {
		return catalogueManager.getNomCatalogues();		
	}
}
