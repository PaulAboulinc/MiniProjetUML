
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
			new FenetreAffichage("La quantit� n'est pas un entier ! \n");
			return false;
        }
		
		if (catalogue.acheterStock(nom, qte)) {
			new FenetreAffichage("Le stock a �t� mis � jour !");
			return true;
		}else {
			String affichage = "Le stock n'a pas �t� mis � jour ! \n";
			if (qte <= 0) {
				affichage += "La quantit� ne peut pas �tre inf�rieur ou �gal � 0 !  \n";
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
			new FenetreAffichage("La quantit� n'est pas un entier ! \n");
			return false;
        }
		
		if (catalogue.vendreStock(nom, qte)) {
			new FenetreAffichage("Le stock a �t� mis � jour !");
			return true;
		} else {
			String affichage = "Le stock n'a pas �t� mis � jour ! \n";
			if (qte <= 0) {
				affichage += "La quantit� ne peut pas �tre inf�rieur ou �gal � 0 !  \n";
			}
			new FenetreAffichage(affichage);
			return false;
		}
	}

}
