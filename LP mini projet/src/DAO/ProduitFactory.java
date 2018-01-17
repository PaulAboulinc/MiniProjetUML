package DAO;

public class ProduitFactory {
	
	public I_ProduitDAO createProduit () {
		return new ProduitDAO();
		//return new AdaptateurProduitDAO_XML();
	}
}
