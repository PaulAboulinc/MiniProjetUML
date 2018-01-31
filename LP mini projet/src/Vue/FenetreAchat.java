package Vue;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Controller.ControllerAchatVente;

public class FenetreAchat extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7632069220644012920L;
	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControllerAchatVente controllerAchatVente;
	
	public FenetreAchat(ControllerAchatVente cAchatVente ) {
		
		controllerAchatVente = cAchatVente;
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(controllerAchatVente.getNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		controllerAchatVente.acheterProduit(combo.getSelectedItem().toString(), txtQuantite.getText());
	}

}
