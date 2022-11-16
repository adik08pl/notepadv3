package com.mkrzyzowski.mynotepad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MainController {
    public TextField txtFilePath;
    public TextArea txtContent;
    public Button btnToUpperCase;
    public Button btnToLowerCase;
    public Button btnClear;
    public Button btnAddDate;
    public Button btnShowStatistics;
    public Button btnAddTime;
    public Button btnGeneratorHasel;
    public Button btnSaveFile;
    public DatePicker datePicker;

    Random random = new Random();
    boolean czyOtwartyPlik;
    @FXML
    public void inicialize(){
        czyOtwartyPlik = false;
    }
    public void readFile(ActionEvent actionEvent) {
        txtContent.clear();

        if(czyOtwartyPlik == true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Czy chcesz zapisać zmiany w tym pliku?");
            alert.setContentText("");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                boolean saveResult = FileHelper.saveFile(txtFilePath.getText(), txtContent.getText());
                if (!saveResult)
                    return;

                txtContent.clear();
                setComponentsDisabled(true);
            }
        }


        List<String> fileContent = FileHelper.loadFileContent(txtFilePath.getText());
        if (fileContent == null)
            return;

        fileContent.forEach(line -> txtContent.appendText(line + "\n"));
        setComponentsDisabled(false);
        czyOtwartyPlik = true;
    }
    public void saveFile(ActionEvent actionEvent) {
        boolean saveResult = FileHelper.saveFile(txtFilePath.getText(), txtContent.getText());
        if (!saveResult)
            return;

        txtContent.clear();
        setComponentsDisabled(true);
    }

    public void toUpperCase(ActionEvent actionEvent) {
        String content = txtContent.getText();
        content = content.toUpperCase();
        txtContent.setText(content);
    }

    public void toLowerCase(ActionEvent actionEvent) {
        String content = txtContent.getText();
        content = content.toLowerCase();
        txtContent.setText(content);
    }

    public void clear(ActionEvent actionEvent) {
        txtContent.clear();
    }

    public void addDate(ActionEvent actionEvent) {
        String content = txtContent.getText();
        content = String.format("%s \nData: %s", content, LocalDate.now());
        txtContent.setText(content);
    }

    public void addTime(ActionEvent actionEvent) {
        String content = txtContent.getText();
        content = String.format("%s \nGodzina: %s", content, LocalTime.now());
        txtContent.setText(content);
    }

    public void showStatistics(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Statystyki pliku");
        alert.setHeaderText("W pliku znajduje się: ");
        alert.setContentText("Liter: ");
        alert.showAndWait();
    }

    private void setComponentsDisabled(boolean disabled) {
        btnAddDate.setDisable(disabled);
        datePicker.setDisable(disabled);
        btnSaveFile.setDisable(disabled);
        btnAddTime.setDisable(disabled);
        btnClear.setDisable(disabled);
        btnShowStatistics.setDisable(disabled);
        btnToLowerCase.setDisable(disabled);
        btnToUpperCase.setDisable(disabled);
        btnGeneratorHasel.setDisable(disabled);
        txtContent.setDisable(disabled);
    }

    public void GeneratorHasel(ActionEvent actionEvent) {
        String haslo = " ";
        int losowo = 0;
        for (int i = 0; i < 5; i++) {
            losowo = random.nextInt(26) + 97;
            char male = (char) losowo;
           haslo = haslo + male;
        }
        for (int i = 0; i < 3; i++) {
            losowo = random.nextInt(26) + 65;
            char duze = (char) losowo;
            haslo = haslo + duze;
        }
        for (int i = 0; i < 2; i++) {
            losowo = random.nextInt(9);
            haslo = haslo + losowo;
        }
        String content = txtContent.getText();
        content = String.format(content + "\nAutomarycznie wygenerowane hasło: " + haslo);
        txtContent.setText(content);
    }

    public void datePickerWybor(ActionEvent actionEvent) {
        String content = txtContent.getText();
        content = String.format("%s \nData: %s", content, datePicker.getValue());
        txtContent.setText(content);
    }
}