package ecobike.database_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Create connection to MySQL Database
 */
public class MySQLConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecobike";
    private static final String USER_NAME = "root";

    // CHANGE PASSWORD HERE
    private static final String PASSWORD = "";
    private static final Connection conn = getConnection();

    /**
     * Create connection to MySQL Database
     * @return Connection object
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (Exception ex) {
            System.out.println("Connect failed!");
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * Excecute a command
     * @param command: command to execute
     */
    public static void execute(String command){
        System.out.println("Executing command: \n" + command);
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(command);
            System.out.println("Successfully executed command: " + command);
        } catch (Exception e){
            System.out.println("Failed to execute command: \n" + command);
            e.printStackTrace();
        }
    }

    /**
     * Execute a query and return an ArrayList<ArrayList<String>> object
     *
     * @param command: command to execute
     * @return query result
     */
    public static ArrayList<ArrayList<String>> query(String command){
        System.out.println("Executing query: \n" + command);
        try{
            ArrayList<ArrayList<String>> queryResults = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            int numberOfColumn = rs.getMetaData().getColumnCount();
            while (rs.next()){
                ArrayList<String> s = new ArrayList<>();
                for(int i = 1; i <= numberOfColumn; ++i)
                    s.add(rs.getString(i));
                queryResults.add(s);
            }
            System.out.println("Successfully executed command!");
            return queryResults;
        }catch (Exception e){
            System.out.println("Successfully executed command!");
            e.printStackTrace();
            return null;
        }
    }
}