package menuapp.Pages;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

/**
 * The {@code EditMenuItemPage} class implements the {@code IPage} interface and provides functionalities to edit existing menu items.
 * This class allows branch managers to modify details such as price, description, and availability of menu items that belong
 * to their respective branches. It supports interactive modifications through a simple text-based interface.
 *
 * <p>Upon being initiated, it prompts the user to enter the name of the menu item to edit. Once the item is found, it allows
 * the user to select which attribute of the menu item they wish to edit. If the specified item belongs to the manager's branch,
 * the changes are committed to the menu list file. The class handles file operations to ensure data integrity during the edit process.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class EditMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    /**
     * Constructs an {@code EditMenuItemPage} with a specified {@code BranchManager}.
     * 
     * @param branchManager The {@code BranchManager} instance associated with this page.
     */
    public EditMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    /**
     * Starts the edit menu item page by displaying a greeting, initiating the edit process, and transitioning to other options upon completion.
     */
    @Override
    public void startPage() {
        display();
        editMenuItem();
        goToOptionsPage();
    }

    /**
     * Displays the initial greeting and prompt to the user.
     */
    @Override
    public void display() {
        System.out.println("Edit Menu Item:");
    }

    /**
     * Retrieves user input from the console, ensuring it is properly formatted.
     * 
     * @return A trimmed and lowercase version of the user input.
     */
    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase();
    }

     /**
     * Manages the editing of a menu item. This method handles file operations to locate the specified item,
     * allows the user to select and modify specific attributes of the item, and updates the file accordingly.
     * If the item is not found or does not belong to the user's branch, it notifies the user and makes no changes.
     */
    public void editMenuItem() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter menu item name to edit: ");
            String nameToEdit = scanner.nextLine().trim();
    
            File inputFile = new File(FILE_NAME);
            File tempFile = new File("SC2002_Project/src/FOMS/menu_manager/temp_menu_list.txt");
    
            Scanner fileScanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(tempFile);
    
            boolean found = false;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(";");
                if (details.length > 0 && details[0].equalsIgnoreCase(nameToEdit) && details[2].equalsIgnoreCase(branchManager.getBranch())) {
                    found = true;
                    System.out.println("Menu item found. Choose what to edit:");
                    System.out.println("1. Price");
                    System.out.println("2. Description");
                    System.out.println("3. Availability");
                    System.out.print("Enter your choice: ");
                    String editChoice = scanner.nextLine().trim();
    
                    switch (editChoice) {
                        case "1":
                            System.out.print("Enter new price: ");
                            String newPrice = getInput();
                            details[1] = newPrice;
                            break;
                            case "2":
                            if (details.length < 6) { // If description doesn't exist, prompt to add it
                                System.out.print("Description does not exist. Enter new description: ");
                                String newDescription = getInput();
                                line += ";" + (newDescription.length() <= 20 ? newDescription : newDescription.substring(0, 20));
                                details = line.split(";"); // Update details array with appended description
                            } else {
                                System.out.print("Enter new description: ");
                                String newDescription = getInput();
                                details[5] = newDescription.length() <= 20 ? newDescription : newDescription.substring(0, 20);
                            }
                            break;
                        
                        case "3":
                            System.out.print("Enter new availability (true/false): ");
                            String newAvailability = getInput();
                            details[4] = newAvailability;
                            break;
                        default:
                            System.out.println("Invalid choice. No changes will be made.");
                            break;
                    }
                }
                writer.write(String.join(";", details) + "\n");
            }
    
            writer.close();
            fileScanner.close();
    
            if (!found) {
                System.out.println("Menu item not found or does not belong to your branch.");
                tempFile.delete(); // Delete the temporary file if the item is not found or doesn't belong to the branch
            } else {
                inputFile.delete(); // Delete the original file
                tempFile.renameTo(inputFile); // Rename the temporary file to the original file
                System.out.println("Menu item edited successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while editing the menu item: " + e.getMessage());
        } 
    }
    
    /**
     * Transitions to the manager options page after editing a menu item.
     */
    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
