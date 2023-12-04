package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

    public static Connection connection = null;
    private Statement statement = null;

    public void connectToDB() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/polyclinic","root", "12345678");
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*public static Connection connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered");
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/polyclinic", "root", "12345678");

        }catch (ClassNotFoundException e){
            System.out.println("Can't find Driver");
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("SQLException " + e);
        }
        return connection;
    }*/
    public static void close() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Closing connection");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection!");
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }


}
