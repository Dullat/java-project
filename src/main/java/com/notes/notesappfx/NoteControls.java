package com.notes.notesappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NoteControls extends NoteApp {

    @FXML
    private Button cancleButton;


    public void cancleButtonAction(ActionEvent e) {
        Stage stage = (Stage) cancleButton.getScene().getWindow();
        stage.close();
    }
}
