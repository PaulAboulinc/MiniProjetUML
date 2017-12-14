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
			new FenetreAffichage("La quantité n'est pas un entier ! \n");
			return false;
        }
		if (nom.trim().isEmpty()) {
			new FenetreAffichage("Le nom ne peut pas être vide ! \n");
			return false;
		}
		
		boolean isAjoute = catalogue.addProduit(nom,prix,quantite);
		if(isAjoute == true){
			new FenetreAffichage("Le produit a été ajouté ! \n");
			return true;
		}else{
			String affichage = "Le produit n'a pas été ajouté ! \n";
			if (Arrays.asList(catalogue.getNomProduits()).contains(nom)) {
				affichage += "Le nom est déjà  utilisé ! \n";
			}
			if (prix <= 0) {
				affichage += "Le prix ne peut pas être inférieur ou égal à  0 !  \n";
			}
			if (quantite <= 0) {
				affichage += "La quantité ne peut pas être inférieur ou égal à  0 !  \n";
			}
			new FenetreAffichage (affichage);
			return false;
		}
	}
	
	public boolean suppressionProduit(String nom){
		boolean isRemove = catalogue.removeProduit(nom);
		if(isRemove == true){
			new FenetreAffichage("Le produit a été supprimé !");
			return true;
		}else{
			new FenetreAffichage("Le produit n'a pas été supprimé !");
			return false;
		}
	}
}
