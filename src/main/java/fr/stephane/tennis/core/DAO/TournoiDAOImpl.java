package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.HibernateUtil;

import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiDAOImpl {

    public void create(Tournoi tournoi){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(tournoi);
    }

    public Tournoi getById(Long id) {
        Tournoi tournoi=null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().openSession();
        tournoi = session.get(Tournoi.class,id);
        System.out.println("Tournoi lu");

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

    public void update(Long id ){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Tournoi tournoi = getById(id);
        session.delete(tournoi);
        System.out.println("Joueur supprimé");

    }

    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Tournoi tournoi = getById(id);
        session.delete(tournoi);
    }
}
