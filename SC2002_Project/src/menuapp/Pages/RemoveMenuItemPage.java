package menuapp.Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

/**
 * The {@code RemoveMenuItemPage} class implements the {@code IPage} interface and is responsible for removing a menu item
 * from the system's menu list. This class is designed for branch managers to manage their specific branch menus,
 * allowing them to easily remove items that are no longer available or relevant.
 *
 * <p>This class handles user interactions for identifying the menu item to be removed and processes file operations
 * to update the menu list accordingly. It also provides feedback to the user on the success or failure of the operation.</p>
 *
 * <p>Utilizing simple text-based user interfaces, the class supports SOLID principles in the following ways:</p>
 * <ul>
 *   <li><strong>Single Responsibility Principle:</strong> This class has a single responsibility of removing a menu item.</li>
 *   <li><strong>Open/Closed Principle:</strong> It is open for extension and closed for modification. New functionality,
 *       such as removing multiple items at once, could be added without altering the existing code.</li>
 *   <li><strong>Dependency Inversion Principle:</strong> This class depends on abstractions by using the {@code IPage} interface,
 *       not on concrete details of menu management directly, which are encapsulated within the class.</li>
 * </ul>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class RemoveMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    /**
     * Constructs a {@code RemoveMenuItemPage} with the specified {@code BranchManager}.
     * 
     * @param branchManager The {@code BranchManager} to associate with this page for menu item management specific to their branch.
     */
    public RemoveMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    /**
     * Starts the page by displaying a prompt, removing a specified menu item, and navigating to the next options page.
     */
    @Override
    public void startPage() {
        display();
        removeMenuItem();
        goToOptionsPage();
    }

    /**
     * Displays the prompt for removing a menu item to the user.
     */
    @Override
    public void display() {
        System.out.println("Remove Menu Item:");
    }

    /**
     * Handles the input from the user, trimming white spaces and converting to lowercase for standardized processing.
     * 
     * @return The processed user input.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * Facilitates the removal of a menu item. It prompts the user for the menu item name, verifies if it belongs to the branch,
     * and then performs file operations to update the menu list accordingly.
     */
    public void removeMenuItem() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter menu item name to remove: ");
            String nameToRemove = scanner.nextLine().trim();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("SC2002_Project/src/FOMS/menu_manager/temp_menu_list.txt");

            Scanner fileScanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);

            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(";");
                if (details.length > 0 && details[0].equalsIgnoreCase(nameToRemove) && details[2].equalsIgnoreCase(branchManager.getBranch())) {
                    found = true;
                    continue;  
                }
                writer.write(line + "\n");
            }

            writer.close();
            fileScanner.close();

            if (!found) {
                System.out.println("Menu item not found or does not belong to your branch.");
                tempFile.delete();  
            } else {
                inputFile.delete();  
                tempFile.renameTo(inputFile);  
                System.out.println("Menu item removed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while removing the menu item: " + e.getMessage());
        }
    }

    /**
     * Transitions to the options management page once the removal operation is completed.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
