import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import dimensions.Dimension3D;
import garbage.Garbage;
import garbage.GarbageStorageGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GarbageStorageGeneratorTest {

    @Test
    @Order(1)
    public void testListGeneration_shouldHaveRightSize() {
        List<Character> list = GarbageStorageGenerator.generateListForType(Garbage.METAL, 10);
        assertEquals(10, list.size());
    }

    @Test
    @Order(2)
    public void testListGeneration_shouldHaveRightType() {
        List<Character> list = GarbageStorageGenerator.generateListForType(Garbage.METAL, 10);
        for (Character c : list) {
            assertEquals(Garbage.METAL.getID(), c);
        }
    }

    @Test
    @Order(3)
    public void testListGenerationForMultipleTypes_shouldHaveRightSize() {
        List<Character> list = GarbageStorageGenerator.generateListForTypes(Garbage.values(), 100);
        assertEquals(100, list.size());
    }

    @Test
    @Order(4)
    public void testShuffle_shouldNotBeSameOrder() {
        List<Character> list = GarbageStorageGenerator.generateListForTypes(Garbage.values(), 100);

        List<Character> copy = new ArrayList<>();
        copy.addAll(list);
        List<Character> shuffled = GarbageStorageGenerator.shuffle(copy);

        boolean sameOrder = true;
        for (int i = 0; i < list.size(); i++) {
            sameOrder = list.get(i)== shuffled.get(i);
            if (!sameOrder) {
                break;
            }
        }

        assertFalse(sameOrder);
    }

    @Test
    @Order(5)
    public void testGenerateRandomStorage_shouldHaveSameSize() {
        char[][][] array = GarbageStorageGenerator.generateRandomStorage(
                new Dimension3D(5, 15, 10), Garbage.values());

        assertEquals(5, array.length);

        for (int i = 0; i < 5; i++) {
            assertEquals(15, array[i].length);
            for (int j = 0; j < 10; j++) {
                assertEquals(10, array[i][j].length);
            }
        }
    }
}
