package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import FOMS.Pages.IPage;
import FOMS.menu_manager.*;
import FOMS.branch_manager.*;

public class CustOrderPage implements IPage{

    @Override
    public void startPage() {
        display();
        int choice;
        Scanner sc = new Scanner(System.in);

        // Initializing dependencies for ViewMenu
        MenuDisplay menuDisplay = new ConsoleMenuDisplay();
        String[] branches = ReadBranchList.getBranchIDs("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");
        BranchSelector branchSelector = new ConsoleBranchSelector(sc, branches);
        String filename = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
        Map<String, MenuItem> menuMap = ReadMenu.readMenuItems(filename);
        MenuOrganizer menuOrganizer = new MenuOrganizer(menuMap);
        ViewMenu viewMenu = new ViewMenu(menuDisplay, branchSelector, menuOrganizer);

        System.out.println("Welcome to the Fast Food Ordering System!");
        do {
            showOptions();
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    viewMenu.displayMenuForBranch();
                    addItemsToCart();
                    break;
                case 2:
                    modifyCart();
                    break;
                case 3:
                    checkoutCart();
                    break;
                case 4:
                    checkOrderStatus();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private void showOptions() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1 - Browse Menu");
        System.out.println("2 - View/Edit Cart");
        System.out.println("3 - Checkout");
        System.out.println("4 - Check Order Status");
        System.out.println("5 - Exit");
        System.out.print("Your choice: ");
    }

    private void addItemsToCart() {
        System.out.println("Enter the item name to add to cart or 'exit' to return:");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            MenuItem item = viewMenu.getMenuItem(input);
            if (item != null) {
                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Cart.addItem(item, quantity);
                System.out.println("Item added to cart.");
            } else {
                System.out.println("Item not found. Please try again.");
            }
            input = Scanner.nextLine();
        }
    }

    private void modifyCart() {
        Cart.displayItems();
        System.out.println("Enter the item name to remove or update, or 'clear' to empty the cart:");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("clear")) {
            Cart.clear();
            System.out.println("Cart cleared.");
        } else {
            System.out.print("Enter new quantity (0 to remove): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline
            Cart.updateItemQuantity(input, quantity);
        }
    }

    private void checkoutCart() {
        if (Cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        Cart.displayItems();
        System.out.println("Total: $" + Cart.calculateTotal());

        // Choose between takeaway or dine-in
        System.out.println("Choose an option for your order:");
        System.out.println("1 - Takeaway");
        System.out.println("2 - Dine-in");
        System.out.print("Your choice: ");
        String orderType = (scanner.nextInt() == 1) ? "Takeaway" : "Dine-in";
        scanner.nextLine(); // consume newline

        // Simulate payment process
        System.out.println("Proceeding to payment...");
        System.out.println("Enter payment amount: ");
        double payment = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (payment >= Cart.calculateTotal()) {
            System.out.println("Payment successful!");
            System.out.println("Receipt:");
            System.out.println("Total Paid: $" + payment);
            System.out.println("Change: $" + (payment - Cart.calculateTotal()));
            System.out.println("Order type: " + orderType);
            Cart.clear(); // Clear cart after successful transaction
            System.out.println("Thank you for your purchase!");
        } else {
            System.out.println("Insufficient payment. Transaction cancelled.");
        }
    }

    private void checkOrderStatus() {
        System.out.print("Enter your order ID: ");
        String orderId = scanner.nextLine();
        OrderStatus status = orderManager.getOrderStatus(orderId);
        if (status != null) {
            System.out.println("Order Status: " + status);
        } else {
            System.out.println("Order not found.");
        }
    }
}