package Model;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import DAO.I_ProduitDAO;
import DAO.ProduitDAO;
import DAO.ProduitFactory;
import Vue.FenetreAffichage;

public class Catalogue implements I_Catalogue{
	
	public List<I_Produit> listProduits;
	ProduitFactory produitFactory = new ProduitFactory();
	I_ProduitDAO produitDAO;
	public Catalogue () {
		produitDAO = (I_ProduitDAO) produitFactory.createProduit();
		listProduits = produitDAO.findAllProduit(); 
		//listProduits = new ArrayList<I_Produit>(); 
	}
	//Vérifier dans controller que le prix et qte sont numériques et non nulles
	@Override
	public boolean addProduit(I_Produit produit) {
		if (produit == null) {
			return false;
		}
		if (Arrays.asList(getNomProduits()).contains(produit.getNom().trim()) || produit.getPrixUnitaireHT()<=0 || produit.getPrixUnitaireHT()<=0 || produit.getQuantite()<0) {
			return false;
		}
		Produit p = new Produit (produit);
		produitDAO.createProduit(p);
		listProduits.add(p);
		return true;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		if (Arrays.asList(getNomProduits()).contains(nom.trim()) || qte<0 || prix<=0) {
			return false;
		}	
	    String n = nom.trim();
	    n = n.replace("\t", " ");
		Produit p = new Produit (n,  prix , qte);
		produitDAO.createProduit(p);
		listProduits.add(p);
		return true;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int i=0;
		if (l != null) {
			for (I_Produit i_Produit : l) {
				if (addProduit(i_Produit)) {
					produitDAO.createProduit(i_Produit);
					i++;
				}
			}
		}
		return i;
	}

	@Override
	public boolean removeProduit(String nom) {
		for (I_Produit i_Produit : listProduits) {
			if (i_Produit.getNom().equals(nom)) {
				produitDAO.deleteProduit(nom);
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
					produitDAO.updateQuantite(nomProduit, i_Produit.getQuantite()+qteAchetee);
					return i_Produit.ajouter(qteAchetee);
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
					produitDAO.updateQuantite(nomProduit, i_Produit.getQuantite()-qteVendue);
					return i_Produit.enlever(qteVendue);
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
		Collections.sort(Arrays.asList(noms));
		return noms;
	}

	@Override
	public double getMontantTotalTTC() {
		double result = 0;
		for (I_Produit i_Produit : listProduits) {
			result += i_Produit.getPrixStockTTC();
		}
		return Math.round(result * 100.0) / 100.0;
	}

	@Override
	public void clear() {
		listProduits.removeAll(listProduits);	
	}
	
	@Override
	public String toString () {
		BigDecimal montantTotal = new BigDecimal(this.getMontantTotalTTC());
		montantTotal = montantTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		String montantTotalTTC = ""+montantTotal;
		montantTotalTTC = montantTotalTTC.replace(".", ",");
		
		String affichage="";
		
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