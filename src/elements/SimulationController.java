package elements;

import render.ConsoleMenuRenderer;

import java.util.Scanner;

public class SimulationController extends Thread {
    private final Simulation simulation;
    private final ConsoleMenuRenderer consoleMenuRenderer = new ConsoleMenuRenderer();
    private static final String START_OPTION = "1";
    private static final String PAUSE_OPTION = "2";
    private static final String EXIT_OPTION = "3";


    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        consoleMenuRenderer.printMainMenu();

        while (true) {
            if (!controlStartMenu()) {
                break;
            }
        }

        if (simulation.isNotRunning()) {
            return;
        }

        while (true) {
            if (!controlPauseMenu()) {
                return;
            }
        }
    }

    private boolean controlStartMenu() {
        Scanner scanner = new Scanner(System.in);

        String option = scanner.nextLine().trim();
        if (option.equals(START_OPTION)) {
            consoleMenuRenderer.printStartMenu();
            simulation.startSimulation();
        } else if (option.equals(EXIT_OPTION)) {
            consoleMenuRenderer.printExitMenu();
            simulation.stopSimulation();
        } else {
            consoleMenuRenderer.printInvalidStartMenuInput();
            return true;
        }

        return false;
    }

    private boolean controlPauseMenu() {
        Scanner scanner = new Scanner(System.in);

        String option = scanner.nextLine().trim();
        if (option.equals(START_OPTION) && simulation.isNotRunning()) {
            consoleMenuRenderer.printStartMenu();
            simulation.startSimulation();
        } else if (option.equals(PAUSE_OPTION)) {
            consoleMenuRenderer.printPauseMenu();
            simulation.pauseSimulation();
        } else if (option.equals(EXIT_OPTION)) {
            consoleMenuRenderer.printExitMenu();
            simulation.stopSimulation();
            return false;
        } else {
            consoleMenuRenderer.printInvalidInput();
        }

        return true;
    }
}