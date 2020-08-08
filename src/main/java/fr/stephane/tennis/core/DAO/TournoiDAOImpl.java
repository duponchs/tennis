package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiDAOImpl {
    public void create(Tournoi tournoi){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("INSERT INTO TOURNOI (NOM,CODE) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            ResultSet rs = preparedStatement.getGeneratedKeys(); // permet de retourner toutes les valeurs auto générer

            if ( rs.next()){
                tournoi.setId(rs.getLong(1));
            }

            System.out.println("Tournoi crée");

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

    public Tournoi getById(Long id) {
        Tournoi tournoi = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tournoi = session.get(Tournoi.class,id);
            System.out.println("Tournoi lu");

        } catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return tournoi;
    }

    public List<Tournoi> list () {
        List<Tournoi> desTournois = new ArrayList<Tournoi>();
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("SELECT ID,NOM,CODE FROM TOURNOI ");

            ResultSet rs = preparedStatement.executeQuery(); // retourne item

            while ( rs.next()){
                Tournoi unTournoi = new Tournoi();
                unTournoi.setId(rs.getLong("ID"));
                unTournoi.setNom(rs.getString("NOM"));
                unTournoi.setCode(rs.getString("CODE"));
                desTournois.add(unTournoi);
            }

            System.out.println("Tournois lus");

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
        return desTournois;
    }

    public void update(Tournoi tournoi){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("UPDATE  TOURNOI SET NOM=?,CODE=? WHERE ID=?");

            preparedStatement.setString(1,tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3,tournoi.getId());

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            System.out.println("Tournoi modifié");

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

            PreparedStatement preparedStatement  = con.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");

            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            System.out.println("Tournoi supprimé");

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
