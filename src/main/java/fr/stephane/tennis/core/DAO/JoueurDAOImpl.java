package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDAOImpl {

    public void create(Joueur joueur){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(joueur);
            transaction.commit();
            System.out.println("Joueur crée");

        } catch (Exception e){
            if (transaction !=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Joueur getById(Long id) {
        Joueur joueur = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            joueur = session.get(Joueur.class,id);
            System.out.println("Joueur lu");

        }catch (Throwable t){
            t.printStackTrace();
        }
        finally {
            if(session != null){
                session.close();
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
