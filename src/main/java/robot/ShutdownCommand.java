package robot;

/**
 * Shutdown the robot.
 */
public class ShutdownCommand implements IRobotCommand {

    private Robot robot;

    public ShutdownCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.setActive(false);
        System.out.println("StartCommand: Shutdown robot " + robot.getID() + " (active = " + robot.isActive() + ")");
    }
}
