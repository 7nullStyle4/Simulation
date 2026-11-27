package actions;

import elements.Coordinates;
import entities.*;
import settings.Settings;

public class EntityObjectSupplier {

    public static Entity createEntityByType(Class<? extends Entity> entityType, Coordinates coordinates) {
        return switch (entityType.getSimpleName()) {
            case "Herbivore" -> new Herbivore(Settings.HERBIVORE_HEALTH, Settings.HERBIVORE_SPEED, coordinates);
            case "Predator" -> new Predator(Settings.PREDATOR_HEALTH, Settings.PREDATOR_SPEED, Settings.PREDATOR_ATTACK, coordinates);
            case "Grass" -> new Grass(coordinates);
            case "Rock" -> new Rock(coordinates);
            case "Tree" -> new Tree(coordinates);
            default -> throw new IllegalArgumentException("Unsupported entity type: " + entityType);
        };
    }
}
