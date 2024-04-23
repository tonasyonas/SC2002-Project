package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import java.util.List;
import FOMS.Pages.IPage;
import FOMS.menu_manager.*;
import FOMS.branch_manager.*;
import FOMS.order_manager.*;
// import java.text.DecimalFormat;
// import java.util.HashMap;
// import java.util.ArrayList;
// import FOMS.order_manager.CreditCardPayment;
// import FOMS.order_manager.OnlinePaymentPlatform;

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
        List<Branch> branchlist = ReadBranchList.getBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");
        String[] branches = AllBranch.getBranchIDs(branchlist);
        this.branchSelector = new ConsoleBranchSelector(scanner, branches);
        String filename = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
        Map<String, MenuItem> menuMap = ReadMenu.readMenuItems(filename);
        MenuOrganizer menuOrganizer = new MenuOrganizer(menuMap);
        this.viewMenu = new ViewMenu(menuDisplay, branchSelector, menuOrganizer);
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    @Override
    public void startPage() {
        display();
        showOptions();
        do {
            String input = scanner.nextLine().trim();
            Integer choice = tryParseInt(input);  // improved to handle non-integer input
            if (choice == null) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Your choice: ");
                continue; // Skip the rest of the loop iteration
            }
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
                    if(checkoutCart()){
                        finalizeOrder();
                    };
                    break;
                case 4:
                    checkOrderStatus();
                    break;
                case 5:
                    Scanner collectscanner = new Scanner(System.in);
                    CollectOrderPage collectOrderPage = new CollectOrderPage(collectscanner);
                    collectOrderPage.startPage();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please choose again.");
                    System.out.print("Your choice: ");
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
        System.out.println("5 - Collect Order");
        System.out.println("6 - Exit");
        if (selectedBranch != null) {
            System.out.println("6 - Change Branch");
        }
        System.out.print("Your choice: ");
    }

    private void addItemsToCart() {
        System.out.println("Enter the item number to add to cart or 'exit' to return:");
        String input = scanner.nextLine().trim().toLowerCase();
    
        if ("exit".equals(input)) {
            return;
        }
    
        try {
            int itemNumber = Integer.parseInt(input);
            MenuItem selectedItem = viewMenu.getMenuItemByNumber(itemNumber, selectedBranch);
    
            if (selectedItem != null) {
                System.out.print("Enter quantity: ");
                int quantity = Integer.parseInt(scanner.nextLine());
                System.out.println("Yes or No: Do you want any customisations");
                String boolCustomisation = scanner.nextLine().trim();
                if (boolCustomisation.equals("yes")) {
                    System.out.println("Entering customization loop...");
                    scanner.nextLine(); // Consume newline character
                    for (int i = 0; i < quantity; i++) {
                        System.out.printf("Customisation for %s %d, e.g. 'no onions', press enter if none: ", selectedItem.getItem(), i+1);
                        String customization = scanner.nextLine().trim();
                        MenuItem item = new MenuItem(selectedItem.getItem(), selectedItem.getCost(), selectedItem.getBranch());
                        item.setCustomizations(customization);
                        cartManager.addItem(item, 1, customization);
                        System.out.println("Item added to cart with the following customizations: " + customization);
                    }
                    System.out.println("Exiting customization loop...");
                }
                else {
                    MenuItem item = new MenuItem(selectedItem.getItem(), selectedItem.getCost(), selectedItem.getBranch()); //need change 
                    String customization = "";
                    cartManager.addItem(item, quantity, customization);
                    System.out.println("Item added to cart with the following customizations: " + customization);

                }
                
             
                
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'exit'.");
        }
    }
        
    


    private void modifyCart() {
        while (true) {
            int itemNumber = 1;
            // Display cart items with item numbers and customizations
            System.out.println("Cart Items:");
            for (Map.Entry<OrderItem, Integer> entry : cartManager.getItems().entrySet()) {
                OrderItem orderItem = entry.getKey();
                MenuItem item = orderItem.getMenuItem();
                int quantity = entry.getValue();
                String customizations = orderItem.getCustomization(); // Customization is now part of OrderItem
                String displayCustomization = customizations.isEmpty() ? "No customization" : customizations;
                System.out.println(itemNumber + ". " + item.getItem() + " - Quantity: " + quantity + " - Customization: " + displayCustomization);
                itemNumber++; // Increment item number
            }
    
            
            
    
            // Prompt user for action
            System.out.println("\nEnter the item number to update, 'clear' to empty the cart, or 'back' to return:");
            String input = scanner.nextLine().trim().toLowerCase();
    
            // Perform actions based on user input
            switch (input) {
                case "clear":
                    cartManager.clearCart();
                    return;
                case "back":
                    return;
                default:
                    try {
                        int selectedNumber = Integer.parseInt(input);
                        if (selectedNumber >= 1 && selectedNumber <= cartManager.getItems().size()) {
                            // Get the selected item based on item number
                            OrderItem[] orderItemsArray = cartManager.getItems().keySet().toArray(new OrderItem[0]);
                            OrderItem selectedOrderItem = orderItemsArray[selectedNumber - 1];
                            MenuItem selectedMenuItem = selectedOrderItem.getMenuItem();

    
                            // Prompt for new quantity
                            System.out.print("Enter new quantity (0 to remove): ");
                            int quantity = Integer.parseInt(scanner.nextLine());
                            cartManager.removeItem(selectedMenuItem, selectedOrderItem.getCustomization());
                            if (quantity > 0) {
                                System.out.println("Yes or No: Do you want any customisations");
                                String boolCustomisation = scanner.nextLine().trim();
                                if (boolCustomisation.equals("yes")) {
                                    System.out.println("Entering customization loop...");
                                    scanner.nextLine(); // Consume newline character
                                    for (int i = 0; i < quantity; i++) {
                                        System.out.printf("Customisation for %s %d, e.g. 'no onions', press enter if none: ", selectedMenuItem.getItem(), i+1);
                                        String customization = scanner.nextLine().trim();
                                        MenuItem item = new MenuItem(selectedMenuItem.getItem(), selectedMenuItem.getCost(), selectedMenuItem.getBranch());
                                        item.setCustomizations(customization);
                                        cartManager.addItem(item, 1, customization);
                                        System.out.println("Item added to cart with the following customizations: " + customization);
                                    }
                                    System.out.println("Exiting customization loop...");
                                }
                                else {
                                    MenuItem item = new MenuItem(selectedMenuItem.getItem(), selectedMenuItem.getCost(), selectedMenuItem.getBranch()); //need change 
                                    String customization = "";
                                    cartManager.addItem(item, quantity, customization);
                                    System.out.println("Item added to cart with the following customizations: " + customization);

                                }
                                
                             
                            }
                        } else {
                            System.out.println("Invalid item number. Please try again.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
            }
        }
    }
    
    
        
    
    
    private boolean checkoutCart() {
        if (cartManager.isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checkout.");
            return false;
        }
        String orderType = getOrderTypeFromUser();

        cartManager.displayItems();
        double total = cartManager.calculateTotal();
        System.out.printf("Total: $%.2f\n", total);
        boolean paymentSuccessful = handlePayment(total);

    // If payment is successful, finalize the order
        if (paymentSuccessful) {
            finalizeOrder(); // Pass the order type to finalizeOrder method
        }

        return paymentSuccessful;
    }

    private boolean handlePayment(double total) {
        System.out.println("Payment methods: ");
        GetPaymentMethods.displayPaymentMethods();
        System.out.print("Choose your payment method: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Selected payment method: ");
        GetPaymentMethods.displayPaymentMethod(paymentChoice);

        System.out.println("Enter payment amount: ");
        double payment = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        if (payment < total) {
            System.out.println("Insufficient payment. Transaction cancelled.");
            return false;
        }

        System.out.print("Payment successful using ");
        GetPaymentMethods.displayPaymentMethod(paymentChoice);
        return true;
    }


    private void finalizeOrder() {
        String orderType = getOrderTypeFromUser();
        String orderId = orderManager.placeOrder(cartManager, orderType);
        if (orderId != null) {
            Order order = orderManager.getOrderById(orderId);
            if (order != null) {
                System.out.println("Order ID: " + orderId + " has been placed successfully.");
                orderManager.saveOrderToFile(order, selectedBranch, "SC2002_Project/src/FOMS/order_manager/order.txt");
            } else {
                System.out.println("Error: Order not found after payment.");
            }
        } else {
            System.out.println("There was an error placing your order.");
        }
    }
    private String getOrderTypeFromUser() {
        System.out.println("Select order type:");
        System.out.println("1 - Takeaway");
        System.out.println("2 - Dine-in");
        System.out.print("Your choice: ");
        String input = scanner.nextLine().trim();
        Integer choice = tryParseInt(input);

        if (choice != null) {
            return (choice == 1) ? "Takeaway" : "Dine-in";
        } else {
            System.out.println("Invalid input. Please enter 1 for Takeaway or 2 for Dine-in.");
            return getOrderTypeFromUser(); // Recursive call to re-prompt the user
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