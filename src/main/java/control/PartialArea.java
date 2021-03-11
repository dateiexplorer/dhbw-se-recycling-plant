package control;

import dimensions.Dimension2D;

import java.util.ArrayList;
import java.util.List;

/**
 * A helper class to simulate an partial area, which holds a list of all positions in this area.
 */
public class PartialArea {

    private int id;
    private List<Dimension2D> positions;

    public PartialArea(int id, Dimension2D startPosition, Dimension2D dimension) {
        this.id = id;

        // Calculating all available positions for this area.
        positions = new ArrayList<>();
        for (int l = startPosition.getLength(); l < startPosition.getLength() + dimension.getLength(); l++) {
            for (int w = startPosition.getWidth(); w < startPosition.getWidth() + dimension.getLength(); w++) {
                positions.add(new Dimension2D(l, w));
            }
        }
    }

    // Getter and setter
    public int getID() {
        return id;
    }

    public List<Dimension2D> getPositions() {
        return positions;
    }
}
