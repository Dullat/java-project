package com.notes.notesappfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import java.net.URI;

public class AboutMeController {

    @FXML
    public void mouseClickedInsta(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://instagram.com/dullat101"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void mouseClickedGithub(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/dullat"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void visitOtherProject(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/dullat/java-project-ASCIIart"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void visitSourceCode(ActionEvent e) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/dullat/java-project-NotesApp"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}