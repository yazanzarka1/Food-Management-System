package edu.ntnu.idi.idatt.console;

import org.fusesource.jansi.Ansi.Color;

/**
 * Main application class for the console application.
 *
 * @author yazanzarka
 * @version 0.0.8
 * @since 0.0.1
 */
public class Ui {

  private final DisplayManager displayManager = new DisplayManager();
  private final InputHandler inputHandler = new InputHandler(displayManager);
  private Boolean displayMenu = false;
  private CommandRegistry commandRegistry;

  /**
   * Initialize the application with predefined menus, commands and contexts.
   *
   * @param commandRegistry CommandRegistry to use for the application.
   */
  public void init(CommandRegistry commandRegistry) {
    this.commandRegistry = commandRegistry;
  }

  /**
   * Start the application. Main loop for the application. Get input from user and execute commands.
   * Display menu for current context if user types "help". Exit application if user types "exit".
   */
  public void start() {
    while (true) {
      if (Boolean.TRUE.equals(displayMenu)) {
        commandRegistry.getCurrentContext().displayMenu();
      }
      String input = inputHandler.getInput("Enter command (help - Available Commands): ");

      // Check for help command
      if ("help".equalsIgnoreCase(input)) {
        displayManager.showMessage(commandRegistry.getCurrentContext().getName() + " - Help");
        commandRegistry.getCurrentContext().displayMenu();
        continue;
      }

      // Check for exit command
      if ("exit".equalsIgnoreCase(input)) {
        displayManager.showMessage("Exiting application...");
        displayManager.shutdown();
        break;
      }

      try {
        displayMenu = commandRegistry.executeCommand(input);
      } catch (Exception e) {
        commandRegistry.getCurrentContext().displayMenu();
        displayManager.showColoredMessage(e.getMessage(), Color.RED);
      }
    }
  }
}

