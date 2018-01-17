package Model;
import java.math.BigDecimal;


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
		if (qteAchetee>0) {
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}
	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue<=this.quantiteStock && qteVendue>0){
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
	
	public String getPrixUnitaireHTEnFormatString () {
		return getPrixEnFormatString(this.getPrixUnitaireHT());
	}
	
	public String getPrixUnitaireTTCEnFormatString () {
		return getPrixEnFormatString(this.getPrixUnitaireTTC());
	}
	
	public String getPrixEnFormatString (double prix) {
		BigDecimal bigDecimalPrix = new BigDecimal(prix);
		bigDecimalPrix = bigDecimalPrix.setScale(2, BigDecimal.ROUND_HALF_UP);
		String stringPrix = ""+bigDecimalPrix;
		stringPrix = stringPrix.replace(".", ",");
		return stringPrix;
	}
	
	public String toString() {
		String prixUnitaireHT = getPrixUnitaireHTEnFormatString();
		String prixUnitaireTTC = getPrixUnitaireTTCEnFormatString();
		return this.getNom()+" - prix HT : "+prixUnitaireHT+" € - prix TTC : "+prixUnitaireTTC+ " € - quantité en stock : "+this.getQuantite()+"\n";
	}
}
