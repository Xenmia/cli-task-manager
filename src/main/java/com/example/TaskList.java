package com.example;

import java.util.List;

public class TaskList {
  public static final String STRIKETHROUGH = "\033[9m";
  public static final String RESETSTRIKETHROUGH = "\033[0m";
  public static final String BOLD = "\u001B[1m";
  public static final String RESETBOLD = "\u001B[0m";

  private final TaskStorage storage;
  private final List<Task> tasks;

  public TaskList() {
    this.storage = new TaskStorage();
    this.tasks = storage.getTasks();
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  public void removeTask(int index) {
    if (checkIndex(index)) {
      this.tasks.remove(index);
      System.out.print("Task " + (index + 1) + " removed successfully.\n");
    }
  }

  public void switchTaskStatus(int index) {
    if (checkIndex(index)) {
      this.tasks.get(index).setStatus(!this.tasks.get(index).getStatus());
      System.out.print("Task " + (index + 1) + " status switched successfully.\n");
    }
  }

  public void switchTaskPriority(int index) {
    if (checkIndex(index)) {
      this.tasks.get(index).setPriority(!this.tasks.get(index).getPriority());
      System.out.print("Task " + (index + 1) + " priority switched successfully.\n");
    }
  }

  public void displayTasks() {
    if (this.tasks.isEmpty()) {
      System.out.println("The Task List is empty.\n");
      return;
    }
    for (int i = 0; i < this.tasks.size(); i++) {
      Task task = this.tasks.get(i);
      String[] formating = { "", "" };
      if (task.getStatus()) {
        formating[0] = STRIKETHROUGH;
        formating[1] = RESETSTRIKETHROUGH;
      } else if (task.getPriority()) {
        formating[0] = BOLD;
        formating[1] = RESETBOLD;
      }

      System.out.print(formating[0] + (i + 1) + ": " + task.getDescription() + formating[1]);
      System.out.print("\n");
    }
  }

  private boolean checkIndex(int index) {
    if (index < 0 || index >= this.tasks.size()) {
      System.out.print("Invalid index.\n");
      return false;
    }
    return true;
  }

  public void saveTasks() {
    storage.saveTasks();
  }
}
