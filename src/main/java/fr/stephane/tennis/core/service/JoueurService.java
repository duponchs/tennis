package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.JoueurDAOImpl;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {

    private JoueurDAOImpl joueurDAO;

    public JoueurService() {
        this.joueurDAO = new JoueurDAOImpl();
    }

    public void createJoueur(Joueur joueur){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            joueurDAO.create(joueur);
            transaction.commit();

        }catch (Exception e){
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
    public Joueur getJoueur(Long id){
        Session session = null;
        Transaction transaction = null;
        Joueur joueur = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            joueur = joueurDAO.getById(id);
            transaction.commit();

        }catch (Exception e){
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
        return joueur;
    }
    public void renomme(Long id, String nouveauNom){

        Joueur joueur = getJoueur(id); // objet detachet non persistant

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            joueur.setNom(nouveauNom);
            Joueur joueur2 = (Joueur) session.merge(joueur);
            transaction.commit();

        }catch (Exception e){
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
    public void changementSexe(Long id, char nouveauSexe){

        Joueur joueur = getJoueur(id); // objet detachet non persistant

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            joueur.setSexe(nouveauSexe);
            Joueur joueur2 = (Joueur) session.merge(joueur);
            transaction.commit();

        }catch (Exception e){
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
    public void deleteJoueur(Long id){
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            joueurDAO.delete(id);
            transaction.commit();

        }catch (Exception e){
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

}
