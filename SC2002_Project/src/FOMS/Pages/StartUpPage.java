package FOMS.Pages;

import java.util.Scanner;

import menuapp.Pages.CustOrderPage;
import menuapp.Pages.StaffLoginPage;

/**
 * The {@code StartUpPage} class implements the {@code IPage} interface and serves as the initial interaction point
 * for users of the FOMS. This class is responsible for determining whether the user
 * is a customer or a staff member and directing them to the appropriate part of the application based on their response.
 *
 * <p>Upon instantiation, the class prompts the user to identify as either a customer or a staff member. Depending
 * on the user's input, it either navigates to the {@code CustOrderPage} for customers or the {@code StaffLoginPage}
 * for staff members. This class handles user input errors by prompting the user again until a valid input is received.</p>
 *
 * <p>This approach ensures that users are correctly routed to the functionalities relevant to their role, thus
 * maintaining a clear and efficient user experience.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */


public class StartUpPage implements IPage {
    private Scanner scanner;

    /**
     * Constructs a new {@code StartUpPage} instance with a dedicated {@code Scanner} for capturing user input.
     * It initializes the scanner and prompts the user to identify their user type upon creation.
     */
    public StartUpPage() {
        this.scanner = new Scanner(System.in); 
        System.out.println("Are you a customer or a staff member?");
    }

     /**
     * Initiates the page by displaying the prompt for user type and processing the input to navigate to the
     * appropriate page. This method implements a simple decision-making process to determine the next step
     * based on user input, with error handling for invalid entries.
     */
    @Override
    public void startPage() {
        display();
        String userType = getInput();
        switch (userType) {
            case "customer":
                CustOrderPage custOrderPage = new CustOrderPage();
                custOrderPage.startPage();
                break;
            case "staff":
                StaffLoginPage staffLoginPage = new StaffLoginPage();
                staffLoginPage.startPage();
                break;
            default:
                System.out.println("Invalid input. Please enter 'customer' or 'staff'.");
                startPage();  
                break;
        }
    }

    /**
     * Displays a prompt to the user asking them to specify whether they are a customer or a staff member.
     */
    @Override
    public void display() {
        System.out.print("Enter 'customer' or 'staff': ");
    }

    /**
     * Retrieves and processes user input, ensuring it is properly formatted to facilitate accurate processing.
     *
     * @return A string representing the user's role choice, trimmed and converted to lower case.
     */
    @Override
    public String getInput() {
        String userType = scanner.nextLine().trim().toLowerCase();
        return userType;
    }

}
