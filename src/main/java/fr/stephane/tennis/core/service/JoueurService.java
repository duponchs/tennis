package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.JoueurDAOImpl;
import fr.stephane.tennis.core.entity.Joueur;

public class JoueurService {

    private JoueurDAOImpl joueurDAO;

    public JoueurService() {
        this.joueurDAO = new JoueurDAOImpl();
    }

    public void createJoueur(Joueur joueur){
        joueurDAO.create(joueur);
    }
    public Joueur getJoueur(Long id){
        return joueurDAO.getById(id);
    }

}
