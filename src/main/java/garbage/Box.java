package garbage;

import dimensions.Dimension2D;

import java.util.ArrayList;
import java.util.List;

/**
 * A Box, which holds garbage. Should used to sort the garbage based on his type.
 */
public class Box {

    private Dimension2D position;
    private int capacity;

    private int filled;
    private char[] storage;

    private List<IBoxListener> listeners;

    public Box(int capacity, Dimension2D position) {
        storage = new char[capacity];
        this.position = position;

        listeners = new ArrayList<>();
        empty();
    }

    /**
     * Add a new listener.
     * @param listener - The listener that should listen to box events.
     */
    public void addListener(IBoxListener listener) {
        listeners.add(listener);
    }

    /**
     * Remove the listener.
     * @param listener - The listener that should not listen to the box events anymore.
     */
    public void removeListener(IBoxListener listener) {
        listeners.remove(listener);
    }

    /**
     * Empties a box.
     */
    public void empty() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = '#';
        }

        filled = 0;
    }

    /**
     * Fill the box with one piece of garbage.
     * @param garbage - The piece of garbage.
     * @return Returns false if the garbage is full.
     */
    public boolean fill(char garbage) {
        if (filled == storage.length) {
            for (IBoxListener listener : listeners) {
                listener.onBoxIsFull(this);
            }

            return false;
        }

        storage[filled] = garbage;
        filled++;
        return true;
    }

    // Getter and setter
    public Dimension2D getPosition() {
        return position;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFilled() {
        return filled;
    }

    public char[] getStorage() {
        return storage;
    }
}
