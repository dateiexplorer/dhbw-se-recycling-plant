package control;

import dimensions.Dimension2D;
import garbage.Garbage;
import camera.ICameraListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A data storage, which holds all classified data.
 * Listen to camera events (observer).
 */
public class Repository implements ICameraListener {

    private Map<Garbage, List<Dimension2D>> repository;

    public Repository() {
        repository = new HashMap<>();
    }

    /**
     * Clears all data from the repository.
     */
    public void clear() {
        repository.clear();
    }

    @Override
    public void onClassify(Garbage type, Dimension2D position) {
        if (repository.get(type) == null) {
            repository.put(type, new ArrayList<>());
        }

        repository.get(type).add(position);
    }

    // Getter and setter
    public Map<Garbage, List<Dimension2D>> getData() {
        return repository;
    }
}
