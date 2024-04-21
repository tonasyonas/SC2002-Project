package menuapp.Pages;

import FOMS.Pages.IPage;
import java.util.Scanner;

public class CustOrderPage implements IPage{

    @Override
    public void startPage() {
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Welcome to the ordering system!");
    }

    @Override
    public String getInput() {   
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toLowerCase();
        return choice;
    }

}
