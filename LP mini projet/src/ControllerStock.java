
public class ControllerStock {

	I_Catalogue catalogue;
	
	public ControllerStock(I_Catalogue c) {
		catalogue = c;
	}
	
	public void afficherQuantite () {
		String afficher = catalogue.toString();
		afficher += "\n \n Montant total TTC du stock "+catalogue.getMontantTotalTTC()+"€";
		new FenetreAffichage(afficher);
	}
}
