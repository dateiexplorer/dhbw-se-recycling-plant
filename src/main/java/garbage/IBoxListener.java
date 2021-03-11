package garbage;

/**
 * An interface that should be implemented by all classes that listen to box events.
 * This is not described by the specification but is necessary to avoid full boxes during simulation.
 */
public interface IBoxListener {

    /**
     * Called if the corresponding box is full.
     * @param box - The corresponding box.
     */
    void onBoxIsFull(Box box);
}
