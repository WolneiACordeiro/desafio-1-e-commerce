package e_commerce.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Connect {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/ecommerceCart";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_NAME = "ecommerceCart";

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public Connect() {
        createDatabaseIfNotExists();
    }
    public boolean connectDatabase() {
        boolean result = true;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + "?useUnicode=true&characterEncoding=UTF-8", USER, PASSWORD);
            statement = connection.createStatement();

        } catch (ClassNotFoundException driverEx) {
            JOptionPane.showMessageDialog(null, "Driver not found!: " + driverEx);
            result = false;
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + sqlEx);
            result = false;
        }
        return result;
    }

    public void disconnect() {
        try {
            statement.close();
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to close the database connection: " + ex);
        }
    }

    public void createDatabaseIfNotExists() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASSWORD);

            String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement = connection.createStatement();
            statement.executeUpdate(createDatabaseSql);

            connection.close();

            String urlWithDatabase = "jdbc:mysql://localhost/" + DATABASE_NAME;
            connection = DriverManager.getConnection(urlWithDatabase, USER, PASSWORD);
            statement = connection.createStatement();

            String createProductTableSql = "CREATE TABLE IF NOT EXISTS Product " +
                    "(id INT AUTO_INCREMENT, " +
                    "nameProduct VARCHAR(150), " +
                    "price DOUBLE, " +
                    "quantity INT, " +
                    "PRIMARY KEY(id))";
            statement.executeUpdate(createProductTableSql);

        } catch (ClassNotFoundException driverEx) {
            JOptionPane.showMessageDialog(null, "Driver not found!: " + driverEx);
        } catch (SQLException sqlEx) {
            JOptionPane.showMessageDialog(null, "Error creating the database: " + sqlEx);
        }
    }

    public void executeSQL(String sql) {
        if (connectDatabase()) {
            try {
                statement.execute(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error executing SQL query: " + ex);
            } finally {
                disconnect();
            }
        }
    }

    public ResultSet executeQuery(String sql) {
        ResultSet resultSet = null;
        if (connectDatabase()) {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error executing SQL query: " + ex);
            }
        }
        return resultSet;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        if (connectDatabase()) {
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        }
        return null;
    }
}
