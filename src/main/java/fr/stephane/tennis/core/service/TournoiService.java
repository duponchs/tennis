package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.JoueurDAOImpl;
import fr.stephane.tennis.core.DAO.TournoiDAOImpl;
import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Tournoi;

public class TournoiService {
    private TournoiDAOImpl tournoiDAO;

    public TournoiService() {
        this.tournoiDAO = new TournoiDAOImpl();
    }

    public void createTournoi(Tournoi tournoi){
        tournoiDAO.create(tournoi);
    }
    public Tournoi getTournoi(Long id){
        return tournoiDAO.getById(id);
    }
}
