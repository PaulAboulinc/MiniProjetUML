package DAO;

import java.util.List;

import Model.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean createProduit (I_Produit p);
	public abstract List<I_Produit> findAllProduit ();
	public abstract I_Produit findByNameProduit (String nom);
	public abstract boolean updateQuantite (I_Produit p);
	public abstract boolean deleteProduit (I_Produit p);
	
}