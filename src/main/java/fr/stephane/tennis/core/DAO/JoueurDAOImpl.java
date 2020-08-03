package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.entity.Joueur;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAOImpl {

    public void create(Joueur joueur){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3,(joueur.getSexe()).toString());

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            ResultSet rs = preparedStatement.getGeneratedKeys(); // permet de retourner toutes les valeurs auto générer

            if ( rs.next()){
                joueur.setId(rs.getLong(1));
            }

            System.out.println("Joueur crée");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur getById(Long id) {
        Joueur joueur = null;
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("SELECT NOM,PRENOM,SEXE FROM JOUEUR WHERE ID=?");

            preparedStatement.setLong(1,id);

            ResultSet rs = preparedStatement.executeQuery(); // retourne item

            if ( rs.next()){
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }

            System.out.println("Joueur lu");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueur;
    }

    public List<Joueur>list () {
        List<Joueur> desJoueurs = new ArrayList<Joueur>();
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("SELECT ID,NOM,PRENOM,SEXE FROM JOUEUR ");

            ResultSet rs = preparedStatement.executeQuery(); // retourne item

            while ( rs.next()){
                Joueur unJoueur = new Joueur();
                unJoueur.setId(rs.getLong("ID"));
                unJoueur.setNom(rs.getString("NOM"));
                unJoueur.setPrenom(rs.getString("PRENOM"));
                unJoueur.setSexe(rs.getString("SEXE").charAt(0));
                desJoueurs.add(unJoueur);
            }

            System.out.println("Joueurs lus");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return desJoueurs;
    }

    public void update(Joueur joueur){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("UPDATE  JOUEUR SET NOM=?,PRENOM=?,SEXE=? WHERE ID=?");

            preparedStatement.setString(1,joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3,(joueur.getSexe()).toString());
            preparedStatement.setLong(4,joueur.getId());

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            System.out.println("Joueur modifié");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void delete(Long id){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");

            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            System.out.println("Joueur supprimé");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
