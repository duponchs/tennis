package fr.stephane.tennis.core.DAO;

import fr.stephane.tennis.core.DataSourceProvider;
import fr.stephane.tennis.core.entity.Score;


import javax.sound.sampled.Control;
import javax.sql.DataSource;
import java.sql.*;

public class ScoreDAOImpl {
    public void create(Score score){
        Connection con = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            con = dataSource.getConnection();

            PreparedStatement preparedStatement  = con.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, score.getMatch().getId());

            if (score.getSet1() == null){
                preparedStatement.setNull(2, Types.TINYINT);
            }else{
                preparedStatement.setByte(2, score.getSet1());
            }

            if (score.getSet2() == null){
                preparedStatement.setNull(3, Types.TINYINT);
            }else{
                preparedStatement.setByte(3, score.getSet2());
            }

            if (score.getSet3() == null){
                preparedStatement.setNull(4, Types.TINYINT);
            }else{
                preparedStatement.setByte(4, score.getSet3());
            }

            if (score.getSet4() == null){
                preparedStatement.setNull(5, Types.TINYINT);
            }else{
                preparedStatement.setByte(5, score.getSet4());
            }

            if (score.getSet5() == null){
                preparedStatement.setNull(6, Types.TINYINT);
            }else{
                preparedStatement.setByte(6, score.getSet5());
            }

            preparedStatement.executeUpdate(); // retourne le nombre d'élement, mise a jour

            ResultSet rs = preparedStatement.getGeneratedKeys(); // permet de retourner toutes les valeurs auto générer

            if ( rs.next()){
                score.setId(rs.getLong(1));
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
