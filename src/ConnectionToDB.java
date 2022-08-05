
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToDB {
    //JDBC and database properties.
    private static final String DB_DRIVER =
            "com.mysql.jdbc.Driver";
    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/library";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "57247";

    static Connection connection =null;
    public static Connection getConnection()
    {
        if (connection != null) return connection;
        // get db, user, pass
        return getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    private static Connection getConnection(
            String db_name,String user_name,String password)
    {
        try
        {

            connection = DriverManager.
                    getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return connection;
    }



}


