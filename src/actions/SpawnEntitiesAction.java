package actions;

import elements.SimulationMap;
import entities.*;

import java.util.*;

public class SpawnEntitiesAction implements Action {
    protected final EntityFactory entityFactory = new EntityFactory();
    protected final Map<Class<? extends Entity>, Integer> entitiesToSpawn = new HashMap<>(Map.of(
            Herbivore.class, 5,
            Predator.class, 4,
            Grass.class, 5,
            Tree.class, 3,
            Rock.class, 3
    ));

    @Override
    public void execute(SimulationMap simulationMap) {
        entityFactory.create(simulationMap, entitiesToSpawn);
    }
}

