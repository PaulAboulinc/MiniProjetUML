package Vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.ControllerAchatVente;
import Controller.ControllerProduit;
import Controller.ControllerStock;
import Model.Catalogue;
import Model.I_Catalogue;



public class FenetrePrincipale extends JFrame implements ActionListener,
		WindowListener {

	private JButton btAfficher;
	private JButton btNouveauProduit;
	private JButton btSupprimerProduit;
//	private JButton btNouvelleCategorie;
//	private JButton btSupprimerCategorie;
	private JButton btAchat;
	private JButton btVente;
	private JButton btQuitter;
	I_Catalogue catalogue = new Catalogue();
	ControllerProduit cProduit = new ControllerProduit(catalogue);
	ControllerAchatVente cAchatVente = new ControllerAchatVente(catalogue);
	ControllerStock cStock = new ControllerStock(catalogue);

	
	public FenetrePrincipale() {
		
		setTitle("exercice Produits");
		setBounds(500, 500, 320, 250);
		JPanel panAffichage = new JPanel();
		JPanel panNouveauSupprimerProduit = new JPanel();
//		JPanel panNouveauSupprimerCategorie = new JPanel();
		JPanel panAchatVente = new JPanel();
		JPanel panQuitter = new JPanel();
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAfficher = new JButton("Quantités en stock");
		btNouveauProduit = new JButton("Nouveau Produit");
		btSupprimerProduit = new JButton("Supprimer Produit");
//		btNouvelleCategorie = new JButton("Nouvelle Categorie");
//		btSupprimerCategorie = new JButton("Supprimer Categorie");
		btAchat = new JButton("Achat Produits");
		btVente = new JButton("Vente Produits");
		btQuitter = new JButton("Quitter");
		panAffichage.add(btAfficher);
		panNouveauSupprimerProduit.add(btNouveauProduit); 
		panNouveauSupprimerProduit.add(btSupprimerProduit);
//		panNouveauSupprimerCategorie.add(btNouvelleCategorie); 
//		panNouveauSupprimerCategorie.add(btSupprimerCategorie);
		panAchatVente.add(btAchat); 
		panAchatVente.add(btVente);  
		panQuitter.add(btQuitter);

		contentPane.add(panAffichage);
//		contentPane.add(panNouveauSupprimerCategorie);
		contentPane.add(panNouveauSupprimerProduit);
		contentPane.add(panAchatVente);
		contentPane.add(panQuitter);

		btAfficher.addActionListener(this);
		btNouveauProduit.addActionListener(this);
		btSupprimerProduit.addActionListener(this);
//		btNouvelleCategorie.addActionListener(this);
//		btSupprimerCategorie.addActionListener(this);
		btAchat.addActionListener(this);
		btVente.addActionListener(this);
		btQuitter.addActionListener(this);
		
		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

/* tabProduits permet de tester le fonctionnement des fen�tres avec un tableau de noms de produits "en dur"
   Quand l'application fonctionnera, il faudra bien sûr récupérer les noms des produits dans le Catalogue */
		String[] tabProduits = catalogue.getNomProduits();
/* M�me chose pour tabCategories (partie 4) */ 		
//		String[] tabCategories = new String[] {"Bio", "Luxe" };
		
		if (e.getSource() == btAfficher)
			cStock.afficherQuantite();
		if (e.getSource() == btNouveauProduit)
//			new FenetreNouveauProduit(tabCategories);
			new FenetreNouveauProduit(cProduit);
		if (e.getSource() == btSupprimerProduit)
			new FenetreSuppressionProduit(cProduit);
//		if (e.getSource() == btNouvelleCategorie)
//			new FenetreNouvelleCategorie();
//		if (e.getSource() == btSupprimerCategorie)
//			new FenetreSuppressionCategorie(tabCategories);
		if (e.getSource() == btAchat)
			new FenetreAchat(cAchatVente);
		if (e.getSource() == btVente)
			new FenetreVente(cAchatVente);
		if (e.getSource() == btQuitter){
			System.out.println("Au revoir");
			System.exit(0);
		}	
	}

	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	
	
	public static void main(String[] args) {
		new FenetrePrincipale();
		
		//Test catalogue et Produits
		/*Catalogue c = new Catalogue();
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		I_Produit p1 = new Produit("testl1", 1.0, 1);
		I_Produit p2 = new Produit("testl2", 2.0, 2);
		listProduits.add(p1);
		listProduits.add(p2);
		System.out.println(c.addProduit("test1", 10, 5));
		System.out.println("Add : "+c.addProduit("test2", 15, 50));
		System.out.println("Acheter : "+c.acheterStock("test2", 1));
		System.out.println("Vendre : "+c.vendreStock("test2", 52));
		System.out.println("RemoveProduit test1 : "+c.removeProduit("test1"));
		System.out.println("AddListProduit : "+c.addProduits(listProduits));
		System.out.println("RemoveProduit testl1 : "+c.removeProduit("testl1"));
		System.out.println("RemoveProduit test21 : "+c.removeProduit("testl2"));
		System.out.println(" : "+c.addProduit(p1));
		//c.clear();
		System.out.println(c.toString());*/
	}

}
