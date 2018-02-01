package Model;

public interface I_CategorieManager {

	public abstract boolean ajouterCategorie (I_Categorie categorie);
	public abstract boolean supprimerCategorie (String nomCategorie);
	public abstract boolean isCategorieEmpty(String nomCategorie);
	public abstract String[] getNomCategories();
	public abstract void clear();
	
}
