import dimensions.Dimension2D;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        Simulation simulation = new Simulation(Configuration.instance.storageSize,
                new ArrayList<>() {{
                    add(new Dimension2D(0, 0));
                    add(new Dimension2D(0, Configuration.instance.storageSize.getHeight()));
                    add(new Dimension2D(Configuration.instance.storageSize.getWidth(), 0));
                    add(new Dimension2D(Configuration.instance.storageSize.getWidth(),
                            Configuration.instance.storageSize.getHeight()));
                }});

        // Do all iterations. This may take a long time!
        //simulation.iterateAll();

        // Alternatively do one iteration
        simulation.iterate();
    }
}
