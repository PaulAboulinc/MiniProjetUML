package Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Model.CatalogueFactory;
import Model.I_Categorie;
import Model.I_CategorieManager;
import Vue.FenetreAffichage;

public class ControllerCategorie {
	
	private I_CategorieManager categorieManager;
	
	public ControllerCategorie() {
		categorieManager = CatalogueFactory.createCategorieManager();
	}

	public boolean ajouterCategorie(String nom, String taux) {
		float tauxTVA;
		try {
			tauxTVA = Float.parseFloat(taux);
		} catch (NumberFormatException e) {
			new FenetreAffichage("Le tauxTVA n'est pas valide !");
			return false;
		}
		if (nom.trim().isEmpty()) {
			new FenetreAffichage("Le nom ne peut pas être vide !");
			return false;
		}
		
		new FenetreAffichage("La catégorie a été ajoutée.");
		I_Categorie categorie = CatalogueFactory.createCategorie(nom, tauxTVA);
		return categorieManager.ajouterCategorie(categorie);
	}

	public boolean supprimerCategorie(String nomCategorie) {
		if (categorieManager.isCategorieEmpty(nomCategorie)) {
			new FenetreAffichage("La catégorie a été supprimée.");
			return categorieManager.supprimerCategorie(nomCategorie);
		}
		new FenetreAffichage("Vous ne pouvez pas supprimer cette catégorie car elle n'est pas vide !");
		return false;
	}

	public String[] getNomCategories() {
		return categorieManager.getNomCategories();
	}
	
}
