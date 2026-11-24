package actions;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Entity;
import entities.Predator;

import java.util.Random;

public class AdditionalPredatorSpawnAction extends PredatorSpawnAction {

    public AdditionalPredatorSpawnAction(int initCount) {
        super(initCount);
    }

    @Override
    public void execute(SimulationMap simulationMap) {
        int predatorsCount = (int) simulationMap.getWorldMap().values().stream().filter(entity -> entity instanceof Predator).count();
        if (predatorsCount >= 3) return;

        int additionalAmount = initCount;
        Random random = new Random();

        int count = 0;
        while (count < additionalAmount) {
            Coordinates coordinates = getRandomCell(simulationMap, random);

            if (simulationMap.isCellEmpty(coordinates)) {
                Entity entity = createEntity(coordinates);
                simulationMap.addEntity(coordinates, entity);
                count++;
            }
        }
    }
}
