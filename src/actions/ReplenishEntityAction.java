package actions;

import elements.SimulationMap;
import entities.Entity;
import entities.Grass;
import entities.Herbivore;
import entities.Predator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReplenishEntityAction extends SpawnEntitiesAction {
    private static final Set<Class<? extends Entity>> REPLENISHABLE_ENTITY_TYPES = Set.of(
            Grass.class,
            Herbivore.class,
            Predator.class
    );

    @Override
    public void execute(SimulationMap simulationMap) {
        Map<Class<? extends Entity>, Integer> entitiesToReplenish = new HashMap<>();

        for (Map.Entry<Class<? extends Entity>, Integer> entry : entitiesToSpawn.entrySet()) {
            Class<? extends Entity> entityToSpawn = entry.getKey();
            int countToSpawn = entry.getValue();

            if (!REPLENISHABLE_ENTITY_TYPES.contains(entityToSpawn)) {
                continue;
            }

            long currentCount = simulationMap.getEntities().values().stream().filter(entityToSpawn::isInstance).count();
            if (currentCount < countToSpawn) {
                entitiesToReplenish.put(entityToSpawn, countToSpawn);
            }
        }

        entityFactory.create(simulationMap, entitiesToReplenish);
    }
}