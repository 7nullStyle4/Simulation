package render;

public class ConsoleMenuRenderer implements MenuRenderer {
    private static final String START_OPTION = "1";
    private static final String PAUSE_OPTION = "2";
    private static final String EXIT_OPTION = "3";

    @Override
    public void printMainMenu() {
        System.out.println("Welcome to the game!");
        System.out.printf("%s - start\n", START_OPTION);
        System.out.printf("%s - pause\n", PAUSE_OPTION);
        System.out.printf("%s - exit\n", EXIT_OPTION);

        System.out.print("Your option: ");
    }

    @Override
    public void printStartMenu() {
        System.out.println("Game is running");
    }

    @Override
    public void printPauseMenu() {
        System.out.println("Game is paused");
    }

    @Override
    public void printExitMenu() {
        System.out.println("Game over!");
    }

    @Override
    public void printInvalidInput() {
        System.out.println("Enter a correct option");
    }

    @Override
    public void printInvalidStartMenuInput() {
        System.out.printf("You can enter only %s or %s\n", START_OPTION, EXIT_OPTION);
    }
}
