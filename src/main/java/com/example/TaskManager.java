package com.example;

import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
  private final TaskList taskList = new TaskList();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TaskManager manager = new TaskManager();

    boolean running = true;

    while (running) {
      System.out.print("TaskManager> ");
      String input = scanner.nextLine().trim(); // make it case-insensitive

      if (input.isEmpty()) {
        continue; // just press Enter → reprompt
      }

      String[] parts = input.split(" ", 2); // split into command + rest
      String command = parts[0].toLowerCase();
      String arguments = parts.length > 1 ? parts[1] : "";

      switch (command) {
        case "exit":
        case "quit":
        case "q":
          // Quit the Task Manager
          running = false;
          continue;

        case "list":
        case "view":
        case "ls":
          // View Task List
          manager.listTasks();
          break;

        case "add":
          // Add a task to the Task List
          if (arguments.isEmpty()) {
            System.out.println("Error: Please provide a task description.");
          } else {
            manager.addTask(arguments);
            System.out.print("Task was added to the list!\n");
          }
          break;

        case "remove":
        case "rm":
        case "complete":
        case "comp":
        case "priority":
        case "pr":
          if (arguments.isEmpty()) {
            System.out.println("Error: Please provide a task index.");
          } else {
            manager.handleIndexedCommand(command, arguments);
          }
          break;

        case "save":
          manager.saveTasks();
          break;

        default:
          System.out.println("Error: Invalid input, try again...");
          continue;
      }
    }

    scanner.close();

  }

  private void listTasks() {
    taskList.displayTasks();
  }

  private void handleIndexedCommand(String comm, String arguments) {
    String[] tokens = arguments.split("\\s+");
    int length = tokens.length;
    int[] indexList = new int[length];
    for (int i = 0; i < length; i++) {
      try {
        indexList[i] = Integer.parseInt(tokens[i]) - 1;
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid index '" + tokens[i] + "'");
        return;
      }
    }
    Arrays.sort(indexList);
    for (int i = length - 1; i >= 0; i--) {
      switch (comm) {
        case "complete":
        case "comp":
          switchTaskStatus(indexList[i]);
          break;

        case "priority":
        case "pr":
          switchTaskPriority(indexList[i]);
          break;

        case "remove":
        case "rm":
          removeTask(indexList[i]);
          break;

        default:
          System.out.println("Error: unkown command '" + comm + "'");
          break;
      }
    }
  }

  private void removeTask(int index) {
    taskList.removeTask(index);
    taskList.saveTasks();
  }

  private void switchTaskStatus(int index) {
    taskList.switchTaskStatus(index);
    taskList.saveTasks();
  }

  private void switchTaskPriority(int index) {
    taskList.switchTaskPriority(index);
    taskList.saveTasks();
  }

  private void addTask(String arguments) {
    String[] tokens = arguments.split("\\s+");
    StringBuilder tempDescription = new StringBuilder();
    boolean completed = false;
    boolean priority = false;
    boolean notDuplicatedC = true;
    boolean notDuplicatedP = true;
    boolean startedDescription = false;

    for (String token : tokens) {
        if (token.startsWith("-") && !startedDescription) {
            switch (token) {
                case "-c":
                case "--completed":
                    if (notDuplicatedC) {
                        completed = true;
                        notDuplicatedC = false;
                    } else {
                        System.out.println("Warning: duplicated " + token + " flag will be ignored.");
                    }
                    break;
                case "-p":
                case "--priority":
                    if (notDuplicatedP) {
                        priority = true;
                        notDuplicatedP = false;
                    } else {
                        System.out.println("Warning: duplicated " + token + " flag will be ignored.");
                    }
                    break;
                default:
                    System.out.println("Error: flag " + token + " is invalid.");
                    return;
            }
        } else {
            startedDescription = true;
            tempDescription.append(token).append(" ");
        }
    }
    String description = tempDescription.toString().trim();
    Task task = new Task(description);
    task.setStatus(completed);
    task.setPriority(priority);
    taskList.addTask(task);
    taskList.saveTasks();
  }

  private void saveTasks() {
    taskList.saveTasks();
  }
}
