package org.example.bicyclesharing;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javafx.application.Application;

public class Launcher {

  public static void main(String[] args) {
    File logFile = new File(System.getProperty("user.home"), "BicycleSharing-crash.log");

    try (PrintWriter writer = new PrintWriter(logFile)) {
      writer.println("App started: " + LocalDateTime.now());
      writer.println("Working dir: " + System.getProperty("user.dir"));
      writer.println("Java: " + System.getProperty("java.version"));
      writer.flush();

      Application.launch(HelloApplication.class, args);

      writer.println("App closed normally");
    } catch (Throwable e) {
      try (PrintWriter writer = new PrintWriter(logFile)) {
        writer.println("App crashed: " + LocalDateTime.now());
        e.printStackTrace(writer);
      } catch (Exception ignored) {
      }
    }
  }
}
