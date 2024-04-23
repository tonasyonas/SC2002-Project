package menuapp.Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import FOMS.Pages.IPage;
import FOMS.FOMS_entity.BranchManager;

public class RemoveMenuItemPage implements IPage {
    private static final String FILE_NAME = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
    private BranchManager branchManager;

    public RemoveMenuItemPage(BranchManager branchManager) {
        this.branchManager = branchManager;
    }

    @Override
    public void startPage() {
        display();
        removeMenuItem();
        goToOptionsPage();
    }

    @Override
    public void display() {
        System.out.println("Remove Menu Item:");
    }

    @Override
    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim().toLowerCase();
    }

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
                    continue; // Skip writing this line to the temporary file
                }
                writer.write(line + "\n");
            }

            writer.close();
            fileScanner.close();

            if (!found) {
                System.out.println("Menu item not found or does not belong to your branch.");
                tempFile.delete(); // Delete the temporary file if the item is not found or doesn't belong to the branch
            } else {
                inputFile.delete(); // Delete the original file
                tempFile.renameTo(inputFile); // Rename the temporary file to the original file
                System.out.println("Menu item removed successfully!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while removing the menu item: " + e.getMessage());
        }
    }

    private void goToOptionsPage() {
        IPage optionsPage = new ManagerOptionsPage(branchManager);
        optionsPage.startPage();
    }
}
