package ecobike.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Tạo connection với cơ sở dũ liệu và thực hiện các command và query
 */
public class MySQLDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecobike";
    private static final String USER_NAME = "root";

    // CHANGE PASSWORD HERE
    private static final String PASSWORD = "";
    private static Connection conn = getConnection();

    /**
     * Tạo connection với cơ sở dữ liệu
     * @return Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * Thực thi một lệnh
     * @param command: lệnh thực thi
     */
    public static void execute(String command){
        System.out.println("Executing command: \n" + command);
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(command);
            System.out.println("Successfully execute command: " + command);
        } catch (Exception e){
            System.out.println("Fail to execute command: \n" + command);
            e.printStackTrace();
        }
    }

    /**
     * Thực thi một lệnh  và trả về một mảng hai chiều ứng với bảng trong cơ sở dữ liệu,
     * mảng hai chiều được biểu diễn theo dạng ArrayList[ArrayList[String]]
     *
     * @param command: lệnh thực thi
     * @return mảng hai chiều chứa kết quả query
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
            System.out.println("Successfully execute command!");
            return queryResults;
        }catch (Exception e){
            System.out.println("Successfully execute command!");
            e.printStackTrace();
            return null;
        }
    }
//    public static ArrayList<ArrayList<String>> getRentingBikes(){
//        ArrayList<ArrayList<String>> s = new ArrayList<>();
//        String command = "SELECT  * FROM parkinglot";
//        s = MySQLDB.query(command);
//        return s;
//    }
//
//    public static void main(String[] args) {
//        ArrayList<ArrayList<String>> s= MySQLDB.getRentingBikes();
//        System.out.println(s);
//    }
}