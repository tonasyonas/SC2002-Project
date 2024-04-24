package FOMS.Pages;

/**
 * The {@code IPage} interface defines the essential structure for pages within the FOMS.
 * This interface is implemented by various classes to ensure a consistent approach to displaying content, handling user input,
 * and managing the lifecycle of a page within the application.
 *
 * <p>Implementing classes are expected to provide specific functionalities for different pages of the application, such as
 * login pages, menu management pages, staff management pages, etc. This interface enforces a uniform method structure that
 * aids in maintaining the flow and navigation consistency throughout the application.</p>
 *
 * <ul>
 *   <li>{@link #display()} - Method to display the page's specific content to the user.</li>
 *   <li>{@link #getInput()} - Method to capture input from the user.</li>
 *   <li>{@link #startPage()} - Method to initiate the logic associated with the page.</li>
 * </ul>
 *
 * <p>This interface allows for flexibility in the implementation while ensuring that all pages adhere to a certain protocol
 * for user interaction and data processing.</p>
 *
 * @author Donovan, Sailesh, Kellie, Jonas, Jo Wee
 * @version 1.0
 * @since 2024-04-24
 */
public interface IPage {
    
     /**
     * Displays the page's content to the user. This method should be used to present the static text, forms, and
     * other interface elements that are specific to the particular page being rendered.
     */
    void display();

    /**
     * Captures and returns user input. This method should handle reading from the console or other input sources,
     * and may include validation or preprocessing of the input before returning it.
     *
     * @return A {@code String} representing the user's input.
     */
    String getInput();

     /**
     * Starts the core logic associated with the page. This method is typically called to initiate the processing
     * of user inputs, trigger actions based on those inputs, and manage transitions to other pages.
     */
    void startPage();

}
