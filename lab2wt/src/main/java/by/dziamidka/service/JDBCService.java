package by.dziamidka.service;

import by.dziamidka.exception.DatabaseException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCService {

    private final Logger logger = Logger.getLogger(JDBCService.class);

    private Connection connection;
    private boolean isInited = false;

    private JDBCService() {
    }

    public void init(String url, String user, String password) throws DatabaseException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("Connection to " + url + " successful");
            isInited = true;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Connection to " + url + " failed " + e);
            isInited = false;
            throw new DatabaseException(e.getMessage());
        }
    }

    private static class SingletonHandler {
        static final JDBCService INSTANCE = new JDBCService();
    }

    public static JDBCService getInstance() {
        return JDBCService.SingletonHandler.INSTANCE;
    }

    public Connection getConnection() throws DatabaseException {
        if (isInited)
            return connection;
        else
            throw new DatabaseException("JdbcService not initialized");
    }

}
