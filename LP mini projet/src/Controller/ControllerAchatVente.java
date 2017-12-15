package Controller;
import Model.I_Catalogue;
import Vue.FenetreAffichage;

public class ControllerAchatVente {
	
	I_Catalogue catalogue;
	
	public ControllerAchatVente(I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean acheterProduit (String nom, String q) {
		int qte = 0;
		try {
			qte = Integer.parseInt(q);
		} catch (NumberFormatException n) {
			new FenetreAffichage("La quantité n'est pas un entier ! \n");
			return false;
        }
		
		if (catalogue.acheterStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		}else {
			String affichage = "Le stock n'a pas été mis à jour ! \n";
			if (qte <= 0) {
				affichage += "La quantité ne peut pas être inférieur ou égal à 0 !  \n";
			}
			new FenetreAffichage(affichage);
			return false;
		}
	}
	
	public boolean vendreProduit (String nom, String q) {
		int qte = 0;
		try {
			qte = Integer.parseInt(q);
		} catch (NumberFormatException n) {
			new FenetreAffichage("La quantité n'est pas un entier ! \n");
			return false;
        }
		
		if (catalogue.vendreStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		} else {
			String affichage = "Le stock n'a pas été mis à jour ! \n";
			if (qte <= 0) {
				affichage += "La quantité ne peut pas être inférieur ou égal à 0 !  \n";
			}
			new FenetreAffichage(affichage);
			return false;
		}
	}
	
	public I_Catalogue getCatalogue () {
		return catalogue;
	}

}
