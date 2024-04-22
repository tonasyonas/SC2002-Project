package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import FOMS.Pages.IPage;
import FOMS.menu_manager.*;
import FOMS.branch_manager.*;
import FOMS.order_manager.*;

public class CustOrderPage implements IPage{
    private Scanner scanner;
    private ViewMenu viewMenu; // handle menu display logic
    private CartManager cartManager; // handle cart operations
    private OrderManager orderManager;  // handle order operations
    private BranchSelector branchSelector; // declare at class level
    private String selectedBranch; // New member to store the selected branch


    public CustOrderPage() {
        this.scanner = new Scanner(System.in);
        this.cartManager = new CartManager();
        this.orderManager = new OrderManager();
        initializeDependencies();
    }

    private void initializeDependencies() {
        MenuDisplay menuDisplay = new ConsoleMenuDisplay();
        String[] branches = ReadBranchList.getBranchIDs("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");
        this.branchSelector = new ConsoleBranchSelector(scanner, branches);
        String filename = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
        Map<String, MenuItem> menuMap = ReadMenu.readMenuItems(filename);
        MenuOrganizer menuOrganizer = new MenuOrganizer(menuMap);
        this.viewMenu = new ViewMenu(menuDisplay, branchSelector, menuOrganizer);
    }

    
    @Override
    public void startPage() {
        display();
        int choice;
        do {
            showOptions();
            choice = Integer.parseInt(scanner.nextLine());  // improved to handle non-integer input
            if (choice == 1 && selectedBranch == null) {
                // Initial branch selection
                selectedBranch = branchSelector.selectBranch();
            }
            switch (choice) {
                case 1:
                    viewMenu.displayMenuForBranch(selectedBranch);
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
                case 6:
                    selectedBranch = branchSelector.selectBranch();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        } while (true);
    }


    @Override
    public void display() {
        System.out.println("Welcome to the Fast Food Ordering System!");
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    private void showOptions() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1 - Browse Menu");
        System.out.println("2 - View/Edit Cart");
        System.out.println("3 - Checkout");
        System.out.println("4 - Check Order Status");
        System.out.println("5 - Exit");
        if (selectedBranch != null) {
            System.out.println("6 - Change Branch");
        }
        System.out.print("Your choice: ");
    }

    private void addItemsToCart() {
        System.out.println("Enter the item name to add to cart or 'exit' to return:");
        String itemName = scanner.nextLine();
        while (!itemName.equalsIgnoreCase("exit")) {
            MenuItem item = viewMenu.getMenuItem(itemName, selectedBranch);
            if (item != null) {
                System.out.print("Enter quantity: ");
                try {
                    int quantity = Integer.parseInt(scanner.nextLine());
                    cartManager.addItem(item, quantity);
                    System.out.println("Item added to cart.");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number for the quantity.");
                }
            } else {
                System.out.println("Item not found. Please try again.");
            }
            System.out.println("Enter the item name to add to cart or 'exit' to return:");
            itemName = scanner.nextLine();
        }
    }
    


    private void modifyCart() {
        cartManager.displayItems();
        System.out.println("Enter the item index to update or 'clear' to empty the cart:");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("clear")) {
            cartManager.clearCart();
        } else {
            try {
                int itemIndex = Integer.parseInt(input) - 1; // user sees list starting at 1
                List<MenuItem> itemsList = new ArrayList<>(cartManager.getItems().keySet());
                if (itemIndex >= 0 && itemIndex < itemsList.size()) {
                    MenuItem itemToUpdate = itemsList.get(itemIndex);
                    System.out.print("Enter new quantity (0 to remove): ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    cartManager.updateItemQuantity(itemToUpdate, quantity);
                } else {
                    System.out.println("Invalid item index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private void checkoutCart() {
        if (cartManager.isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checkout.");
            return;
        }
        cartManager.displayItems();
        double total = cartManager.calculateTotal();
        System.out.println("Total: $" + total);

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

        if (payment >= total) {
            System.out.println("Payment successful!");
            String orderId = orderManager.placeOrder(cartManager, orderType); // This should clear the cart and create an order
            System.out.println("Receipt:");
            System.out.println("Total Paid: $" + payment);
            System.out.println("Change: $" + (payment - total));
            System.out.println("Order type: " + orderType);
            System.out.println("Order ID: " + orderId);
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
    // Make sure to include finalize or a method to close the scanner at the end of using this class
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}