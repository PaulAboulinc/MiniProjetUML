import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Catalogue implements I_Catalogue{
	
	public List<I_Produit> listProduits;
	
	public Catalogue () {
		listProduits = new ArrayList<I_Produit>(); 
	}

	@Override
	public boolean addProduit(I_Produit produit) {
		if (Arrays.asList(getNomProduits()).contains(produit.getNom()) || produit.getPrixUnitaireHT()<0) {
			return false;
		}
		Produit p = new Produit (produit);
		listProduits.add(p);
		return true;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		if (Arrays.asList(getNomProduits()).contains(nom) || qte<0) {
			return false;
		}
		Produit p = new Produit (nom, prix, qte);
		listProduits.add(p);
		return true;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int i=0;
		for (I_Produit i_Produit : l) {
			if (addProduit(i_Produit)) {
				i++;
			}
		}
		return i;
	}

	@Override
	public boolean removeProduit(String nom) {
		for (I_Produit i_Produit : listProduits) {
			if (i_Produit.getNom().equals(nom)) {
				listProduits.remove(i_Produit);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			for (I_Produit i_Produit : listProduits) {
				if (i_Produit.getNom().equals(nomProduit)) {
					i_Produit.ajouter(qteAchetee);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			for (I_Produit i_Produit : listProduits) {
				if (i_Produit.getNom().equals(nomProduit)) {
					i_Produit.enlever(qteVendue);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String[] getNomProduits() {
		String[] noms = new String[listProduits.size()];
		for (int i=0; i<listProduits.size(); i++) {
			noms[i] = listProduits.get(i).getNom();
		}
		return noms;
	}

	@Override
	public double getMontantTotalTTC() {
		double result = 0;
		for (I_Produit i_Produit : listProduits) {
			result += i_Produit.getPrixStockTTC();
		}
		return result;
	}

	@Override
	public void clear() {
		System.out.println("test git");		
	}
	

}