package Controller;
import Model.I_Catalogue;
import Vue.FenetreAffichage;

public class ControllerAchatVente {
	
	private I_Catalogue catalogue;
	
	public ControllerAchatVente(I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean acheterProduit (String nom, String q) {
		if (!isQuantiteInteger(q)) {
			return false;
		}
		int qte = 0;
		qte = Integer.parseInt(q);
		
		if (catalogue.acheterStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		}else {
			return AffichageStockNonMisAJour(qte);
		}
	}
	
	public boolean vendreProduit (String nom, String q) {
		if (!isQuantiteInteger(q)) {
			return false;
		}
		int qte = 0;
		qte = Integer.parseInt(q);
		
		if (catalogue.vendreStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		} else {
			return AffichageStockNonMisAJour(qte);
		}
	}
	
	private boolean AffichageStockNonMisAJour (int qte) {
		String affichage = "Le stock n'a pas été mis à jour ! \n";
		if (qte <= 0) {
			affichage += "La quantité ne peut pas être inférieur ou égal à 0 !  \n";
		}
		new FenetreAffichage(affichage);
		return false;
	}
	
	private boolean isQuantiteInteger (String quantite) {
		String affichage = "Le stock n'a pas été mis à jour ! \n";
		try {
			Integer.parseInt(quantite);
			return true;
		} catch (NumberFormatException n) {
			new FenetreAffichage(affichage+"La quantité n'est pas un entier ! \n");
			return false;
        }
	}
	
	public String[] getNomProduits() {
		return catalogue.getNomProduits();
	}

}
