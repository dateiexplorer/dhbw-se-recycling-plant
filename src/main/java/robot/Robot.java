package robot;

import control.ControlCenter;
import dimensions.Dimension2D;

import java.util.List;
import java.util.Queue;

/**
 * A robot which could be controlled by commands.
 */
public class Robot {

    private Dimension2D position;
    private boolean active;
    private int id;

    private Queue<List<IRobotCommand>> commandQueue;
    private ControlCenter center;

    // Initial the garbage is empty ('#').
    private char garbage = '#';

    public Robot(ControlCenter center, int id) {
        this.center = center;
        this.id = id;
    }

    /**
     * Set a command set.
     * @param commandQueue - The commands, which should be executes successively.
     */
    public void setCommandQueue(Queue<List<IRobotCommand>> commandQueue) {
        this.commandQueue = commandQueue;
    }

    /**
     * Each call of this methods let execute a robot a set of commands.
     */
    public void batch() {
        for (IRobotCommand command : commandQueue.poll()) {
            command.execute();
        }
    }

    /**
     * Take the garbage from the current position.
     */
    public void take() {
        center.takeGarbage(id);
    }

    /**
     * Put the garbage in the corresponding box, if the position equals the position of the box.
     */
    public void put() {
        center.putGarbage(id);
    }

    // Getter and setter
    public int getID() {
        return id;
    }

    public void setPosition(Dimension2D position) {
        this.position = position;
    }

    public Dimension2D getPosition() {
        return position;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setGarbage(char garbage) {
        this.garbage = garbage;
    }

    public char getGarbage() {
        return garbage;
    }

    public Queue<List<IRobotCommand>> getCommandQueue() {
        return commandQueue;
    }
}
