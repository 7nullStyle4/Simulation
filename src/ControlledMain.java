import elements.Simulation;
import elements.SimulationController;
import elements.SimulationMap;

public class ControlledMain {
    public static void main(String[] args) {
        SimulationMap simulationMap = new SimulationMap(15, 15);
        Simulation simulation = new Simulation(simulationMap);
        SimulationController controller = new SimulationController(simulation);

        controller.start();
    }
}
