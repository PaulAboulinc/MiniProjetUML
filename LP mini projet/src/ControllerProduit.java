
public class ControllerProduit  {
	
	public void creationProduit(String nom, double prix, int quantite,I_Catalogue catalogue){
		boolean isAjoute = catalogue.addProduit(nom,prix,quantite);
		if(isAjoute == true){
			new FenetreAffichage("Le produit a été ajouté !");
		}else{
			new FenetreAffichage("Le produit n'a pas été ajouté ! Le produit doit déjà exister ou il n'est pas correct");
		}
	}
}
