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

//    public void renomme(Long id, String nouveauNom){
//        Joueur joueur = null;
//        Session session = null;
//        Transaction transaction = null;
//        try {
//
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            joueur = session.get(Joueur.class,id);
//            joueur.setNom(nouveauNom);
//            transaction.commit();
//            System.out.println("nom du joueur modifié");
//
//        }catch (Exception e){
//            if (transaction !=null){
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        finally {
//            if(session != null){
//                session.close();
//            }
//        }
//    }

    public void create(Joueur joueur){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(joueur);
        System.out.println("Joueur crée");
    }

    public Joueur getById(Long id) {
        Joueur joueur = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        joueur = session.get(Joueur.class,id);
        System.out.println("Joueur lu");

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

    public void delete(Long id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Joueur joueur = getById(id);
        session.delete(joueur);
        System.out.println("Joueur supprimé");
    }

}
