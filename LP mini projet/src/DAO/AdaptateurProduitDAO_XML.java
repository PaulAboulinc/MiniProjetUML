package DAO;

import java.util.List;

import Model.I_Produit;

public class AdaptateurProduitDAO_XML implements I_ProduitDAO {

	private ProduitDAO_XML pXML;
	
	public AdaptateurProduitDAO_XML () {
		pXML = new ProduitDAO_XML();
	}

	@Override
	public boolean createProduit(I_Produit p) {
		return pXML.creer(p);
	}

	@Override
	public List<I_Produit> findAllProduit() {
		return pXML.lireTous();
	}

	@Override
	public I_Produit findByNameProduit(String nom) {
		return pXML.lire(nom);
	}

	@Override
	public boolean updateQuantite(I_Produit p) {
		return pXML.maj(p);
	}

	@Override
	public boolean deleteProduit(I_Produit p) {
		return pXML.supprimer(p);
	}

}
