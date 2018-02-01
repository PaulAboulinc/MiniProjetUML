package Vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.ControllerCategorie;

public class FenetreNouvelleCategorie extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7153663099478448574L;
	private JTextField txtTaux;
	private JTextField txtNom;
	private JButton btValider;
	private ControllerCategorie controllerCategorie;

	public FenetreNouvelleCategorie(ControllerCategorie cCategorie) {

		controllerCategorie = cCategorie;
		setTitle("Creation Categorie");
		setBounds(500, 500, 200, 210);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom categorie");
		JLabel labTaux = new JLabel("Taux TVA");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labTaux);
		txtTaux = new JTextField(15);
		contentPane.add(txtTaux);

		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (controllerCategorie.ajouterCategorie(txtNom.getText(), txtTaux.getText())) {
			this.dispose();
		}
	}

}