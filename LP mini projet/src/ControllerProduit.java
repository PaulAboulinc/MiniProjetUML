import java.util.Arrays;


public class ControllerProduit  {
	
	I_Catalogue catalogue;
	
	public ControllerProduit (I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean creationProduit(String nom, String p, String q){
		double prix=0;
		int quantite=0;
		try {
			prix = Double.parseDouble(p);
		} catch (NumberFormatException n) {
			new FenetreAffichage("Le prix n'est pas un entier ! \n");
			return false;
        }
		try {
			quantite = Integer.parseInt(q);
		} catch (NumberFormatException n) {
			new FenetreAffichage("La quantit� n'est pas un entier ! \n");
			return false;
        }
		if (nom.trim().isEmpty()) {
			new FenetreAffichage("Le nom ne peut pas �tre vide ! \n");
			return false;
		}
		
		boolean isAjoute = catalogue.addProduit(nom,prix,quantite);
		if(isAjoute == true){
			new FenetreAffichage("Le produit a �t� ajout� ! \n");
			return true;
		}else{
			String affichage = "Le produit n'a pas �t� ajout� ! \n";
			if (Arrays.asList(catalogue.getNomProduits()).contains(nom)) {
				affichage += "Le nom est d�j� utilis� ! \n";
			}
			if (prix <= 0) {
				affichage += "Le prix ne peut pas �tre inf�rieur ou �gal � 0 !  \n";
			}
			if (quantite <= 0) {
				affichage += "La quantit� ne peut pas �tre inf�rieur ou �gal � 0 !  \n";
			}
			new FenetreAffichage (affichage);
			return false;
		}
	}
	
	public boolean suppressionProduit(String nom){
		boolean isRemove = catalogue.removeProduit(nom);
		if(isRemove == true){
			new FenetreAffichage("Le produit a �t� supprim� !");
			return true;
		}else{
			new FenetreAffichage("Le produit n'a pas �t� supprim� !");
			return false;
		}
	}
}
