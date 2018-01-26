package Model;

public interface I_CatalogueManager {

	public abstract boolean ajouterCatalogue (String nomCatalogue);
	public abstract boolean supprimerCatalogue (String nomCatalogue);
	public abstract I_Catalogue selectionnerCatalogue (String nomCatalogue);
	public abstract int getNombreCatalogues();
	public abstract String[] getNomCatalogues();
	public abstract boolean isCatalogueExist(String nomCatalogue);
	public abstract String[] getNomCataloguesEtNombreProduits ();	
	
}
