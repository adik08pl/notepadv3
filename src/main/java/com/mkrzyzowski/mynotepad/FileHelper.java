package com.mkrzyzowski.mynotepad;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHelper {
    public static List<String> loadFileContent(String filePath) {
        try {
            List<String> result = Files.readAllLines(Path.of(filePath));
            return result;
        } catch (IOException e) {
            showError("Błąd ładowania pliku");
            System.err.println(e);
            return null;
        }
    }

    public static boolean saveFile(String filePath, String text) {
        try {
            Files.write(Path.of(filePath), text.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            showError("Błąd zapisu pliku");
            System.err.println(e);
            return false;
        }
    }

    private static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(message);
        alert.setContentText("Spróbuj ponownie");
        alert.showAndWait();
    }
}
