package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntityFactory {

    public void create(SimulationMap simulationMap, Map<Class<? extends Entity>, Integer> entitiesToSpawn) {
        for (Map.Entry<Class<? extends Entity>, Integer> entry : entitiesToSpawn.entrySet()) {
            List<Coordinates> emptyCells = simulationMap.getEmptyCells();
            Collections.shuffle(emptyCells);

            int count = 0;
            while (count < entry.getValue()) {
                Coordinates coordinates = emptyCells.get(count);
                Entity entity = EntityObjectSupplier.createEntityByType(entry.getKey(), coordinates);
                simulationMap.addEntity(coordinates, entity);
                count++;
            }
        }
    }
}