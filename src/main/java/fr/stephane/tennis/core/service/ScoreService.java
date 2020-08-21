package fr.stephane.tennis.core.service;

import fr.stephane.tennis.core.DAO.ScoreDAOImpl;
import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreDAOImpl scoreDAO;

    public ScoreService() {
        this.scoreDAO = new ScoreDAOImpl();
    }

    public Score getScore(Long id){
        Session session = null;
        Transaction transaction = null;
        Score score = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            score = scoreDAO.getById(id);
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
        return score;
    }
}
