import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector {
    // Database connection details
    static final String DB_URL = "jdbc:mysql://localhost:3306/university"; // Replace with your DB URL
    static final String USER = "root"; // Replace with your username
    static final String PASS = ""; // Replace with your password
    
    static final String Q1 = "SELECT * FROM student"; // Replace with your SQL query
    static final String Q2 = "SELECT * FROM student JOIN takes ON student.ID=takes.ID";

    public static void main(String[] args) {
        // Use try-with-resources for automatic closing of resources
        /*
            Pattern usage:
            - conn = DriverManager
            - Statement = conn.createStatement()
            - ResultSet = 
         */
        // Use different statements per result-set
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Q1);) {

            System.out.println("Connecting to database and creating statement...");

            // Extract data from result set
            System.out.println("============");
            System.out.println("Easy query");
            System.out.println("============");
            while (rs.next()) { // Until empty
                // Retrieve by column name
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                double totCred = rs.getDouble("tot_cred");

                // Display values
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.print(", Total Credits: " + totCred);
                System.out.print("\n");
            }
        } catch 
        (SQLException e) {
            // Handle errors for JDBC
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(Q2)) {
                
                System.out.println("==========");
                System.out.println("JOIN query");
                System.out.println("==========");
                while(rs.next()) {
                    String name = rs.getString("name");
                    String courseId = rs.getString("course_id");

                    System.out.println(name + " is taking the ff. course: " + courseId);
                }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection closed and resources freed.");
    }
}

