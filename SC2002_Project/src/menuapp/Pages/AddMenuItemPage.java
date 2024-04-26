package menuapp.Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

/**
 * The {@code AddMenuItemPage} class represents a page in the application where users can add new menu items.
 * It implements {@code IPage} interface and handles the user interaction required to gather menu item details
 * and save them to a file. This class also checks for the uniqueness of the menu item name within the existing items.
 * 
 * <p>This class handles user inputs for the new menu item's name, price, branch (retrieved from the {@code BranchManager}),
 * category, availability, and a brief description. It ensures that the name is unique, the description does not exceed 20 characters,
 * and all input is processed and formatted before being saved to a text file.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class AddMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    /**
     * Constructs an {@code AddMenuItemPage} object with a specific {@code BranchManager}.
     *
     * @param branchManager the {@code BranchManager} to associate with this page for retrieving the current branch.
     */
    public AddMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    /**
     * Starts the page flow by displaying the page, adding a new menu item, and transitioning to the options page.
     */
    @Override
    public void startPage() {
        display();
        addMenuItem();
        goToOptionsPage();
    }

    /**
     * Displays the initial prompt to the user indicating the purpose of the page.
     */
    @Override
    public void display() {
        System.out.println("Add New Menu Item:");
    }

    /**
     * Retrieves the user input in a normalized format.
     * 
     * @return the trimmed and lowercase user input.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput.trim().toLowerCase();
    }

    
    /**
     * Handles the input gathering for adding a new menu item.
     * This method processes the user input, ensuring the menu item name is unique,
     * and then prompts the user for additional details of the menu item.
     * Successfully gathered data is formatted and written to a file.
     */
    public void addMenuItem() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print("Enter new menu item name: ");
                String name = scanner.nextLine().trim();
    
                // Check if the item name already exists in the menu
                if (isItemNameUnique(name)) {
                    System.out.print("Enter price: ");
                    String price = scanner.nextLine().trim();
    
                    String branch = branchManager.getBranch(); // Get the branch from the branchManager
    
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine().trim();
    
                    System.out.print("Is the item available? (true/false): ");
                    String availability = scanner.nextLine().trim();
    
                    String description;
                    while (true) {
                        System.out.print("Enter description (maximum 20 characters): ");
                        description = scanner.nextLine().trim();
                        if (description.length() <= 20) {
                            break; // Exit the loop if description is within 20 characters
                        } else {
                            System.out.println("Description exceeds 20 characters. Please enter a shorter description.");
                        }
                    }
    
                    // Format the details for writing to the file
                    String newItem = name + ";" + price + ";" + branch + ";" + category + ";" + availability + ";" + description + "\n";
    
                    // Append the new menu item to the file
                    FileWriter writer = new FileWriter(FILE_NAME, true);
                    writer.write(newItem);
                    writer.close();
                    System.out.println("New menu item added successfully!");
                    break; // Exit the loop if the item is added successfully
                } else {
                    System.out.println("An item with the same name already exists. Please enter a unique name.");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while adding the menu item: " + e.getMessage());
        } 
    }

    /**
     * Checks if a menu item name is unique within the existing menu items.
     * 
     * @param name the menu item name to check for uniqueness.
     * @return true if the name is unique, false otherwise.
     * @throws IOException if there is an error reading the file.
     */
    private boolean isItemNameUnique(String name) throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return true; // If the file doesn't exist, assume the name is unique
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(";");
                if (details.length > 0 && details[0].equalsIgnoreCase(name)) {
                    return false; // If the item name already exists, return false
                }
            }
        }

        return true; // If the item name doesn't exist, return true
    }

    /**
     * Transitions to the options management page.
     * This method creates an instance of {@code ManagerOptionsPage} and starts it.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
