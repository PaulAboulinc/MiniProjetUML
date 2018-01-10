package DAO;

import java.util.List;

import Model.I_Produit;

public interface I_ProduitDAO {

	public abstract boolean createProduit(String nom, Double prix, int quantite);
	public abstract boolean createProduit (I_Produit p);
	public abstract I_Produit findProduitById (int id);
	public abstract List<I_Produit> findAllProduit ();
	public abstract I_Produit findByNameProduit (String nom);
	public abstract void updateProduit (String nom, I_Produit p);
	public abstract void updateQuantite (String nom, int qte);
	public abstract void deleteProduit (int id);
	public abstract void deleteProduit (String nom);
	
}