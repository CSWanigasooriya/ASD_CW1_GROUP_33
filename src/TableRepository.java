import java.util.List;

public interface TableRepository {
    Table findById(String id);
    List<Table> findAll();
    Table lockTable(String id);
}
