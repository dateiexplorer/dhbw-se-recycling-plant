package control;

import garbage.Area;
import garbage.Box;
import garbage.GarbageStorage;
import camera.Camera;
import dimensions.Dimension2D;
import robot.Robot;

import java.util.HashMap;
import java.util.Map;

/**
 * The control center (mediator class) manages the communication between all objects that are affected by the
 * recycling plant.
 * It also schedules the commands for each robot.
 */
public class ControlCenter {

    private Repository repository;
    private Camera camera;
    private Area area;
    private GarbageStorage storage;
    private Map<Integer, Robot> robots;
    private Map<Integer, Map<Character, Box>> boxes;

    private Scheduler scheduler;

    public ControlCenter() {
        robots = new HashMap<>();
        boxes = new HashMap<>();

        // The control center schedules the robots directly.
        scheduler = new Scheduler(this);
    }

    /**
     * Register the repository, which holds all classifying data from the camera to schedule the commands for the
     * robots.
     * @param repository - The repository, which holds the data.
     */
    public void registerRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * Register the camera, which classifies the garbage and stores the results to the repository.
     * @param camera - The camera of the recycling plant.
     */
    public void registerCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Register the area, which will filled successively with garbage from a recycling vehicle.
     * @param area - The area of the recycling plant.
     */
    public void registerArea(Area area) {
        this.area = area;
    }

    /**
     * Register the storage (e.g. form a recycling vehicle), that will successively empty to
     * the area to recycle the garbage.
     * @param storage - A storage full of garbage.
     */
    public void registerStorage(GarbageStorage storage) {
        this.storage = storage;
    }

    /**
     * Register a robot, which sorts the garbage in an area to corresponding boxes.
     * @param robot - The robot.
     * @param boxes - The boxes, the robot should sort the garbage into.
     */
    public void registerRobot(Robot robot, Map<Character, Box> boxes) {
        // Every robot has a corresponding box chain.
        robots.put(robot.getID(), robot);
        this.boxes.put(robot.getID(), boxes);
    }

    /**
     * Schedules the robots and set up a command queue to each robot, that the whole area will be managed.
     * @param partialAreaSize - The size of the area, which should handle by one robot at one iteration.
     */
    public void schedule(Dimension2D partialAreaSize) {
        scheduler.schedule(partialAreaSize);
    }

    /**
     * Each time this method is called, the area is filled with a new cargo from the garbage storage.
     */
    public void fillArea() {
        char[][] cargo = storage.getNextCargo();
        area.setGarbage(cargo);
    }

    /**
     * Called by the camera to classify the whole area of garbage.
     */
    public void classifyAll() {
        for (int i = 0; i < area.getDimension().getSize(); i++) {
            camera.classify();
        }

        // Reset the camera for the next iteration.
        camera.reset();
    }

    /**
     * Called by the camera to classify the garbage at the cameras current position.
     */
    public void classify() {
        // Get the current position of the camera on the area.
        Dimension2D currentPos = camera.getPosition();
        camera.setType(area.getGarbageIDAtPosition(currentPos));

        // The camera scans each line from up to bottom.
        if (currentPos.getLength() < area.getGarbage().length - 1) {
            currentPos.setLength(currentPos.getLength() + 1);
        } else {
            currentPos.setLength(0);
            currentPos.setWidth(currentPos.getWidth() + 1);
        }

        // Set the id (char) from the current position.
        camera.setPosition(currentPos);
    }

    /**
     * Called by a robot to take the garbage from the area at the robots position.
     * @param id - The id of the robot.
     */
    public void takeGarbage(int id) {
        Robot robot = robots.get(id);
        robot.setGarbage(area.getGarbageIDAtPosition(robot.getPosition()));
    }

    /**
     * Called by a robot to put the garbage into the corresponding box.
     * @param id - The id of the robot.
     */
    public void putGarbage(int id) {
        Robot robot = robots.get(id);
        Box box = boxes.get(id).get(robot.getGarbage());

        // Check if the robot is at the position of the corresponding box to put the garbage into it.
        if (robot.getPosition() == box.getPosition()) {
            box.fill(robot.getGarbage());
            robot.setGarbage('#');
        }
    }

    // Getter and setter
    public Repository getRepository() {
        return repository;
    }

    public Area getArea() {
        return area;
    }

    public Map<Integer, Robot> getRobots() {
        return robots;
    }

    public Map<Integer, Map<Character, Box>> getBoxes() {
        return boxes;
    }
}
