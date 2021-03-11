package garbage;

/**
 * A simulated abstract emptier, that empties a full box.
 * This is not described in the specification but is necessary to run the simulation.
 */
public class BoxEmptier implements IBoxListener {

    @Override
    public void onBoxIsFull(Box box) {
        box.empty();
    }
}
