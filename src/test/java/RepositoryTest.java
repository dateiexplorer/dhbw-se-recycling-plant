import control.Repository;
import dimensions.Dimension2D;
import garbage.Garbage;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    @Test
    @Order(1)
    public void testRepositoryEntry_shouldHavaAnEntry() {
        Repository repo = new Repository();
        repo.onClassify(Garbage.METAL, new Dimension2D(5, 10));

        Dimension2D position = new Dimension2D(5, 10);
        assertTrue(repo.getData().get(Garbage.METAL).get(0).equals(position));
    }
}
