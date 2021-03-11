package robot;

/**
 * Put the garbage at the corresponding box.
 */
public class PutCommand implements IRobotCommand {

    private Robot robot;

    public PutCommand(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void execute() {
        System.out.println("PutCommand: Robot " + robot.getID() + " puts " + robot.getGarbage());
        robot.put();
    }
}
