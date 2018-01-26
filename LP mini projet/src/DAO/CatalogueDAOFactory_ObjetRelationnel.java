package DAO;

public class CatalogueDAOFactory_ObjetRelationnel extends I_CatalogueDAOFactory {
	
	@Override
	public I_ProduitDAO createProduitDAO(String nomCatalogue) {
		return new ProduitDAO_ObjetRelationnel(nomCatalogue);
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_ObjetRelationnel();
	}
}
