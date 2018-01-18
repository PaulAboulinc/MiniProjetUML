package DAO;

public class ProduitDAOFactory {
	
	public static I_ProduitDAO createProduit () {
		return new ProduitDAO();
		//return new AdaptateurProduitDAO_XML();
	}
}
