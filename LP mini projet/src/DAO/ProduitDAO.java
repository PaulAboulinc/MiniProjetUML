package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.I_Produit;
import Model.CatalogueFactory;

public class ProduitDAO implements I_ProduitDAO{
	
	private Connection cn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private int idCatalogue;
	private I_CategorieDAO categorieDAO;
	
	public ProduitDAO (String nom) {
		categorieDAO = CatalogueDAOFactory.getInstance().createCategorieDAO();
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();
		idCatalogue = getIdCatalogue(nom);
		
		String tempSQL = "SELECT nom,prix,quantite,idCatalogue,idCategorie FROM Produits WHERE idCatalogue = ? ORDER BY id";
		
		try {
			pst = cn.prepareStatement(tempSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst.setInt(1, idCatalogue);
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
			rs.updateInt("idCategorie", p.getIdCategorie());
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
				float tauxTVA = categorieDAO.getTauxTVAById(rs.getInt("idCategorie"));
				p = CatalogueFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"), rs.getInt("idCategorie"), tauxTVA);
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
					float tauxTVA = categorieDAO.getTauxTVAById(rs.getInt("idCategorie"));
					p = CatalogueFactory.createProduit(rs.getString("nom"), rs.getDouble("prix"), rs.getInt("quantite"), rs.getInt("idCategorie"), tauxTVA);					
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
