package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO_ObjetRelationnel implements I_CatalogueDAO {

	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	public CatalogueDAO_ObjetRelationnel () {
		ConnexionBD connexionBD = ConnexionBD.getInstance();
		cn = connexionBD.getConnection();

		try {
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT nom FROM Catalogues");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean createCatalogue(String nom) {
		try {
			rs.moveToInsertRow();
			rs.updateString("nom",nom);
			rs.insertRow();
			rs = st.executeQuery("SELECT nom FROM Catalogues");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<String> findAllCatalogue() {
		List<String> listCatalogue = new ArrayList<String>();
		try {
			rs.beforeFirst();
			while (rs.next()) {
				listCatalogue.add(rs.getString("nom"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCatalogue;
	}

	@Override
	public boolean deleteCatalogue(String nomCatalogue) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				if(rs.getString("nom").equals(nomCatalogue)) {
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
	public int getNombreProduitsParCatalogue(String nom) {
		int i = 0;
		try {
			PreparedStatement pst = cn.prepareStatement("SELECT * FROM Produits P WHERE p.REF_CATALOGUE.nom = ?");
			pst.setString(1,nom);
			ResultSet res = pst.executeQuery();
			while(res.next()) {
				i++;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int getNombreCatalogues() {
		int i = 0;
		try {
			rs.beforeFirst();
			while(rs.next()) {
				i++;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public boolean findCatalogueByName(String nomCatalogue) {
		try {
			rs.beforeFirst();
			while(rs.next()) {
				if (nomCatalogue.equals(rs.getString("nom"))) {
					return true;					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
