package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.MatchDAOImpl;
import fr.stephane.tennis.core.DAO.ScoreDAOImpl;
import fr.stephane.tennis.core.entity.Match;

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
}
