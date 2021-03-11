package control;

import dimensions.Dimension2D;
import garbage.Garbage;
import robot.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * The scheduler schedules the commands for each robot based on the data in the repository.
 */
public class Scheduler {

    private List<PartialArea> partialAreas;
    private ControlCenter center;

    public Scheduler(ControlCenter center) {
        this.center = center;
    }

    /**
     * Schedule all robots based on the data in the repository and the size of an partial area (the area handled by
     * a robot at on iteration.
     * @param partialAreaSize
     */
    public void schedule(Dimension2D partialAreaSize) {
        partialAreas = new ArrayList<>();

        // Get the number of partial areas to create.
        int xPartials = (center.getArea().getDimension().getLength() / partialAreaSize.getLength());
        int yPartials = (center.getArea().getDimension().getWidth() / partialAreaSize.getWidth());

        // Get all positions from each partial area.
        int id = 0;
        for (int x = 0; x < xPartials; x++) {
            for (int y = 0; y < yPartials; y++) {
                partialAreas.add(new PartialArea(id,
                        new Dimension2D(x * partialAreaSize.getLength(), y * partialAreaSize.getWidth()),
                        new Dimension2D(partialAreaSize.getLength(), partialAreaSize.getWidth())));
                id++;
            }
        }

        distributePartialAreasToRobots(
                center.getRobots().values().stream().collect(Collectors.toList()), partialAreas);
    }

    private void distributePartialAreasToRobots(List<Robot> robots, List<PartialArea> areas) {
        int numberOfRobots = robots.size();
        int numberOfAreasToHandle = areas.size() / numberOfRobots;
        for (int i = 0; i < numberOfRobots; i++) {
            Robot robot = robots.get(i);

            List<PartialArea> areasToHandle = areas.subList(
                    numberOfAreasToHandle * i, numberOfAreasToHandle * (i + 1));

            robot.setCommandQueue(createCommandQueueForRobot(robot, areasToHandle));
        }
    }

    private Queue<List<IRobotCommand>> createCommandQueueForRobot(Robot robot, List<PartialArea> areas) {
        Queue<List<IRobotCommand>> commandQueue = new LinkedList<>();
        for (PartialArea area : areas) {
            commandQueue.add(createCommandsForArea(robot, area));
        }

        return commandQueue;
    }

    private List<IRobotCommand> createCommandsForArea(Robot robot, PartialArea area) {
        // Commands for one iteration for the current partial area.
        List<IRobotCommand> commands = new ArrayList<>();

        // Start robot
        commands.add(new StartCommand(robot));

        // For each garbage type...
        for (Garbage garbage : Garbage.values()) {
            // Get each position, which contains this garbage type and is in the current partial area.
            List<Dimension2D> positions = center.getRepository().getData().get(garbage).stream()
                    .filter(p -> area.getPositions().contains(p)).collect(Collectors.toList());

            // For each position...
            for (Dimension2D position : positions) {
                commands.add(new MoveCommand(robot, position));
                commands.add(new TakeCommand(robot));
                commands.add(new MoveCommand(robot,
                        center.getBoxes().get(robot.getID()).get(garbage.getID()).getPosition()));
                commands.add(new PutCommand(robot));
            }
        }

        // Shutdown robot
        commands.add(new ShutdownCommand(robot));
        return commands;
    }
}

