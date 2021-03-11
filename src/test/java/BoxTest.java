import dimensions.Dimension2D;
import garbage.Box;
import garbage.BoxEmptier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoxTest {

    private Box box;

    @BeforeEach
    public void init() {
        box = new Box(5, new Dimension2D(0, 0));
    }

    @Test
    @Order(1)
    public void testBoxFilled_shouldContainGarbage() {
        box.fill('W');

        assertEquals(box.getStorage()[0], 'W');
        assertEquals(box.getFilled(), 1);

        for (int i = 1; i < box.getCapacity(); i++) {
            assertEquals(box.getStorage()[i], '#');
        }
    }

    @Test
    @Order(2)
    public void testBoxFilled_shouldIncreaseFilledValue() {
        box.fill('W');

        assertEquals(box.getFilled(), 1);
    }

    @Test
    @Order(3)
    public void testBoxFilled_restShouldContainsDefault() {
        box.fill('W');

        for (int i = 1; i < box.getCapacity(); i++) {
            assertEquals(box.getStorage()[i], '#');
        }
    }

    @Test
    @Order(4)
    public void testBoxEmptied_shouldContainsDefault() {
        box.fill('W');
        box.empty();

        for (int i = 0; i < box.getCapacity(); i++) {
            assertEquals(box.getStorage()[i], '#');
        }
    }

    @Test
    @Order(5)
    public void testBoxIsFilled_shouldBeEmptiedByEmptier() {
        box.addListener(new BoxEmptier());

        // Empty if box is full.
        for (int i = 0; i < box.getCapacity(); i++) {
            box.fill('W');
        }

        assertEquals(box.getFilled(), 0);
    }
}
