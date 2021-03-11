import camera.Camera;
import control.ControlCenter;
import control.PartialArea;
import control.Repository;
import dimensions.Dimension2D;
import garbage.Area;
import garbage.Garbage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CameraTest {

    private ControlCenter center;
    private Area area;
    private Camera camera;
    private Repository repository;

    private char[][] data;

    @BeforeEach
    public void init() {
        center = new ControlCenter();

        area = new Area(center, new Dimension2D(5, 5));
        center.registerArea(area);

        data = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                data[i][j] = Garbage.METAL.getID();
            }
        }

        area.setGarbage(data);

        camera = new Camera(center);
        center.registerCamera(camera);

        repository = new Repository();
        center.registerRepository(repository);

        camera.addListener(repository);
    }

    @Test
    @Order(1)
    public void testClassifyGarbage_shouldBeSameType() {
        Garbage type = camera.classify();
        assertEquals(Garbage.METAL, type);
    }

    @Test
    @Order(2)
    public void testRepositoryData_dataShouldBeEqual() {
        camera.classify();

        boolean stored = repository.getData().get(Garbage.METAL).contains(new Dimension2D(0, 0));
        assertTrue(stored);
    }

    @Test
    @Order(3)
    public void testRepositoryData_shouldContainAllPositions() {
        camera.classifyAll();

        // Generate all positions.
        PartialArea partialArea = new PartialArea(0,
                new Dimension2D(0, 0), new Dimension2D(5, 5));

        // Merge all data to one list.
        List<Dimension2D> list = new ArrayList<>();
        for (List<Dimension2D> l : repository.getData().values()) {
            list.addAll(l);
        }

        assertTrue(list.containsAll(partialArea.getPositions()));
    }
}
