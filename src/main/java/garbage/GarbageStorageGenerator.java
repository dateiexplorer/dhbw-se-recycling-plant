package garbage;

import dimensions.Dimension3D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A helper class to generate a garbage storage.
 */
public class GarbageStorageGenerator {

    /**
     * Generates a random storage with the given dimensions and types of garbage
     * @param dimension - The dimensions of the generated garbage.
     * @param types - The types of garbage that are put into the garbage storage.
     * @return Returns a randomly generated storage with the given dimensions and types.
     */
    public static char[][][] generateRandomStorage(Dimension3D dimension, Garbage[] types) {
        // Get the total amount of garbage.
        int numberOfGarbage = dimension.getSize();

        // Generate storage.
        char[][][] storage = new char[dimension.getLength()][dimension.getWidth()][dimension.getHeight()];
        Iterator<Character> garbage = shuffle(generateListForTypes(types, numberOfGarbage)).iterator();
        for (int l = 0; l < dimension.getLength(); l++) {
            for (int w = 0; w < dimension.getWidth(); w++) {
                for (int h = 0; h < dimension.getHeight(); h++) {
                    storage[l][w][h] = garbage.next();
                }
            }
        }

        return storage;
    }

    /**
     * Helper method that generations a sorted list of types, which are distributed as a percentage.
     * @param types - The types, that should be generated.
     * @param numberOfGarbage - The total amount of garbage (different types, distributed as a percentage)
     * @return Returns a sorted list of garbage with the given size, distributed as a percentage.
     */
    public static List<Character> generateListForTypes(Garbage[] types, int numberOfGarbage) {
        // Generate a list with total garbage.
        List<Character> garbage = new ArrayList<Character>();
        for (Garbage type : types) {
            int num = (int) (numberOfGarbage * type.getPercentage());
            garbage.addAll(generateListForType(type, num));
        }

        return garbage;
    }

    /**
     * Generates a list for the given type and size.
     * @param type - The type the list contains.
     * @param numberOfGarbage - The number of garbage (the size of the list)
     * @return Returns a list of garbage with the given type and size.
     */
    public static List<Character> generateListForType(Garbage type, int numberOfGarbage) {
        List<Character> garbage = new ArrayList<>();
        for (int i = 0; i < numberOfGarbage; i++) {
            garbage.add(type.getID());
        }

        return garbage;
    }

    /**
     * Shuffles a list of garbage types.
     * @param list - The list to shuffle.
     * @return Returns the shuffled list.
     */
    public static List<Character> shuffle(List<Character> list) {
        Collections.shuffle(list);
        return list;
    }
}
