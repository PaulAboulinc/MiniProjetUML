
public class Produit implements I_Produit {

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static double tauxTVA=0.2;
	
	public Produit (I_Produit p) {
		this.quantiteStock = p.getQuantite();
		this.nom = p.getNom();
		this.prixUnitaireHT = p.getPrixUnitaireHT();
	}
	
	public Produit (String nom, Double prixUnitaireHT, int qte) {
		this.quantiteStock = qte;
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {
		this.quantiteStock += qteAchetee;
		return true;
	}
	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue<=this.quantiteStock){
			this.quantiteStock -= qteVendue;
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String getNom() {
		return this.nom;
	}
	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}
	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}
	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT*(this.tauxTVA+1);
	}
	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock*this.getPrixUnitaireTTC();
	}
	public String toString() {
		return "Nom : "+this.getNom()+" Quantité : "+this.getQuantite()+" Prix Unitaire HT : "+this.getPrixUnitaireHT();
	}
}
