package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseDAOImp<Entity> implements BaseDAO<Entity> {
    static final String url = "jdbc:postgresql://localhost:5432/FS_dados";
    static final String user = "postgres";
    static final String password = "0517";
    static Connection connection = null;
    
    public static Connection getConnection() {
	if(connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	return connection;
    }
    
    public static void closeConnection() {
	if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(BaseDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = null;
	}
    }
}
