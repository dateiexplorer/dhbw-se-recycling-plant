package robot;

/**
 * Start the robot.
 */
public class StartCommand implements IRobotCommand {

    private Robot robot;

    public StartCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.setActive(true);
        System.out.println("StartCommand: Start robot " + robot.getID() + " (active = " + robot.isActive() + ")");
    }
}
