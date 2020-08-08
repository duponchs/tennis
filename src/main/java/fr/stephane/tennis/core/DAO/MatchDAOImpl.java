package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.entity.Match;


import javax.sql.DataSource;
import java.sql.*;

public class MatchDAOImpl {
    public void create(Match match){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1,match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            ResultSet rs = preparedStatement.getGeneratedKeys(); // permet de retourner toutes les valeurs auto générer

            if ( rs.next()){
                match.setId(rs.getLong(1));
            }

            System.out.println("match créé");

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
