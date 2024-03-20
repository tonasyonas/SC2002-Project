package menuapp;  
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;




public class App {
	public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
		final String staff_list = "staff_list.txt";
		account_manager.loadDB(staff_list);//load staff database
		final String menu_list="menu_list.txt";
		menu_manager.loadDB(menu_list); //load menu database
		final String TABLEFILE="tableLayoutDB.txt";
		branch_manager.loadDB(branch_list); //Load branch database
		final String branch_list="branch_list.txt";		
		
    }
}