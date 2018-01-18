package Model;

public class ProduitFactory {
	
	public static I_Produit createProduit (String nom, double prix, int quantite) {
		return new Produit(nom, prix, quantite);
	}
}
