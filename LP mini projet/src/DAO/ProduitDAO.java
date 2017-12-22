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

public class ProduitDAO {
	
	private Connection cn = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	public ProduitDAO () {
		ConnexionBD connexionBD = new ConnexionBD ();
		cn = connexionBD.getConnection();
		
		try {
			st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean createProduit (String nom, Double prix, int quantite) {
		
		try {
			CallableStatement cst = cn.prepareCall("{call addProduit(?,?,?)}");
			cst.setString(1, nom);
			cst.setDouble(2, prix);
			cst.setInt(3, quantite);
			return cst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createProduit (I_Produit p) {
		try {
			CallableStatement cst = cn.prepareCall("{call addProduit(?,?,?)}");
			cst.setString(1, p.getNom());
			cst.setDouble(2, p.getPrixUnitaireHT());
			cst.setInt(3, p.getQuantite());
			return cst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public I_Produit findProduit (int id) {
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits WHERE id >= ?");
			pst.setInt(1,id);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		I_Produit p = null;
		try {
			p = new Produit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	public List<I_Produit> findAllProduit () {
		try {
			rs = st.executeQuery("SELECT * FROM Produits");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		I_Produit p = null;
		/*try {
			p = new Produit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		
		try {
			while (rs.next()) {
				p = new Produit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
				listProduits.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProduits;
	}
	public List<I_Produit> findByNameProduit (String value) {
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits WHERE nom = ?");
			pst.setString(1, value);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		I_Produit p = null;
		/*try {
			p = new Produit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		List<I_Produit> listProduits = new ArrayList<I_Produit>();
		
		try {
			while (rs.next()) {
				p = new Produit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"));
				listProduits.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listProduits;
	}
	public void updateProduit (I_Produit p) {
		try {
			PreparedStatement pst = cn.prepareStatement("UPDATE Produits SET prix = ?, quantite = ? WHERE nom = ?");
			pst.setDouble(1, p.getPrixUnitaireHT());
			pst.setInt(2, p.getQuantite());
			pst.setString(3, p.getNom());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteProduit (int id) {
		try {
			PreparedStatement pst = cn.prepareStatement("DELETE Produits WHERE id = ?");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteProduit (String nom) {
		try {
			PreparedStatement pst = cn.prepareStatement("DELETE Produits WHERE nom = ?");
			pst.setString(1, nom);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
