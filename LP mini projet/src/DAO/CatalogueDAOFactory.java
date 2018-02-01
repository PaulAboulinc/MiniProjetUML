package DAO;

public class CatalogueDAOFactory extends I_CatalogueDAOFactory {
	
	@Override
	public I_ProduitDAO createProduitDAO(String nomCatalogue) {
		return new ProduitDAO(nomCatalogue);
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO();
	}

	@Override
	public I_CategorieDAO createCategorieDAO() {
		return new CategorieDAO();
	}
}
