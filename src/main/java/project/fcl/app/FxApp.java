package project.fcl.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.fcl.model.Member;
import project.fcl.service.FileHandler;
import project.fcl.service.MembershipManagement;

import java.util.LinkedList;
import java.io.*;

public class FxApp extends Application {
    String mem;

    MembershipManagement membershipManagement = new MembershipManagement();
    FileHandler fileHandler = new FileHandler();
    LinkedList<Member> members = fileHandler.readFile();
    private MembershipManagement management;

    @Override
    public void start(Stage primaryStage) {
        management = new MembershipManagement();

        Label text = new Label(
                "WELCOME TO OZONE FITNESS CENTER\n\n"
        );
        text.setWrapText(true);

        // Кнопки
        Button btn1 = new Button("Add Member");
        Button btn2 = new Button("Remove Member");
        Button btn3 = new Button("Display Member Information");
        Button exitBtn = new Button("Exit");

        // Обработчики
        btn1.setOnAction(e -> openNewWindow(""));
        btn2.setOnAction(e -> openNewWindow(""));
        btn3.setOnAction(e -> displayInfoWindow());

        exitBtn.setOnAction(e -> Platform.exit());

        // Контейнер
        VBox root = new VBox(10, text, btn1, btn2, btn3, exitBtn);
        root.setStyle("-fx-padding: 20;");

        // Сцена и окно
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Fitness Club");
        primaryStage.show();
    }

    // Метод для создания новых окон
    private void openNewWindow(String title) {
        Stage stage = new Stage();

        Label label = new Label("Это " + title);
        VBox root = new VBox(label);
        root.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(root, 250, 150));
        stage.setTitle(title);
        stage.show();
    }

    private void displayInfoWindow() {
        Stage stage = new Stage();

        Label label = new Label("Enter visitor's ID");
        TextField textField = new TextField();
        Button btn = new Button("Search");
        Label result = new Label(" ");
        btn.setOnAction(event -> {
            try {
                int id = Integer.parseInt(textField.getText());
                result.setText(management.printMemberInfo(members, id));
            } catch (NumberFormatException e) {
                result.setText("Please enter a valid number");
            }
        });

        VBox root = new VBox(10, label, textField, btn, result);
        root.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Search member");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}