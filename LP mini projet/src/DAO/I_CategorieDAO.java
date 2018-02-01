package DAO;

import java.util.List;

import Model.I_Categorie;

public interface I_CategorieDAO {
	
	public abstract boolean createCategorie(I_Categorie c);
	public abstract List<I_Categorie> findAllCategorie();
	public abstract boolean deleteCategorie(String nomCategorie);
	public abstract int getNombreProduitsParCategorie(String nomCategorie);
	public abstract boolean findCategorieByName(String nomCategorie);
	public abstract int getIdCategorieByName(String nomCategorie);
	public abstract float getTauxTVAById(int idCategorie);
	
}
