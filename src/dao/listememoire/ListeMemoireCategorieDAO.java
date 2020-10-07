package dao.listememoire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MySql.Connexion;
import categorie.Categorie;
import dao.CategorieDAO;

public class ListeMemoireCategorieDAO implements CategorieDAO{

	private static ListeMemoireClientDAO instance;

	private List<Categorie> donnees;

	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return (instance);
	}
	
public ListeMemoireCategorieDAO() {
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


public boolean create(Categorie c) {
Connexion connect = new Connexion();
int i = 0;
try {
Connection connect1 = connect.creeConnexion();
PreparedStatement requete = connect1.prepareStatement("INSERT INTO Categorie(titre, visuel) VALUES ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
requete.setString(1,  c.getTitre());
requete.setString(2,  c.getVisuel());


i = requete.executeUpdate();
ResultSet res = requete.getGeneratedKeys();

if ( res.next())
c.setIdCategorie(res.getInt(1));

connect1.close();
}
catch(SQLException sqle)
{
System.out.println("Erreur !");
}
return (i == 1);



}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public boolean update(Categorie objet) {

Connexion c = new Connexion();
int i = 0;

try {
Connection c1 = c.creeConnexion();

PreparedStatement requete = c1.prepareStatement("UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie = ?");
requete.setString(1, objet.getTitre());
requete.setString(2, objet.getVisuel());
requete.setInt(3, objet.getIdCategorie());
i = requete.executeUpdate();

c1.close();
}
catch (SQLException sqle) {
System.out.println("Probleme update categorie");
}

return (i == 1);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public boolean delete(Categorie objet) {

Connexion c = new Connexion();
int i = 0;

try {
Connection c1 = c.creeConnexion();

PreparedStatement requete = c1.prepareStatement("DELETE FROM Categorie WHERE id_categorie = ? ");
requete.setInt(1, objet.getIdCategorie());
i = requete.executeUpdate();

c1.close();
}
catch (SQLException sqle) {
System.out.println("Probleme delete categorie");
}

return (i == 1);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





public ArrayList<Categorie> findAll() {
Connexion c = new Connexion();
ArrayList<Categorie> liste = new ArrayList<Categorie>();

try {
Connection c1 = c.creeConnexion();

Statement requete = c1.createStatement();
ResultSet res = requete.executeQuery("SELECT * FROM Categorie");
while (res.next()) {

liste.add(new Categorie(res.getInt(1), res.getString(2), res.getString(3)));

}

c1.close();
res.close();
}
catch (SQLException sqle) {
System.out.println("Problemes select * Categorie");
}

return (liste);
}


@Override
public ArrayList<Categorie> getByNom(int id) {
	// TODO Stub de la méthode généré automatiquement
	return null;
}
}