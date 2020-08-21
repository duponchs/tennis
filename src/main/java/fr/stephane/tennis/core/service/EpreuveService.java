package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.EpreuveDAOImpl;

import fr.stephane.tennis.core.HibernateUtil;

import fr.stephane.tennis.core.dto.EpreuveFullDTO;
import fr.stephane.tennis.core.dto.EpreuveLightDTO;
import fr.stephane.tennis.core.dto.TournoiDTO;
import fr.stephane.tennis.core.entity.Epreuve;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private EpreuveDAOImpl epreuveDAO;

    public EpreuveService() {
        this.epreuveDAO = new EpreuveDAOImpl();
    }

    public EpreuveFullDTO  getEpreuveAvecTournoi(Long id){
        Session session = null;
        Transaction transaction = null;
        Epreuve epreuve = null;
        EpreuveFullDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            epreuve = epreuveDAO.getById(id);

            dto = new EpreuveFullDTO();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDTO tournoiDTO = new TournoiDTO();
            tournoiDTO.setId(epreuve.getTournoi().getId());
            tournoiDTO.setCode(epreuve.getTournoi().getCode());
            tournoiDTO.setNom(epreuve.getTournoi().getNom());

            dto.setTournoi(tournoiDTO);

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

    public EpreuveLightDTO getEpreuveSansTournoi(Long id){
        Session session = null;
        Transaction transaction = null;
        Epreuve epreuve = null;
        EpreuveLightDTO dto = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            epreuve = epreuveDAO.getById(id);


            dto = new EpreuveLightDTO();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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
}
