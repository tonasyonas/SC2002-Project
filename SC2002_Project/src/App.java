import FOMS.Pages.StartUpPage;


/**
 * The {@code App} class serves as the main entry point for the FOMS.
 * It initializes the system and directs the user to the startup page where further interactions are handled.
 * This class primarily sets up the initial welcoming interface and triggers the main application logic through the
 * {@code StartUpPage} class.
 *
 * <p>The application starts by displaying an ASCII art logo and a welcome message. It then proceeds to initiate
 * the {@code StartUpPage}, which orchestrates the flow of the application based on the user's navigation choices.</p>
 *
 * <p>This class is designed to be straightforward and minimal, focusing solely on setting up the environment and
 * delegating the detailed user interaction to other parts of the system.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class App {
    /**
     * The main method that starts the application. It prints the application's logo and welcome message,
     * then creates an instance of {@code StartUpPage} to begin the application process.
     *
     * @param args The command-line arguments passed to the application (not used in this application).
     */
    public static void main(String[] args) {

        System.out.println("  ______ ____  __  __  _____ ");
        System.out.println(" |  ____/ __ \\|  \\/  |/ ____|");
        System.out.println(" | |__ | |  | | \\  / | (___  ");
        System.out.println(" |  __|| |  | | |\\/| |\\___ \\ ");
        System.out.println(" | |   | |__| | |  | |____) |");
        System.out.println(" |_|    \\____/|_|  |_|_____/ ");        
        System.out.println("\n================== Welcome to FOMS! ==================");

         StartUpPage startUpPage = new StartUpPage();
        startUpPage.startPage();
        
    }
}