package DAO;

public abstract class I_CatalogueDAOFactory {
	
	private static I_CatalogueDAOFactory instance;

	protected I_CatalogueDAOFactory() {}
	
	public synchronized static I_CatalogueDAOFactory getInstance() {
		if (instance == null) {
			instance = new CatalogueDAOFactory_ObjetRelationnel();
			//instance  = new CatalogueDAOFactory();
		}
		return instance;
	}

	public abstract I_ProduitDAO createProduitDAO (String nomCatalogue);
	public abstract I_CatalogueDAO createCatalogueDAO ();

}
