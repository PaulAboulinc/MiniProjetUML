package Model;

public class CatalogueFactory {
	
	public static I_Produit createProduit (String nom, double prix, int quantite) {
		return new Produit(nom, prix, quantite);
	}
	
	public static I_Catalogue createCatalogue() {
		return new Catalogue();
	}
}
