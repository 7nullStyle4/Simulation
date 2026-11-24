package elements;

import entities.*;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> worldMap = new HashMap<>();

    public SimulationMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Map<Coordinates, Entity> getWorldMap() {
        return new HashMap<>(worldMap);
    }

    public Entity getEntity(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return worldMap.get(coordinates);
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        validateCoordinates(coordinates);
        entity.setCoordinates(coordinates);
        worldMap.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        validateCoordinates(coordinates);
        worldMap.remove(coordinates);
    }

    public void validateCoordinates(Coordinates coordinates) {
        if(coordinates.x() < 1 || coordinates.x() > width || coordinates.y() < 1 || coordinates.y() > height) {
            throw new RuntimeException("Invalid coordinates: %d %d".formatted(coordinates.x(), coordinates.y()));
        };
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        validateCoordinates(coordinates);
        return !worldMap.containsKey(coordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
