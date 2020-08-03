package fr.stephane.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance(){
        if ( singleDataSource == null){
            singleDataSource = new BasicDataSource();
            singleDataSource.setInitialSize(5); //5 connexions sont ouvertes pour notre projet et mi dans le pool
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");
            singleDataSource.setUsername("root");
            singleDataSource.setPassword("root");
        }
        return singleDataSource;
    }
}
