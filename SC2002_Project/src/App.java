import FOMS.Pages.StartUpPage;
import FOMS.account_manager.LoginController;
import FOMS.branch_manager.Restaurant;

public class App {
    public static void main(String[] args) {

        Restaurant myRestaurant = new Restaurant();
        StartUpPage startUpPage = new StartUpPage();
        startUpPage.startPage();
        
    }
}