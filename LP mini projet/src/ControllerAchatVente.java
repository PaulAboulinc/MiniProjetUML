
public class ControllerAchatVente {
	
	I_Catalogue catalogue;
	
	public ControllerAchatVente(I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean acheterProduit (String nom, int qte) {
		if (catalogue.acheterStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		}
		new FenetreAffichage("Le stock n'a pas été mis à jour !");
		return false;
	}
	
	public boolean vendreProduit (String nom, int qte) {
		if (catalogue.vendreStock(nom, qte)) {
			new FenetreAffichage("Le stock a été mis à jour !");
			return true;
		}
		new FenetreAffichage("Le stock n'a pas été mis à jour !");
		return false;
	}

}
