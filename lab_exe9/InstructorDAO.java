import java.util.List;

public interface InstructorDAO {
    // This is an interface
    // How would you want to implement an InstructorDAO
    
    // Use boolean (returns OK or not OK)
    boolean add(Instructor instructor);
    boolean update(Instructor instructor);
    boolean delete(String id);
    List<Instructor> search(String search_type, String value);
    /* // Needs the whole instructor --- what kind of
    boolean listAll();*/
}
