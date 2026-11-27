package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.*;
import utils.EntityFactory;

import java.util.*;

public class SpawnEntitiesAction implements Action {
    protected final Map<Class<? extends Entity>, Integer> entitiesToSpawn = new HashMap<>(Map.of(
            Herbivore.class, 5,
            Predator.class, 4,
            Grass.class, 5,
            Tree.class, 3,
            Rock.class, 3
    ));

    @Override
    public void execute(SimulationMap simulationMap) {
        for (Map.Entry<Class<? extends Entity>, Integer> entry : entitiesToSpawn.entrySet()) {
            Class<? extends Entity> entityType = entry.getKey();
            int initCount = entry.getValue();

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

    protected Coordinates getRandomCell(SimulationMap simulationMap) {
        Random random = new Random();

        int row = random.nextInt(simulationMap.getHeight()) + 1;
        int column = random.nextInt(simulationMap.getWidth()) + 1;

        Coordinates coordinates = new Coordinates(row, column);
        simulationMap.validateCoordinates(coordinates);

        return coordinates;
    }
}

