package org.ntnu.console;

import java.util.Scanner;


/**
 * InputHandler is responsible for waiting for user's input and trimming it so it can be safely passed
 * to commandRegistry for executing commands.
 */
public class InputHandler {
	private final Scanner scanner;
	private final DisplayManager displayManager;

	public InputHandler() {
		this.scanner = new Scanner(System.in);
		displayManager = new DisplayManager();
	}

	// get input from user
	public String getInput() {
		System.out.print("Enter command: ");
		return scanner.nextLine().trim();
	}

	// mockInput for unit testing
	public String mockInput(String input) {
		return input.trim();
	}
}
