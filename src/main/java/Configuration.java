import dimensions.Dimension2D;
import dimensions.Dimension3D;

public enum Configuration {
    instance;

    // The dimension of a vehicle storage.
    // Set length to 5000 might causes ro much heap space.
    public Dimension3D storageSize = new Dimension3D(100, 500, 100);

    // The area, which will handle by one robot during one iteration.
    public Dimension2D partialAreaSize = new Dimension2D(25, 25);
}
