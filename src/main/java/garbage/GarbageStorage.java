package garbage;

import dimensions.Dimension3D;

/**
 * The representation of a garbage storage (e.g. a recycling vehicle).
 */
public class GarbageStorage {

    private char[][][] storage;
    private int next = 0;

    public GarbageStorage(Dimension3D dimension, Garbage[] types) {
        storage = fill(dimension, types);
    }

    /**
     * Fills the garbage storage with an amount a garbage.
     * @param dimension - The dimension of the amount of garbage.
     * @param types - The types of garbage, which should generate.
     * @return Returns a random generated garbage storage with the given dimension and types.
     */
    public char[][][] fill(Dimension3D dimension, Garbage[] types) {
        next = 0;
        return GarbageStorageGenerator.generateRandomStorage(dimension, types);
    }

    /**
     * Empty successive the storage and returns the cargo.
     * @return Returns the next cargo emptied form the storage.
     */
    public char[][] getNextCargo() {
        if (next >= storage.length) {
            return null;
        }

        return storage[next++];
    }
}
