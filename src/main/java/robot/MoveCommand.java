package robot;

import dimensions.Dimension2D;

/**
 * Move a robot to a given position.
 */
public class MoveCommand implements IRobotCommand {

    private Robot robot;
    private Dimension2D position;

    public MoveCommand(Robot robot, Dimension2D position) {
        this.robot = robot;
        this.position = position;
    }

    @Override
    public void execute() {
        robot.setPosition(position);
        System.out.println("MoveCommand: Move robot " + robot.getID() + " to position " + position);
    }
}
