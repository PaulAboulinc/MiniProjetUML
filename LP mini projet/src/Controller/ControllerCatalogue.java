package Controller;

import Model.Catalogue;
import Model.I_Catalogue;
import Model.CatalogueManager;
import Vue.FenetrePrincipale;

public class ControllerCatalogue {
	
	private CatalogueManager catalogueManager;
	
	public ControllerCatalogue () {
		catalogueManager = new CatalogueManager();
	}
	
	public boolean ajouterCatalogue (String nomCatalogue) {
		return catalogueManager.ajouterCatalogue(nomCatalogue);
	}
	
	public boolean supprimerCatalogue (String nomCatalogue) {
		return catalogueManager.supprimerCatalogue(nomCatalogue);
	}
	
	public boolean selectionnerCatalogue (String nomCatalogue) {
		if (catalogueManager.isCatalogueExist(nomCatalogue)) {			
			I_Catalogue catalogue = new Catalogue(nomCatalogue); //a changer par catalogueManage.getCatalogue(nomCatalogue)
			ControllerProduit cProduit = new ControllerProduit(catalogue);
			ControllerAchatVente cAchatVente = new ControllerAchatVente(catalogue);
			ControllerStock cStock = new ControllerStock(catalogue);
			new FenetrePrincipale(cProduit,cAchatVente,cStock);
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
