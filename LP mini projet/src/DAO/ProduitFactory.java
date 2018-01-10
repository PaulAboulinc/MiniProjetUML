package DAO;

public class ProduitFactory {
	
	public ProduitDAO createProduit () {
		return new ProduitDAO();
	}
}
