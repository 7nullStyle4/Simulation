package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.*;
import utils.EntityFactory;

import java.util.Map;

public class ReplenishEntityAction extends SpawnEntitiesAction {
    @Override
    public void execute(SimulationMap simulationMap) {
        for (Map.Entry<Class<? extends Entity>, Integer> entry : entitiesToSpawn.entrySet()) {
            Class<? extends Entity> entityType = entry.getKey();
            int initCount = entry.getValue();

            if (simulationMap.getEntities().values().stream().filter(entityType::isInstance).count() >= initCount) {
                continue;
            }

            int count = 0;
            while (count < initCount) {
                Coordinates coordinates = getRandomCell(simulationMap);

                if (simulationMap.isCellEmpty(coordinates)) {
                    Entity entity = EntityFactory.create(entityType, coordinates);
                    simulationMap.addEntity(coordinates, entity);
                    count++;
                }
            }
        }
    }
}
