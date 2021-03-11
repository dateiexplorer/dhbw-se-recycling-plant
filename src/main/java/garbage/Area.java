package garbage;

import control.ControlCenter;
import dimensions.Dimension2D;

/**
 * The area of the recycling plant, that is filled successively with garbage form a garbage store (e.g. a recycling
 * vehicle.
 */
public class Area {

    private ControlCenter center;
    private Dimension2D dimension;

    private char[][] garbage;

    public Area(ControlCenter center, Dimension2D dimension) {
        this.center = center;
        this.dimension = dimension;
    }

    /**
     * Each call fills the area with a cargo of garbage from the registered garbage store.
     * @return The cargo of the garbage store.
     */
    public char[][] fill() {
        center.fillArea();
        return garbage;
    }

    /**
     * Returns the associated character of the garbage at the given position.
     * @param position - The position of the garbage in the area.
     * @return The associated character of the garbage.
     */
    public char getGarbageIDAtPosition(Dimension2D position) {
        return garbage[position.getLength()][position.getWidth()];
    }

    // Getter and setter
    public Dimension2D getDimension() {
        return dimension;
    }

    public void setGarbage(char[][] garbage) {
        this.garbage = garbage;
    }

    public char[][] getGarbage() {
        return garbage;
    }

}
