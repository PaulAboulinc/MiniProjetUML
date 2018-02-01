package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.ControllerCategorie;

public class FenetreSuppressionCategorie extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1040560733921212831L;
	private JButton btSupprimer;
	private JComboBox<String> combo;
	private ControllerCategorie controllerCategorie;
	
	public FenetreSuppressionCategorie(String lesCategories[],ControllerCategorie cCategorie) {
		
		controllerCategorie = cCategorie;
		setTitle("Suppression categorie");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(lesCategories);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Categorie"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		controllerCategorie.supprimerCategorie(combo.getSelectedItem().toString());
	}

}
