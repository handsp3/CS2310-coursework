/**
 * 
 */
package io;

import java.util.Scanner;
import astaire.Controller;

/**
 * A simple text-based user interface for the dance show programme generator.
 * 
 * @author S H S Wong
 * @version  08/11/2018
 */
public class TUI {

	private Controller controller;  
	private Scanner stdIn;
	
	public TUI(Controller controller) {
		
		this.controller = controller;
		
		// Creates a Scanner object for obtaining user input
		stdIn = new Scanner(System.in);
		
		while (true) {
			displayMenu();
			getAndProcessUserOption();
		}
	}

	/**
	 * Displays the header of this application and a summary of menu options.
	 */
	private void displayMenu() {
		display(header());
		display(menu());
	}
	
	/**
	 * Obtains an user option and processes it.
	 */
	private void getAndProcessUserOption() {
		String command = stdIn.nextLine().trim();
		switch (command) {
		case "1" : // Lists all dancers in a dance
			display("Lists all dancers in a dance...");
			display("Enter the name of the required dance:");
			display(controller.listAllDancersIn(stdIn.nextLine().trim()));
			break;
		case "2" : // Lists all dances and respective dancers
			display("Lists all dance numbers and the respective dancers...");
			display(controller.listAllDancesAndPerformers());
			break;
		case "3" : // Checks the feasibility of a given running order
			display("Checks the feasibility of a given running order...");
			display("Enter the name of the CSV file with the proposed running order:");
			String dataFile = stdIn.nextLine().trim();
			display("Enter the required number of gaps between dances:");
			String gap = stdIn.nextLine().trim();
			try {
				display(controller.checkFeasibilityOfRunningOrder(dataFile, (new Integer(gap)).intValue()));
			} catch (NumberFormatException e) {
				display("You have not entered the number of gaps as an integer. Sorry, no checking can be done.");
			}
			break;
		case "4" : // Generates a running order of all dance numbers
			display("Generates a running order...");
			display("Enter the required number of gaps between dances:");
			try {
				display(controller.generateRunningOrder((new Integer(stdIn.nextLine().trim())).intValue()));
			} catch (NumberFormatException e) {
				display("You have not entered the number of gaps as an integer. Sorry, no checking can be done.");
			}
			break;
		case "5" : // Exits the application
			display("Goodbye!");
			System.exit(0);
			break;
		default : // Not a known command option
			display(unrecogniseCommandErrorMsg(command));
		}
	}
	
	/*
	 * Returns a string representation of a brief title for this application as the header.
	 * @return	a header
	 */
	private static String header() {
		return "\nDance Show Programme Generator\n";
	}
	
	/*
	 * Returns a string representation of the user menu.
	 * @return	the user menu
	 */
	private static String menu() {
		return "Enter the number associated with your chosen menu option.\n" +
			   "1: List all dancers in a dance\n" +
			   "2: List all dance numbers and the respective dancers\n" +
		       "3: Check the feasibility of a given running order\n" +
			   "4: Generate a running order\n" +
			   "5: Exit this application\n";
	}
	
	/*
	 * Displays the specified info for the user to view.
	 * @param info	info to be displayed on the screen
	 */
	private void display(String info) {
		System.out.println(info);
	}
	
    /*
     * Returns an error message for an unrecognised command.
     * 
     * @param error the unrecognised command
     * @return      an error message
     */
    private static String unrecogniseCommandErrorMsg(String error) {
            return String.format("Cannot recognise the given command: %s.%n", error);
    }
}
