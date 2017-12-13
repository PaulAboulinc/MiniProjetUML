
public class ControllerProduit  {
	
	I_Catalogue catalogue;
	
	public ControllerProduit (I_Catalogue c) {
		catalogue = c;
	}
	
	public boolean creationProduit(String nom, double prix, int quantite){
		boolean isAjoute = catalogue.addProduit(nom,prix,quantite);
		if(isAjoute == true){
			new FenetreAffichage("Le produit a été ajouté !");
			return true;
		}else{
			new FenetreAffichage("Le produit n'a pas été ajouté !");
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
