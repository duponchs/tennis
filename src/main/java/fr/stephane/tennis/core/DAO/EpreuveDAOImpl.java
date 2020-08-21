package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.HibernateUtil;
import fr.stephane.tennis.core.entity.Epreuve;
import org.hibernate.Session;


public class EpreuveDAOImpl {

    public Epreuve getById(Long id) {
        Epreuve epreuve= null;
        Session session = null;

        session = HibernateUtil.getSessionFactory().openSession();
        epreuve = session.get(Epreuve.class,id);
        System.out.println("Epreuve lue");

        return epreuve;
    }


}
