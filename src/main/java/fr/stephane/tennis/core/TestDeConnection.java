package fr.stephane.tennis.core;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection con = null;
        try {
            //Depuis JDBC 2+
            //  MysqlDataSource dataSource = new MysqlDataSource();
            //  dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            //  dataSource.setServerName("localhost");
            //  dataSource.setPort(3306);
            //  dataSource.setDatabaseName("tennis");
            //  dataSource.setUseSSL(false);
            //  dataSource.setServerTimezone("Europe/Paris");
            //  dataSource.setUser("root");
            //  dataSource.setPassword("root");
            //  con = dataSource.getConnection();

            // Avec la dependance commons DBCP
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5); //5 connexions sont ouvertes pour notre projet et mi dans le pool
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            dataSource.setUsername("root");
            dataSource.setPassword("root");
            con = dataSource.getConnection();

            //MySQL driver MySQL Connector
            //  DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","Grimgor59100!!!59100");


            PreparedStatement preparedStatement  = con.prepareStatement("UPDATE JOUEUR SET NOM=?,PRENOM=? WHERE ID=?");

            Long identifiant = 24L;
            String nom = "Errani";
            String prenom = "Sara";
            preparedStatement.setString(1,nom);
            preparedStatement.setString(2,prenom);
            preparedStatement.setLong(3,identifiant);

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour



            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con!=null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
