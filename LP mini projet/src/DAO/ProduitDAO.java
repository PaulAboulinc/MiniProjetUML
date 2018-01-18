package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.I_Produit;
import Model.Produit;
import Model.ProduitFactory;

public class ProduitDAO implements I_ProduitDAO{
	
	private Connection cn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public ProduitDAO () {
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();
		
		try {
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT nom,prix,quantite FROM Produits ORDER BY id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createProduit (I_Produit p) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nom",p.getNom());
			rs.updateDouble("prix", p.getPrixUnitaireHT());
			rs.updateInt("quantite", p.getQuantite());
			rs.insertRow();
			rs = st.executeQuery("SELECT nom,prix,quantite FROM Produits ORDER BY id");
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
				
				p = ProduitFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
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
					p = ProduitFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));					
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
