package Vue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.ControllerProduit;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	private JButton btSupprimer;
	private JComboBox<String> combo;
	ControllerProduit controllerProduit;
	public FenetreSuppressionProduit(ControllerProduit c) {
		controllerProduit = c;
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(controllerProduit.getCatalogue().getNomProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (controllerProduit.suppressionProduit(combo.getSelectedItem().toString())){
			combo.removeItem(combo.getSelectedItem());
		}
	}

}
