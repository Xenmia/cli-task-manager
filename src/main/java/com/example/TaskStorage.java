package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaskStorage {
  private final Gson gson = new Gson();
  private List<Task> tasks;
  private static final String FOLDER_PATH = "learning/src/main/resources/Data/";
  private static final String FILE_NAME = "tasks.json";

  public TaskStorage() {
    try {
      // 1. Read file into a String
      String content = Files.readString(Path.of(FOLDER_PATH + FILE_NAME));

      // 2. Convert JSON -> Java objects
      this.tasks = gson.fromJson(content, new TypeToken<List<Task>>() {
      }.getType());

      // 3. gson returns null
      if (this.tasks == null) {
        this.tasks = new ArrayList<>();
      }
    } catch (IOException e) {
      // File missing / unreadable -> start empty
      this.tasks = new ArrayList<>();
    }
  }

  public List<Task> getTasks() {
    return this.tasks;
  }

  public void saveTasks() {
    try {
      String json = gson.toJson(this.tasks);
      Files.createDirectories(Path.of(FOLDER_PATH));
      Files.writeString(Path.of(FOLDER_PATH + FILE_NAME), json);
    } catch (IOException e) {
      System.out.print("Error saving file");
    }
  }
}
