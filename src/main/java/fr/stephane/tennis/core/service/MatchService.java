package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.MatchDAOImpl;
import fr.stephane.tennis.core.DAO.ScoreDAOImpl;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.dto.EpreuveFullDTO;
import fr.stephane.tennis.core.dto.JoueurDTO;
import fr.stephane.tennis.core.dto.MatchDTO;
import fr.stephane.tennis.core.dto.TournoiDTO;
import fr.stephane.tennis.core.entity.Epreuve;
import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreDAOImpl scoreDAO;
    private MatchDAOImpl matchDAO;

    public MatchService() {
        this.scoreDAO = new ScoreDAOImpl();
        this.matchDAO = new MatchDAOImpl();
    }

    public void enregistrerNouveauMatch(Match match){
        matchDAO.create(match);
        scoreDAO.create(match.getScore());
    }

    public MatchDTO getMatch(long id){
        Session session = null;
        Transaction transaction = null;
        Match match = null;
        MatchDTO dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            match = matchDAO.getById(id);


            JoueurDTO dtoFinaliste = new JoueurDTO();
            dtoFinaliste.setId(match.getFinaliste().getId());
            dtoFinaliste.setNom(match.getFinaliste().getNom());
            dtoFinaliste.setPrenom(match.getFinaliste().getPrenom());
            dtoFinaliste.setSexe(match.getFinaliste().getSexe());

            JoueurDTO dtoVainqueur = new JoueurDTO();
            dtoVainqueur.setId(match.getVainqueur().getId());
            dtoVainqueur.setNom(match.getVainqueur().getNom());
            dtoVainqueur.setPrenom(match.getVainqueur().getPrenom());
            dtoVainqueur.setSexe(match.getVainqueur().getSexe());

            dto = new MatchDTO();
            dto.setId(match.getId());
            dto.setFinaliste(dtoFinaliste);
            dto.setVainqueur(dtoVainqueur);

            EpreuveFullDTO epreuvedto = new EpreuveFullDTO();
            epreuvedto.setId(match.getEpreuve().getId());
            epreuvedto.setAnnee(match.getEpreuve().getAnnee());
            epreuvedto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDTO tournoiDTO = new TournoiDTO();
            tournoiDTO.setId(match.getEpreuve().getTournoi().getId());
            tournoiDTO.setCode(match.getEpreuve().getTournoi().getCode());
            tournoiDTO.setNom(match.getEpreuve().getTournoi().getNom());

            epreuvedto.setTournoi(tournoiDTO);

            dto.setEpreuve(epreuvedto);

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
