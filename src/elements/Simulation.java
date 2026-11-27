package elements;

import actions.*;
import render.ConsoleMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final SimulationMap simulationMap;
    private final ConsoleMapRenderer consoleMapRenderer;
    private final MoveCounter moveCounter;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();
    private volatile boolean isRunning;

    public Simulation(SimulationMap simulationMap) {
        this.simulationMap = simulationMap;
        this.consoleMapRenderer = new ConsoleMapRenderer();
        this.moveCounter = new MoveCounter();
        createActions();
        executeActions(initActions);
    }

    public void startSimulation() {
        sleepBetweenCommands();
        if (isRunning) {
            return;
        }

        isRunning = true;
        new Thread(() -> {
            sleep();
            while (isRunning) {
                nextTurn();
                sleep();
                if (isNotRunning()) {
                    return;
                }
            }
        }).start();
    }

    public void pauseSimulation() {
        sleepBetweenCommands();
        if (!isRunning) {
            return;
        }

        isRunning = false;
    }

    public void stopSimulation() {
        sleepBetweenCommands();
        isRunning = false;
    }

    public boolean isNotRunning() {
        return !isRunning;
    }

    private void nextTurn() {
        consoleMapRenderer.render(simulationMap);
        executeActions(turnActions);
        moveCounter.printCount();
    }

    private void executeActions(List<Action> actions) {
        for(Action action : actions) {
            action.execute(simulationMap);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleepBetweenCommands() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void createActions() {
        initActions.add(new SpawnEntitiesAction());

        turnActions.add(new MoveCreaturesAction());
        turnActions.add(new RemoveDeadCreatureAction());
        turnActions.add(new ReplenishEntityAction());
    }
}