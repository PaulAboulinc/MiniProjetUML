package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.I_Produit;
import Model.ProduitFactory;

public class ProduitDAO implements I_ProduitDAO{
	
	private Connection cn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private int idCatalogue;
	
	public ProduitDAO (String nom) {
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();
		idCatalogue = getIdCatalogue(nom);
		
		String tempSQL = "SELECT nom,prix,quantite,idCatalogue FROM Produits WHERE idCatalogue = "+ idCatalogue +" ORDER BY id";
		
		try {
			//pst = (PreparedStatement) cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//pst = cn.prepareStatement("SELECT nom,prix,quantite,idCatalogue FROM Produits WHERE idCatalogue = ? ORDER BY id");
			//pst.setInt(1,idCatalogue);
			pst = cn.prepareStatement(tempSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int getIdCatalogue(String nomCatalogue) {
		try {
			PreparedStatement ps = cn.prepareStatement("SELECT id FROM Catalogues WHERE nom = ? ");
			ps.setString(1,nomCatalogue);
			ResultSet res = ps.executeQuery();
			res.next();
			return res.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean createProduit (I_Produit p) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nom",p.getNom());
			rs.updateDouble("prix", p.getPrixUnitaireHT());
			rs.updateInt("quantite", p.getQuantite());
			rs.updateInt("idCatalogue", idCatalogue);
			rs.insertRow();
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
