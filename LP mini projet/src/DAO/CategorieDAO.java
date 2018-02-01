package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.CatalogueFactory;
import Model.I_Categorie;

public class CategorieDAO implements I_CategorieDAO{

	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	public CategorieDAO() {
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();

		try {
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT nom, tauxTVA FROM Categories C");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createCategorie(I_Categorie c) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nom",c.getNom());
			rs.updateFloat("tauxTVA",c.getTauxTVA());
			rs.insertRow();
			rs = st.executeQuery("SELECT C.* FROM Categories C");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<I_Categorie> findAllCategorie() {
		List<I_Categorie> listCategorie = new ArrayList<I_Categorie>();
		try {
			rs.beforeFirst();
			while (rs.next()) {
				I_Categorie categorie = CatalogueFactory.createCategorie(rs.getString("nom"),rs.getFloat("tauxTVA"));
				listCategorie.add(categorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCategorie;
	}

	@Override
	public boolean deleteCategorie(String nomCategorie) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if(rs.getString("nom").equals(nomCategorie)) {
					rs.deleteRow();
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getNombreProduitsParCategorie(String nomCategorie) {
		ResultSet res = null;
		int result = 0;
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT COUNT(*) as countProduits FROM Produits P JOIN Categories C ON C.id=P.idCategorie WHERE C.nom = ?");
			pst.setString(1,nomCategorie);
			res = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			res.next();
			result = res.getInt("countProduits");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean findCategorieByName(String nomCategorie) {
		try {
			rs.beforeFirst();
			while(rs.next()) {
				if (nomCategorie.equals(rs.getString("nom"))) {
					return true;					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getIdCategorieByName(String nomCategorie) {
		ResultSet res = null;
		int result = -1;
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT id FROM Categories C WHERE C.nom = ?");
			pst.setString(1,nomCategorie);
			res = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			res.next();
			result = res.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public float getTauxTVAById(int idCategorie) {
		ResultSet res = null;
		float result = -1;
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT tauxTVA FROM Categories C WHERE C.id = ?");
			pst.setInt(1,idCategorie);
			res = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			res.next();
			result = res.getFloat("tauxTVA");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
