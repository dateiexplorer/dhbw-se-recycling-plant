package robot;

/**
 * Take the garbage with a robot from the current position.
 */
public class TakeCommand implements IRobotCommand {

    private Robot robot;

    public TakeCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.take();
        System.out.println("TakeCommand: Robot " + robot.getID() + " takes " + robot.getGarbage());
    }
}
