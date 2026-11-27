package render;

import elements.Coordinates;
import elements.SimulationMap;
import entities.Entity;

public class ConsoleMapRenderer implements MapRenderer {
    private static final String HERBIVORE = "\uD83D\uDC01";
    private static final String PREDATOR = "\uD83D\uDC08";
    private static final String GRASS = "\uD83E\uDDC0";
    private static final String TREE = "\ud83c\udf33";
    private static final String ROCK = "\ud83e\udea8";
    private static final String EMPTY_CELL = "\u2B1C";

    public void render(SimulationMap simulationMap) {
        int height = simulationMap.getHeight();
        int width = simulationMap.getWidth();

        System.out.println();
        for (int row = 1; row <= height; row++) {
            for (int column = 1; column <= width; column++) {
                Coordinates coordinates = new Coordinates(row, column);

                if(simulationMap.isCellEmpty(coordinates)) {
                    System.out.print(EMPTY_CELL + " ");
                }  else {
                    Entity entity = simulationMap.getEntity(coordinates);
                    String sprite = getIcon(entity);
                    System.out.print(sprite + " ");
                }
            }
            System.out.println();
        }
    }

    private String getIcon(Entity entity) {
        return switch (entity.getClass().getSimpleName()) {
            case "Herbivore" -> HERBIVORE;
            case "Predator"  -> PREDATOR;
            case "Grass"     -> GRASS;
            case "Tree"      -> TREE;
            case "Rock"      -> ROCK;
            default -> throw new IllegalArgumentException("Undefined entity: " + entity.getClass().getSimpleName());
        };
    }
}
