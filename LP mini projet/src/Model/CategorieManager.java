package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import DAO.I_CatalogueDAOFactory;
import DAO.I_CategorieDAO;

public class CategorieManager implements I_CategorieManager{

private I_CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = I_CatalogueDAOFactory.getInstance().createCategorieDAO();
	}
	
	@Override
	public boolean ajouterCategorie(I_Categorie categorie) {
		String nomCat = categorie.getNom().trim();
		if (Arrays.asList(getNomCategories()).contains(nomCat) || nomCat.isEmpty()) {
			return false;
		}	
		String nom = nomCat;
		nom = nom.replace("\t", " ");
		I_Categorie cat = CatalogueFactory.createCategorie(nom, categorie.getTauxTVA());
		return categorieDAO.createCategorie(cat);
	}

	@Override
	public boolean supprimerCategorie(String nomCategorie) {
		if (Arrays.asList(getNomCategories()).contains(nomCategorie)) {
			return categorieDAO.deleteCategorie(nomCategorie);
		}
		return false;
	}

	@Override
	public String[] getNomCategories() {
		List<I_Categorie> listCategories = categorieDAO.findAllCategorie();
		String[] noms = new String[listCategories.size()];
		for (int i=0; i<listCategories.size(); i++) {
			noms[i] = listCategories.get(i).getNom();
		}
		Collections.sort(Arrays.asList(noms));
		return noms;	
	}

	@Override
	public void clear() {
		List<I_Categorie> listCategories = categorieDAO.findAllCategorie();
		for (I_Categorie i_categorie : listCategories) {
			categorieDAO.deleteCategorie(i_categorie.getNom());
		}
	}

	@Override
	public boolean isCategorieEmpty(String nomCategorie) {
		if (categorieDAO.getNombreProduitsParCategorie(nomCategorie)==0) {
			return true;
		}
		return false;
	}

}
