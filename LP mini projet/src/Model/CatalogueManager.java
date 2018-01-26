package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import DAO.I_CatalogueDAO;
import DAO.I_CatalogueDAOFactory;


public class CatalogueManager implements I_CatalogueManager {
	
	private I_CatalogueDAO catalogueDAO;
	
	public CatalogueManager () {
		catalogueDAO = I_CatalogueDAOFactory.getInstance().createCatalogueDAO();
	}

	public boolean ajouterCatalogue (String nomCatalogue) {
		if (Arrays.asList(getNomCatalogues()).contains(nomCatalogue.trim())) {
			return false;
		}	
		String nom = nomCatalogue.trim();
		nom = nom.replace("\t", " ");
		return catalogueDAO.createCatalogue(nom);
	}
	
	public boolean supprimerCatalogue (String nomCatalogue) {
		if (Arrays.asList(getNomCatalogues()).contains(nomCatalogue)) {
			return catalogueDAO.deleteCatalogue(nomCatalogue);
		}
		return false;
	}
	
	public I_Catalogue selectionnerCatalogue (String nomCatalogue) {
		return CatalogueFactory.createCatalogue(nomCatalogue);
	}
		
	public int getNombreCatalogues() {
		return catalogueDAO.getNombreCatalogues();
	}
	
	public String[] getNomCatalogues() {
		List<String> listCatalogues = catalogueDAO.findAllCatalogue();
		String[] noms = new String[listCatalogues.size()];
		for (int i=0; i<listCatalogues.size(); i++) {
			noms[i] = listCatalogues.get(i);
		}
		Collections.sort(Arrays.asList(noms));
		return noms;		
	}
	public boolean isCatalogueExist(String nomCatalogue) {		
		return catalogueDAO.findCatalogueByName(nomCatalogue);
	}
	
	public String[] getNomCataloguesEtNombreProduits () {
		List<String> listNomsCatalogues = catalogueDAO.findAllCatalogue();
		String[] listCatalogueEtNombreProduits = new String[listNomsCatalogues.size()];
		for (int i=0; i<listNomsCatalogues.size(); i++) {
			listCatalogueEtNombreProduits[i] = listNomsCatalogues.get(i)+" : "+catalogueDAO.getNombreProduitsParCatalogue(listNomsCatalogues.get(i))+" produits";
		}
		Collections.sort(Arrays.asList(listCatalogueEtNombreProduits));
		return listCatalogueEtNombreProduits;	
	}
}
