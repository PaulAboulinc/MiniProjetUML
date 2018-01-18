package Model;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import DAO.I_ProduitDAO;
import DAO.ProduitDAOFactory;

public class Catalogue implements I_Catalogue{
	
	private I_ProduitDAO produitDAO;
	
	public Catalogue () {
		produitDAO = ProduitDAOFactory.createProduit();
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		if (produit != null) {
			return addProduit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite());
		}
		return false;
	}
	
	@Override
	public boolean addProduit(String n, double prix, int qte) {
		if (Arrays.asList(getNomProduits()).contains(n.trim()) || qte<0 || prix<=0) {
			return false;
		}	
		String nom = n.trim();
		nom = nom.replace("\t", " ");
		I_Produit produit = ProduitFactory.createProduit(nom,  prix , qte);
		return produitDAO.createProduit(produit);
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int i=0;
		if (l != null) {
			for (I_Produit i_Produit : l) {
				if (addProduit(i_Produit)) {
					i++;
				}
			}
		}
		return i;
	}

	@Override
	public boolean removeProduit(String nom) {
		List<I_Produit> listProduits = produitDAO.findAllProduit();
		for (I_Produit i_Produit : listProduits) {
			if (i_Produit.getNom().equals(nom)) {
				return produitDAO.deleteProduit(i_Produit);
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			I_Produit i_Produit = produitDAO.findByNameProduit(nomProduit);
			if (i_Produit.ajouter(qteAchetee)) {
				return produitDAO.updateQuantite(i_Produit);
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			I_Produit i_Produit = produitDAO.findByNameProduit(nomProduit);
			if (i_Produit.enlever(qteVendue)) {
				return produitDAO.updateQuantite(i_Produit);
			}
		}
		return false;
	}

	@Override
	public String[] getNomProduits() {
		List<I_Produit> listProduits = produitDAO.findAllProduit();
		String[] noms = new String[listProduits.size()];
		for (int i=0; i<listProduits.size(); i++) {
			noms[i] = listProduits.get(i).getNom();
		}
		Collections.sort(Arrays.asList(noms));
		return noms;
	}

	@Override
	public double getMontantTotalTTC() {
		double result = 0;
		List<I_Produit> listProduits = produitDAO.findAllProduit();
		for (I_Produit i_Produit : listProduits) {
			result += i_Produit.getPrixStockTTC();
		}
		return Math.round(result * 100.0) / 100.0;
	}

	@Override
	public void clear() {
		List<I_Produit> listProduits = produitDAO.findAllProduit();
		for (I_Produit i_Produit : listProduits) {
			produitDAO.deleteProduit(i_Produit);
		}
	}
	
	private String getPrixEnFormatString (double prix) {
		BigDecimal bigDecimalPrix = new BigDecimal(prix);
		bigDecimalPrix = bigDecimalPrix.setScale(2, BigDecimal.ROUND_HALF_UP);
		String stringPrix = ""+bigDecimalPrix;
		stringPrix = stringPrix.replace(".", ",");
		return stringPrix;
	}
	
	@Override
	public String toString () {
		String montantTotalTTC = this.getPrixEnFormatString(this.getMontantTotalTTC());
		String affichage="";
		List<I_Produit> listProduits = produitDAO.findAllProduit();
		for (I_Produit i_Produit : listProduits) {
			affichage += i_Produit.toString();
		}
		if (listProduits.isEmpty()) {
			return "\n" + "Montant total TTC du stock : "+montantTotalTTC+" €";
		} else {
			return affichage+"\n" + "Montant total TTC du stock : "+montantTotalTTC+" €";
		}
	}

}