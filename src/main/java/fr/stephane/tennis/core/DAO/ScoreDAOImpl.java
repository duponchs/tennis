package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Joueur;
import fr.stephane.tennis.core.entity.Score;
import org.hibernate.Session;


public class ScoreDAOImpl {

    public void create(Score score){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
        System.out.println("Score créé");
    }

    public Score getById(Long id){
        Score score = null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        score = session.get(Score.class,id);
        System.out.println("Score lu");

        return score;
    }


}
