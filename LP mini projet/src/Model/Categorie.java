package Model;

public class Categorie implements I_Categorie {

	private String nom;
	private float tauxTVA;
	
	public Categorie(String n, float tva) {
		nom = n;
		tauxTVA = tva;
	}
	
	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public float getTauxTVA() {
		return tauxTVA;
	}
}
