package menuapp.Pages;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

public class EditMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    public EditMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    @Override
    public void startPage() {
        display();
        editMenuItem();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Edit Menu Item:");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase();
    }

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
    
    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
