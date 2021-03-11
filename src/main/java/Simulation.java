import camera.Camera;
import control.ControlCenter;
import control.Repository;
import dimensions.Dimension2D;
import dimensions.Dimension3D;
import garbage.Area;
import garbage.Box;
import garbage.Garbage;
import garbage.GarbageStorage;
import garbage.BoxEmptier;
import robot.Robot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Simulation {

    private Dimension3D storageSize;
    private List<Dimension2D> robotStartPositions;

    private ControlCenter center;
    private GarbageStorage storage;
    private Area area;

    private Camera camera;
    private Repository repository;

    public Simulation(Dimension3D storageSize, List<Dimension2D> robotStartPositions) {
        this.storageSize = storageSize;
        this.robotStartPositions = robotStartPositions;
        init();
    }

    public void init() {
        center = new ControlCenter();

        // Create new storage.
        storage = new GarbageStorage(
                storageSize,
                Garbage.values());
        center.registerStorage(storage);

        // Create new area.
        area = new Area(center,
                new Dimension2D(storageSize.getWidth(), storageSize.getHeight()));
        center.registerArea(area);

        // Create camera.
        camera = new Camera(center);
        center.registerCamera(camera);

        // Create new repository.
        repository = new Repository();
        center.registerRepository(repository);

        // Create robots and Boxes for each robot.
        for (int i = 0; i < robotStartPositions.size(); i++) {
            center.registerRobot(new Robot(center, i),
                    createBoxesAtPosition(robotStartPositions.get(i)));
        }

        // Add repository as listener for camera events.
        camera.addListener(repository);
    }

    // Helper function to create a set of boxes at a specific position.
    private static Map<Character, Box> createBoxesAtPosition(Dimension2D position) {
        Map<Character, Box> boxes = new HashMap<>();
        for (Garbage type : Garbage.values()) {
            Box box = new Box(1000, position);
            boxes.put(type.getID(), box);

            // Add an emptier to each box. This is not described in the specification, but is necessary to run
            // the simulation.
            box.addListener(new BoxEmptier());
        }

        boxes.get(Garbage.WASTE.getID()).setCapacity(5000);
        return boxes;
    }

    public void iterateAll() {
        int iteration = 1;
        boolean hasNext = true;

        while (hasNext) {
            System.out.println("--- Iteration: " + iteration);
            hasNext = iterate();
            if (!hasNext) {
                System.out.println("All done!");
            }

            iteration++;
        }
    }

    public boolean iterate() {
        if (area.fill() == null) {
            return false;
        }

        camera.classifyAll();
        center.schedule(Configuration.instance.partialAreaSize);

        for (Robot robot : center.getRobots().values()) {
            robot.batch();
        }

        return true;
    }

    // Getter and setter
    public Dimension3D getStorageSize() {
        return storageSize;
    }

    public List<Dimension2D> getRobotStartPositions() {
        return robotStartPositions;
    }

    public ControlCenter getControlCenter() {
        return center;
    }

    public GarbageStorage getStorage() {
        return storage;
    }

    public Area getArea() {
        return area;
    }

    public Camera getCamera() {
        return camera;
    }

    public Repository getRepository() {
        return repository;
    }
}
