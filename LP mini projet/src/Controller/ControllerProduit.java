package Controller;
import java.util.Arrays;

import Model.I_Catalogue;
import Vue.FenetreAffichage;


public class ControllerProduit  {
	
	I_Catalogue catalogue;
	
	public ControllerProduit (I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean creationProduit(String nomFormulaire, String prixFormulaire, String quantiteFormulaire){
		if (!isValeurDuFormulaireValide(nomFormulaire, prixFormulaire, quantiteFormulaire)) {
			return false;
		}	
		int quantite = Integer.parseInt(quantiteFormulaire);
		double prix = Double.parseDouble(prixFormulaire);
		String nom = nomFormulaire.trim();
		boolean isProduitAjoute = catalogue.addProduit(nom,prix,quantite);
		if(isProduitAjoute == true){
			new FenetreAffichage("Le produit a été ajouté ! \n");
			return true;
		}else{
			return AffichageErreurProduitNonAjoute(nom,prix,quantite);
		}
	}
	
	private boolean isValeurDuFormulaireValide (String nomFormulaire, String prixFormulaire, String quantiteFormulaire) {
		if (!isPrixDouble(prixFormulaire)) {
			return false;
		}	
		if (!isQuantiteInteger(quantiteFormulaire)) {
			return false;
		}	
		if (isNomVide(nomFormulaire)) {
			return false;
		}
		return true;
	}
	
	private boolean isNomVide (String nomFormulaire) {
		if (nomFormulaire.trim().isEmpty()) {
			new FenetreAffichage("Le nom ne peut pas être vide ! \n");
			return true;
		}
		return false;
	}
	
	private boolean isPrixDouble (String prixFormulaire) {
		try {
			Double.parseDouble(prixFormulaire);
			return true;
		} catch (NumberFormatException n) {
			new FenetreAffichage("Le prix n'est pas un entier ! \n");
			return false;
        }
	}
	
	private boolean isQuantiteInteger (String quantiteFormulaire) {
		try {
			Integer.parseInt(quantiteFormulaire);
			return true;
		} catch (NumberFormatException n) {
			new FenetreAffichage("La quantité n'est pas un entier ! \n");
			return false;
        }
	}
	
	
	private boolean AffichageErreurProduitNonAjoute (String nom, double prix, int quantite) {
		String affichage = "Le produit n'a pas été ajouté ! \n";
		if (Arrays.asList(catalogue.getNomProduits()).contains(nom)) {
			affichage += "Le nom est déjà utilisé ! \n";
		}
		if (prix <= 0) {
			affichage += "Le prix ne peut pas être inférieur ou égal à 0 !  \n";
		}
		if (quantite <= 0) {
			affichage += "La quantité ne peut pas être inférieur ou égal à 0 !  \n";
		}
		new FenetreAffichage (affichage);
		return false;
	}
	
	public boolean suppressionProduit(String nomFormulaire){
		boolean isProduitSupprime = catalogue.removeProduit(nomFormulaire);
		if(isProduitSupprime){
			new FenetreAffichage("Le produit a été supprimé !");
			return true;
		}else{
			new FenetreAffichage("Le produit n'a pas été supprimé !");
			return false;
		}
	}
	
	public String[] getNomProduits() {
		return catalogue.getNomProduits();
	}
}
