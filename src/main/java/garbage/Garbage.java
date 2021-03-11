package garbage;

/**
 * An enumeration of all available garbage types an the percentage distribution in an garbage storage.
 */
public enum Garbage {
    METAL('M', 0.08),
    GLASS('G', 0.24),
    PLASTIC('P', 0.48),
    WASTE('W', 0.2);

    private char id;
    private double percentage;

    Garbage(char id, double percentage) {
        this.id = id;
        this.percentage = percentage;
    }

    public char getID() {
        return id;
    }

    public double getPercentage() {
        return percentage;
    }
}
