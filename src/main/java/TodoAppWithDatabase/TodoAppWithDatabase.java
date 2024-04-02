package TodoAppWithDatabase;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.sql.*;


public class TodoAppWithDatabase extends Application {
    private ListView<String> taskListView;
    private TextField taskInput;

    private Label HeadLabel;

    DatePicker datePicker = new DatePicker(); //adding date picker

    @Override
    public void start(Stage primaryStage) {

        HeadLabel = new Label("ALL TASKS");

        // Create navigation buttons
        Button todayButton = new Button("Today");
        Button allButton = new Button("All");
        Button aboutMeButton = new Button("aboutMe");
        Button toggleButton = new Button("Toggle Sidebar");

        //header
        HBox header = new HBox(10,HeadLabel, toggleButton);
        header.setPadding(new Insets(10));
        header.setAlignment(Pos.CENTER_RIGHT);

        // Create sidebar layout
        VBox sidebar = new VBox(10, todayButton, allButton, aboutMeButton);
        sidebar.setPadding(new Insets(10));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #909090;");

        //about me
        String path = "file:///C:/Users/chusa/IdeaProjects/app/src/profile.png";
        ImageView profilePic = new ImageView(new Image(path)); //profile image
        profilePic.setFitWidth(100);
        profilePic.setFitHeight(100);

        Text nameLabel = new Text("John Doe"); //profile lable
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Hyperlink githubLink = new Hyperlink("GITHUB"); //social
        Hyperlink instagramLink = new Hyperlink("Instagram");

        VBox profileInfo = new VBox(10);
        profileInfo.setAlignment(Pos.CENTER);
        profileInfo.getChildren().addAll(nameLabel, githubLink, instagramLink);

        HBox profileSection = new HBox(20);
        profileSection.setAlignment(Pos.CENTER);
        profileSection.setPadding(new Insets(20));
        profileSection.getChildren().addAll(profilePic, profileInfo);
        //end of about me

        // task view list
        taskListView = new ListView<>();
        taskInput = new TextField();

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask());

        Button cButton = new Button("c task");
        cButton.setOnAction(e -> addTask());

        HBox inputBox = new HBox(15, taskInput, addButton, cButton, datePicker);
        inputBox.setPadding(new Insets(10));

        //view 1
        BorderPane mainView = new BorderPane();
        mainView.setTop(header);
        mainView.setCenter(taskListView);
        mainView.setBottom(inputBox);

        //main layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(mainView);

        //nav actions
        toggleButton.setOnAction(e -> {
            sidebar.setVisible(!sidebar.isVisible());
            if (!sidebar.isVisible()) {
                // If sidebar is hidden, set main view to occupy the entire center area
                root.setLeft(null);
            } else {
                // If sidebar is visible, reset margins of main content
                root.setLeft(sidebar);
            }
        });

        allButton.setOnAction(e -> {
            mainView.setCenter(taskListView);
            HeadLabel.setText("ALL TASKS");
        });

        aboutMeButton.setOnAction(e -> {
            mainView.setCenter(profileSection);
            HeadLabel.setText("ABOUT ME");
        });
        //end of nav actions

        //link actions
        githubLink.setOnAction(e -> {
            String url = "https://www.github.com/dullat";
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
            } catch (java.io.IOException | java.net.URISyntaxException ex) {
                ex.printStackTrace();
            }
        });

        instagramLink.setOnAction(e -> {
            String url = "https://www.instagram.com/dullat101";
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
            } catch (java.io.IOException | java.net.URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        //end of link actions

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Empty Views Example");
        primaryStage.show();
    }

    private void addTask() {
        String description = taskInput.getText();
        LocalDate selectedDate = datePicker.getValue();

        if (!description.isEmpty()) {
            String task = description + " - " + selectedDate.toString();
            taskListView.getItems().add(task);
            taskInput.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

