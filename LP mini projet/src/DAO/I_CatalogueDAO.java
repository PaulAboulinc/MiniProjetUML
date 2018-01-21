package DAO;

import java.util.List;

public interface I_CatalogueDAO {

	public abstract boolean createCatalogue(String nomCatalogue);
	public abstract List<String> findAllCatalogue();
	public abstract boolean deleteCatalogue(String nomCatalogue);
	public abstract int getNombreProduitsParCatalogue(String nomCatalogue);
	public abstract int getNombreCatalogues();
	public abstract boolean findCatalogueByName(String nomCatalogue);

}
