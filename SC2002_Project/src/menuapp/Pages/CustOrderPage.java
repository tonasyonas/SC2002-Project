package menuapp.Pages;

import java.util.Scanner;
import java.util.Map;
import java.util.List;
import FOMS.Pages.IPage;
import FOMS.menu_manager.*;
import FOMS.branch_manager.*;
import FOMS.order_manager.*;
import FOMS.process_manager.DisplayOrder;

/**
 * The {@code CustOrderPage} class implements the {@code IPage} interface and facilitates the entire ordering process for customers.
 * This class handles menu browsing, item selection and customization, cart management, order finalization, and payment.
 * It leverages several components such as {@code ViewMenu}, {@code CartManager}, {@code OrderManager}, and {@code BranchSelector}
 * to provide a comprehensive ordering interface.
 *
 * <p>The class is designed to guide the user through selecting a branch, browsing the menu, adding items to the cart with
 * optional customizations, viewing and modifying the cart, checking out, and viewing order details. It supports both dine-in
 * and takeaway options.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */

public class CustOrderPage implements IPage{
    private Scanner scanner;
    private ViewMenu viewMenu; // handle menu display logic
    private CartManager cartManager; // handle cart operations
    private OrderManager orderManager;  // handle order operations
    private IBranchSelector branchSelector; // declare at class level
    private String selectedBranch; // New member to store the selected branch
    private String orderType; // to hold the order type across different parts of the process

    /**
     * Constructor for {@code CustOrderPage}. Initializes components used for managing orders and user interaction.
     */
    public CustOrderPage() {
        this.scanner = new Scanner(System.in);
        this.cartManager = new CartManager();
        this.orderManager = new OrderManager();
        this.branchSelector = new ConsoleBranchSelector(scanner, getBranches());
        this.viewMenu = new ViewMenu(new ConsoleMenuDisplay(), branchSelector, new MenuOrganizer(getMenuMap()));
        initializeDependencies();
    }

    /**
     * Initializes dependencies necessary for the operation of the menu and branch selection features.
     */
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

    /**
     * Parses a string and tries to convert it to an integer safely, returning null if the conversion fails.
     *
     * @param value the string to parse.
     * @return the integer value if successful, null otherwise.
     */
    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Retrieves the map of menu items from a file location.
     * This method loads the menu items from a text file and converts them into a map where each key is a unique identifier
     * (such as the item name) and the value is the corresponding {@code MenuItem} object.
     * The map is used throughout the class to manage menu-related operations, ensuring that all menu data is up-to-date
     * and accessible when needed.
     *
     * @return A map of {@code String} to {@code MenuItem} representing the menu items available.
     *         Each key is a unique identifier for the menu item, and the value is the {@code MenuItem} object containing details like name, price, and branch.
     */
    private Map<String, MenuItem> getMenuMap() {
        String filename = "SC2002_Project/src/FOMS/menu_manager/menu_list.txt";
        return ReadMenu.readMenuItems(filename);
    }

    /**
     * Retrieves an array of branch identifiers from a predefined file location.
     * This method loads the list of branch information from a text file and extracts the unique identifiers for each branch.
     * The identifiers are used throughout the class to facilitate branch-specific operations, such as displaying the menu
     * relevant to a selected branch or managing branch-specific orders.
     *
     * <p>This method leverages the {@code ReadBranchList.getBranchList} to fetch the branch details and then uses
     * {@code AllBranch.getBranchIDs} to extract just the identifiers, simplifying further processing and selection
     * in user interfaces.</p>
     *
     * @return An array of {@code String}, each representing a unique branch identifier.
     */

    private String[] getBranches() {
        List<Branch> branchlist = ReadBranchList.getBranchList("SC2002_Project/src/FOMS/branch_manager/branch_list.txt");
        return AllBranch.getBranchIDs(branchlist);
    }

    /**
     * Refreshes the menu items from the data source to ensure the menu is up-to-date before display.
     */
    private void refreshMenu() {
        // Reload the menu items from the file every time the menu needs to be displayed
        MenuOrganizer menuOrganizer = new MenuOrganizer(getMenuMap());
        this.viewMenu = new ViewMenu(new ConsoleMenuDisplay(), branchSelector, menuOrganizer);
    }


    /**
     * Starts the customer order page, displaying a menu and processing user actions through a loop until exit.
     */
    @Override
    public void startPage() {
        display();
        do {
            showOptions();
            String input = scanner.nextLine().trim();
            Integer choice = tryParseInt(input);  // improved to handle non-integer input
            if (choice == null) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Your choice: ");
                continue; // Skip the rest of the loop iteration
            }
            switch (choice) {
                case 1:
                    if (selectedBranch == null) {
                        selectedBranch = branchSelector.selectBranch();
                    }
                    refreshMenu();
                    viewMenu.displayMenuForBranch(selectedBranch);
                    addItemsToCart();
                    break;
                case 2:
                    modifyCart();
                    break;
                case 3:
                    if (checkoutCart()) { // This will now also set the orderType
                        finalizeOrder(this.orderType); // Pass the order type stored in the instance variable
                    }
                    break;
                case 4:
                    System.out.print("Enter your order ID: ");
                    String orderId = scanner.nextLine();
                    DisplayOrder.viewOrderDetails(orderId);
                    break;
                case 5:
                    Scanner collectscanner = new Scanner(System.in);
                    CollectOrderPage collectOrderPage = new CollectOrderPage(collectscanner, orderManager);
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


    /**
     * Displays the welcoming message and introductory text for the ordering system.
     */
    @Override
    public void display() {
        System.out.println("Welcome to the Fast Food Ordering System!");
    }

    /**
     * Captures the user's input from the console.
     *
     * @return The user's input as a trimmed string.
     */
    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Displays available options for the customer to select from, including browsing the menu, managing the cart, and more.
     */
    private void showOptions() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1 - Browse Menu");
        System.out.println("2 - View/Edit Cart");
        System.out.println("3 - Checkout");
        System.out.println("4 - Check Order Status");
        System.out.println("5 - Collect Order");
        System.out.println("6 - Exit");
        System.out.println("Your choice: ");
    }

    /**
     * Manages adding items to the user's cart, including handling item selection and customization.
     */
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
                if (boolCustomisation.equalsIgnoreCase("yes")) {
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
                    System.out.println("Item added to cart with no customizations.");

                }
                
             
                
            } else {
                System.out.println("Invalid item number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'exit'.");
        }
    }
    
    /**
     * Provides functionality for modifying items in the cart, including changing quantities or removing items.
     */
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
                                    System.out.println("Item added to cart with no customizations.");

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
    
    
        
    /**
     * Handles the checkout process, confirming items and quantities in the cart, calculating totals, and managing payment.
     *
     * @return true if the checkout process is successful, false otherwise.
     */
    private boolean checkoutCart() {
        if (cartManager.isEmpty()) {
            System.out.println("Your cart is empty. Please add items before checkout.");
            return false;
        }
        // Prompt for order type before initiating payment
        this.orderType = getOrderTypeFromUser(); // Store order type in the instance variable
        cartManager.displayItems();
        double total = cartManager.calculateTotal();
        System.out.printf("Total: $%.2f\n", total);
        
        // Handle payment
        boolean paymentSuccessful = handlePayment(total);
        return paymentSuccessful;
    }

    
    /**
     * Handles payment processing based on user-selected payment method.
     *
     * @param total the total amount to be paid.
     * @return true if the payment was successful, false otherwise.
     */
    private boolean handlePayment(double total) {
        System.out.println("Payment methods: ");
        GetPaymentMethods.displayPaymentMethods();
        System.out.print("Choose your payment method: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Selected payment method: ");
        GetPaymentMethods.displayPaymentMethod(paymentChoice);
        System.out.print("Payment successful using ");
        GetPaymentMethods.displayPaymentMethod(paymentChoice);
        return true;
    }


    /**
     * Finalizes the order, generating an order ID, saving order details, and displaying the order receipt.
     *
     * @param orderType the type of the order (e.g., 'Takeaway' or 'Dine-in').
     */
    private void finalizeOrder(String orderType) {
        String orderId = orderManager.placeOrder(cartManager, orderType);
        if (orderId != null) {
            Order order = orderManager.getOrderById(orderId);
            if (order != null) {
                System.out.println("Order ID: " + orderId + " has been placed successfully.");
                orderManager.saveOrderToFile(order, selectedBranch, "SC2002_Project/src/FOMS/order_manager/order.txt");
                System.out.println("Receipt: ");
                DisplayOrder.viewOrderDetails(orderId);
                
            } else {
                System.out.println("Error: Order not found after payment.");
            }
        } else {
            System.out.println("There was an error placing your order.");
        }
    }
    
    /**
     * Gets the user's preferred order type by prompting them to choose between options like 'Takeaway' or 'Dine-in'.
     *
     * @return the selected order type as a string.
     */
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
            return getOrderTypeFromUser();  // Recursive call to re-prompt the user
        }
    }
    /**
     * Closes the scanner when the class is no longer in use to prevent resource leaks.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}