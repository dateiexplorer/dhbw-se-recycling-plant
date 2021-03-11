package dimensions;

/**
 * A two dimensional vector type.
 */
public class Dimension2D implements Cloneable {

    protected int length;
    protected int width;

    public Dimension2D(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Dimension2D other = (Dimension2D) obj;
        return length == other.length && width == other.width;
    }

    @Override
    public Object clone() {
        return new Dimension2D(length, width);
    }

    @Override
    public String toString() {
        return "Dimension2D { length: " + length + ", width: " + width + " }";
    }

    // Getter and setter
    public int getSize() {
        return length * width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
