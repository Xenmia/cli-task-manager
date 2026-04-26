package com.example;

public class Task {

  private String description;
  private boolean completed;
  private boolean priority;

  public Task(String description) {
    this.description = description;
    this.completed = false;
    this.priority = false;
  }

  // Getters
  public String getDescription() {
    return this.description;
  }

  public boolean getStatus() {
    return this.completed;
  }

  public boolean getPriority() {
    return this.priority;
  }

  // Setters
  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(boolean status) {
    this.completed = status;
  }

  public void setPriority(boolean priority) {
    this.priority = priority;
  }

}
