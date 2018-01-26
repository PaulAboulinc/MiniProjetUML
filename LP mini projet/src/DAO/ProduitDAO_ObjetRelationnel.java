package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.I_Produit;
import Model.CatalogueFactory;

public class ProduitDAO_ObjetRelationnel implements I_ProduitDAO{
	
	private Connection cn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String nomCatalogue;
	
	public ProduitDAO_ObjetRelationnel (String nom) {
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();
		
		nomCatalogue = nom;
		String tempSQL = "SELECT nom,prix,quantite,P.ref_catalogue.nom FROM Produits P WHERE P.ref_catalogue.nom = ? ORDER BY id";
		
		try {
			pst = cn.prepareStatement(tempSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, nomCatalogue);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createProduit (I_Produit p) {
		try {
			PreparedStatement ps = cn.prepareStatement("INSERT INTO Produits VALUES (null,?,?,?,"
										+ "(SELECT REF(c) FROM Catalogues c WHERE c.nom = ?))");
			ps.setString(1, p.getNom());
			ps.setDouble(2, p.getPrixUnitaireHT());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, nomCatalogue);
			ps.executeQuery();
			rs = pst.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<I_Produit> findAllProduit () {
		I_Produit p = null;
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		
		try {
			rs.beforeFirst();
			while (rs.next()) {
				p = CatalogueFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
				listProduits.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProduits;
	}
	
	public I_Produit findByNameProduit (String nom) {
		I_Produit p = null;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				if (nom.equals(rs.getString("nom"))) {
					p = CatalogueFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public boolean updateQuantite (I_Produit p) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if(rs.getString("nom").equals(p.getNom())) {
					rs.updateInt("quantite", p.getQuantite());
					rs.updateRow();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteProduit (I_Produit p) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if(rs.getString("nom").equals(p.getNom())) {
					rs.deleteRow();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}	
}
