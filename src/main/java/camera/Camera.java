package camera;

import control.ControlCenter;
import garbage.Garbage;
import dimensions.Dimension2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The camera scans the area and classifies the garbage types.
 * The classified type and the corresponding position is send as an event to the listeners.
 */
public class Camera {

    private List<ICameraListener> listeners;
    private char type;
    private Dimension2D position;

    private ControlCenter center;

    public Camera(ControlCenter center) {
        this.center = center;
        listeners = new ArrayList<>();

        reset();
    }

    /**
     * Add a new event listener.
     * @param listener - A listener for the camera events.
     */
    public void addListener(ICameraListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove the event listener.
     * @param listener - The listener to remove from the camera events.
     */
    public void removeListener(ICameraListener listener) {
        listeners.remove(listener);
    }

    /**
     * Reset the camera to the position 0x0.
     */
    public void reset() {
        position = new Dimension2D(0, 0);
    }

    /**
     * The camera scans the whole area and classifies the garbage types.
     */
    public void classifyAll() {
        center.classifyAll();
    }

    /**
     * Classifies the garbage type of the garbage at the cameras position.
     */
    public Garbage classify() {
        Dimension2D currentPos =  (Dimension2D) position.clone();
        center.classify();

        // The id (char) type for the next garbage is set.
        // Find the corresponding garbage type.
        Garbage classifiedType = Arrays.stream(Garbage.values()).filter(g -> g.getID() == type).findFirst().get();

        // Inform the listeners.
        for (ICameraListener listener : listeners) {
            listener.onClassify(classifiedType, currentPos);
        }

        return classifiedType;
    }

    // Getter and setter
    public void setType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public void setPosition(Dimension2D position) {
        this.position = position;
    }

    public Dimension2D getPosition() {
        return position;
    }
}
