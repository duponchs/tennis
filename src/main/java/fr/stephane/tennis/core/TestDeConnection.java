package fr.stephane.tennis.core;


import fr.stephane.tennis.core.DAO.JoueurDAOImpl;
import fr.stephane.tennis.core.entity.Joueur;


import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        JoueurDAOImpl joueurDAOImpl = new JoueurDAOImpl();
        Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannik");
        noah.setSexe('H');
        joueurDAOImpl.create(noah);

        System.out.println("le joueur crée à l'identifiant "+noah.getId());


    }
}



