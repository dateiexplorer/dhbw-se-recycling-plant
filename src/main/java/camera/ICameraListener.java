package camera;

import dimensions.Dimension2D;
import garbage.Garbage;

/**
 * An interface, should implemented by all types, that listens to the camera events.
 */
public interface ICameraListener {

    /**
     * Invoked if a garbage type was classified by the camera.
     * @param type - The type of the garbage.
     * @param position - The position of the garbage in the area.
     */
    void onClassify(Garbage type, Dimension2D position);
}
