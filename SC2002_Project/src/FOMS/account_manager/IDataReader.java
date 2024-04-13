
import java.io.IOException;
import java.util.List;

public interface IDataReader {
    List<Staff> readStaffList(String filename) throws IOException;
    List<Branch> readBranchList(String filename) throws IOException;
    List<Menu> readMenuList(String filename) throws IOException;
}
