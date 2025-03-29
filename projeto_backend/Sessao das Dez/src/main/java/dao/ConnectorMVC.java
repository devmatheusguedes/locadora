package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorMVC {
    private final String senha = "root"; // lomg int double float Date Integer String Boolean
    private final String user = "postgres";
    private final String url = "jdbc:postgresql://localhost:5432/locadora";

    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        try{
            connection = DriverManager.getConnection(url, user, senha);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return connection;
    }
}
