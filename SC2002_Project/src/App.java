import FOMS.Pages.StartUpPage;
import FOMS.account_manager.LoginController;
import FOMS.branch_manager.Restaurant;

public class App {
    public static void main(String[] args) {

        System.out.println("  ______ ____  __  __  _____ ");
        System.out.println(" |  ____/ __ \\|  \\/  |/ ____|");
        System.out.println(" | |__ | |  | | \\  / | (___  ");
        System.out.println(" |  __|| |  | | |\\/| |\\___ \\ ");
        System.out.println(" | |   | |__| | |  | |____) |");
        System.out.println(" |_|    \\____/|_|  |_|_____/ ");        
        System.out.println("\n================== Welcome to FOMS! ==================");

        Restaurant myRestaurant = new Restaurant();
        StartUpPage startUpPage = new StartUpPage();
        startUpPage.startPage();
        
    }
}