package DAO;

public class ProduitDAOFactory {
	
	public static I_ProduitDAO createProduit (String nomCatalogue) {
		return new ProduitDAO(nomCatalogue);
		//return new AdaptateurProduitDAO_XML();
	}
}
