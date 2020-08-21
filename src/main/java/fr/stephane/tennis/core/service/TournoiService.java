package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.JoueurDAOImpl;
import fr.stephane.tennis.core.DAO.TournoiDAOImpl;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.dto.TournoiDTO;
import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Tournoi;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiDAOImpl tournoiDAO;

    public TournoiService() {
        this.tournoiDAO = new TournoiDAOImpl();
    }

    public void createTournoi(TournoiDTO dto){
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            Tournoi tournoi = new Tournoi();
            tournoi.setId(dto.getId());
            tournoi.setCode(dto.getCode());
            tournoi.setNom(dto.getNom());

            tournoiDAO.create(tournoi);
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
    public TournoiDTO getTournoi(Long id){
        Session session = null;
        Transaction transaction = null;
        Tournoi tournoi = null;
        TournoiDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            tournoi = tournoiDAO.getById(id);

            dto = new TournoiDTO();
            dto.setId(tournoi.getId());
            dto.setCode(tournoi.getCode());
            dto.setNom(tournoi.getNom());

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
        return dto;
    }
    public void deleteTournoi(Long id){
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            tournoiDAO.delete(id);
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
