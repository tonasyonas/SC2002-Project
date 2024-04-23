package menuapp.Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

public class AddMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    public AddMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    @Override
    public void startPage() {
        display();
        addMenuItem();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Add New Menu Item:");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput.trim().toLowerCase();
    }

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

    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
