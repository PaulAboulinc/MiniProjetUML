
public class ControllerStock {

	I_Catalogue catalogue;
	
	public ControllerStock(I_Catalogue c) {
		catalogue = c;
	}
	
	public void afficherQuantite () {
		String afficher = catalogue.toString();
		new FenetreAffichage(afficher);
	}
}
