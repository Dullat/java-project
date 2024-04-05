package com.notes.notesappfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class TaskListCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            String[] parts = item.split(" - ");
            String description = parts[0];
            String date = parts[1];

            // Create custom layout
            Label descriptionLabel = new Label(description);
            Label dateLabel = new Label(date);

            // Align labels
            descriptionLabel.setAlignment(Pos.CENTER_LEFT);
            dateLabel.setAlignment(Pos.CENTER_RIGHT);

            // Set padding
            descriptionLabel.setPadding(new Insets(0, 10, 0, 0));
            dateLabel.setPadding(new Insets(0, 0, 0, 10));

            descriptionLabel.setStyle("-fx-font-weight: bold;");

            // Add nodes to layout
            GridPane gridPane = new GridPane();
            gridPane.addRow(0, descriptionLabel, dateLabel);
            GridPane.setHgrow(descriptionLabel, Priority.ALWAYS); // Expand description to fill space
            GridPane.setHgrow(dateLabel, Priority.NEVER); // Keep date label fixed size

            setGraphic(gridPane);
        }
    }
}

