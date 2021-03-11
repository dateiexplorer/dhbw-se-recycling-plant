package dimensions;

/**
 * A three dimensional vector type.
 */
public class Dimension3D extends Dimension2D {

    private int height;

    public Dimension3D(int length, int width, int height) {
        super(length, width);
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && height == ((Dimension3D) obj).height;
    }

    @Override
    public Object clone() {
        return new Dimension3D(length, width, height);
    }

    @Override
    public String toString() {
        return "Dimension3D { length: " + length + ", width: " + width + ", height: " + height + " }";
    }


    @Override
    public int getSize() {
        return length * width * height;
    }

    // Getter and setter
    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
