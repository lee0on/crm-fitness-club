package project.fcl.ui.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import project.fcl.model.Member;
import project.fcl.service.FileHandler;
import project.fcl.service.MembershipManagement;
import java.util.LinkedList;


public class SearchMemberController {

    private final MembershipManagement management = new MembershipManagement();
    private final FileHandler fileHandler = new FileHandler();
    private final LinkedList<Member> members = fileHandler.readFile();

    @FXML
    private Label res;

    @FXML
    private TextField textField;

    @FXML
    void onDisplayInfo(ActionEvent event) {
            try {
                int id = Integer.parseInt(textField.getText());
                res.setText(management.printMemberInfo(members, id));
            } catch (NumberFormatException e) {
                res.setText("Please enter a valid number");
            }
    }
}
