package com.notes.notesappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainMenuController implements Initializable {

    @FXML
    private TextArea textField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button addNewNoteButton;

    @FXML
    private BorderPane mainPane;

    private List<String> tasks = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setCellFactory(param -> new TaskListCell());
        loadTasks();
    }

    @FXML
    protected void onAddButtonClickAction(ActionEvent e){
        String description = textField.getText();
        LocalDate selectedDate = datePicker.getValue();
        if (!description.isEmpty()) {
            String task = description + " - " + selectedDate.toString();
            listView.getItems().add(task);
            tasks.add(task);
            saveTasks();
            textField.clear();
        }
    }

    private void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.ser"))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        File file = new File("tasks.ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                tasks = (List<String>) ois.readObject();
                // Populate ListView with loaded tasks
                listView.getItems().addAll(tasks);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            tasks = new ArrayList<>();
        }
    }

    @FXML
    private void deleteTask() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            listView.getItems().remove(selectedIndex);
            tasks.remove(selectedIndex);
            saveTasks();
        }
    }

    @FXML
    private void editNoteBottonAction() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedTask = tasks.get(selectedIndex);
            // Extracting the description part without the date
            String[] parts = selectedTask.split(" - ");
            textField.setText(parts[0]); // Set the description to the text field
            // Clear the date picker selection
            datePicker.setValue(null);
        }
    }

    @FXML
    private void saveEditedButtonAction() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String editedTaskDescription = textField.getText();
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate == null) {
                // If no new date is specified, use the old date from the selected task
                String selectedTask = tasks.get(selectedIndex);
                // Extracting the date part
                String[] parts = selectedTask.split(" - ");
                String oldDate = parts.length > 1 ? parts[1] : "";
                // Reconstruct the task with the edited description and the old date
                editedTaskDescription += " - " + oldDate;
            } else {
                // If a new date is specified, use it
                editedTaskDescription += " - " + selectedDate.toString();
            }
            // Update the task list view and the tasks list
            tasks.set(selectedIndex, editedTaskDescription);
            listView.getItems().set(selectedIndex, editedTaskDescription);
            // Save the tasks to file
            saveTasks();
            // Clear the text field
            textField.clear();
            // Clear the date picker selection
            datePicker.setValue(null);
        }
    }

    @FXML
    private void aboutButtonAction() throws IOException {
        try {
            // Load the main menu FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutMe.fxml"));
            BorderPane aboutMe = loader.load();
            mainPane.setCenter(aboutMe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showNotesButtonAction(){
        mainPane.setCenter(listView);
    }
}
