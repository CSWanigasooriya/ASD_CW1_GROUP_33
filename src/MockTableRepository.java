import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTableRepository implements TableRepository {

    private final Map<String, Table> tableStore = new HashMap<>();

    public MockTableRepository() {
        // Populate some dummy tables
        Table t1 = new Table("1", 101, 4, "Window");
        Table t2 = new Table("2", 102, 2, "Patio");
        Table t3 = new Table("3", 103, 6, "VIP");

        tableStore.put(t1.getTableId(), t1);
        tableStore.put(t2.getTableId(), t2);
        tableStore.put(t3.getTableId(), t3);
    }

    @Override
    public Table findById(String id) {
        return tableStore.get(id);
    }

    @Override
    public List<Table> findAll() {
        return new ArrayList<>(tableStore.values());
    }

    @Override
    public Table lockTable(String id) {
        // Simulate a lock by just returning the table
        // In real scenario: use concurrency control
        return tableStore.get(id);
    }
}