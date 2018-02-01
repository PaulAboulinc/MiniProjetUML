package Model;

public class CatalogueFactory {
	
	public static I_Produit createProduit (String nom, double prix, int quantite, int idCategorie, float tauxTVA) {
		return new Produit(nom, prix, quantite, idCategorie, tauxTVA);
	}
	
	public static I_Catalogue createCatalogue (String nom) {
		return new Catalogue(nom);
	}
	
	public static I_CatalogueManager createCatalogueManager () {
		return new CatalogueManager();
	}
	
	public static I_Categorie createCategorie (String nom, float tauxTVA) {
		return new Categorie(nom, tauxTVA);
	}
	
	public static I_CategorieManager createCategorieManager () {
		return new CategorieManager();
	}
}
