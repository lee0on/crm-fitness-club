package project.fcl.ui.main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import project.fcl.model.Member;
import project.fcl.service.FileHandler;
import project.fcl.service.MembershipManagement;

import java.io.IOException;
import java.util.LinkedList;

public class MainController {

    private final MembershipManagement management = new MembershipManagement();
    private final FileHandler fileHandler = new FileHandler();
    private final LinkedList<Member> members = fileHandler.readFile();

    @FXML
    private void onAddMember() {
        openNewWindow("Add Member");
    }

    @FXML
    private void onRemoveMember() {
        openNewWindow("Remove Member");
    }

    @FXML
    private void onDisplayInfo() {
        openSearchWindow();
    }

    private void openSearchWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/ui/search/search-member.fxml")
            );

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Search member");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExit() {
        Platform.exit();
    }

    private void openNewWindow(String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.show();
    }
}