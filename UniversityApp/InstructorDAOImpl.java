import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAOImpl implements InstructorDAO {
    static final String DB_URL = "jdbc:mysql://localhost:3306/university"; // Replace with your DB URL
    static final String USER = "root"; // Replace with your username
    static final String PASS = "ZEeh8714!"; // Replace with your password

    /*
    Support the ff. operations
    - add, update, delete, search based on selected attribute, or listing of (all) instructor records */
    // This is where the SQL queries lie... If not success on SQL query >>> throw exception

    // Succesfull DB add operation
    @Override
    public boolean add(Instructor i) {
        String sql = "INSERT INTO instructor(ID,name,dept_name,salary) VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);){
            
            // This function is used for an 'add' operation
            ps.setString(1, i.getID());
            ps.setString(2, i.getName());
            ps.setString(3, i.getDeptName());
            ps.setDouble(4, i.getSalary());
            ps.executeUpdate(); 
            return true;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(Instructor i) {
        String sql = "UPDATE instructor SET name=?, dept_name=?, salary=? WHERE ID=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);){
            
            // This function is used for an 'add' operation
            ps.setString(1, i.getName());
            ps.setString(2, i.getDeptName());
            ps.setDouble(3, i.getSalary());
            ps.setString(4, i.getID());
            
            // Returns true if only one affected
            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM instructor WHERE ID = ?";
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);

            return ps.executeUpdate() == 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Instructor> search(String searchType, String value) {
        List<Instructor> results = new ArrayList<>();
        String sql = "SELECT * FROM instructor WHERE " + searchType + " LIKE ?";
        
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if(searchType.equals("salary")) {
                ps.setDouble(1, Double.parseDouble(value));
            } else {
                ps.setString(1, value);
            }

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                results.add(new Instructor(rs.getString("ID"), rs.getString("name"), 
                    rs.getString("dept_name"), rs.getDouble("salary")));
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }


}
