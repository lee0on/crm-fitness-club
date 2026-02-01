package project.fcl.ui.add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import project.fcl.model.Member;
import project.fcl.model.MultiClubMember;
import project.fcl.model.SingleClubMember;
import project.fcl.service.FileHandler;
import project.fcl.service.MembershipManagement;

import java.io.IOException;
import java.util.LinkedList;

public class AddMemberController {

    private final FileHandler fileHandler = new FileHandler();
    private final LinkedList<Member> members = fileHandler.readFile();
    private final MembershipManagement membershipManagement = new MembershipManagement();

    @FXML
    private TextField nameTextField;

    @FXML
    private RadioButton mercuryRadio;

    @FXML
    private RadioButton neptuneRadio;

    @FXML
    private RadioButton jupiterRadio;

    @FXML
    private RadioButton multiClubRadio;

    @FXML
    private ToggleGroup clubGroup;

    @FXML
    private Label resultLabel;

    @FXML
    void onAddMember(ActionEvent event) {
        // Validate name input
        String name = nameTextField.getText().trim();
        if (name.isEmpty()) {
            resultLabel.setText("ERROR: Please enter a name");
            return;
        }

        // Validate club selection
        RadioButton selectedRadio = (RadioButton) clubGroup.getSelectedToggle();
        if (selectedRadio == null) {
            resultLabel.setText("ERROR: Please select a club");
            return;
        }

        try {
            // Determine club ID based on selected radio button
            int club;
            if (selectedRadio == mercuryRadio) {
                club = 1;
            } else if (selectedRadio == neptuneRadio) {
                club = 2;
            } else if (selectedRadio == jupiterRadio) {
                club = 3;
            } else {
                club = 4; // Multi Clubs
            }

            // Use existing service layer logic to create member
            Member newMember = membershipManagement.createMember(name, club, members);

            // Save to file using existing FileHandler
            String memberCSV = newMember.toCSV();
            fileHandler.appendFile(memberCSV);

            // Display result
            if (newMember instanceof SingleClubMember) {
                SingleClubMember singleMember = (SingleClubMember) newMember;
                resultLabel.setText("SUCCESS: Single Club Member added\n" +
                        "Member ID: " + singleMember.getMemberId() + "\n" +
                        "Name: " + singleMember.getName() + "\n" +
                        "Club: " + getClubName(singleMember.getClub()) + "\n" +
                        "Fee: $" + singleMember.getFee());
            } else if (newMember instanceof MultiClubMember) {
                MultiClubMember multiMember = (MultiClubMember) newMember;
                resultLabel.setText("SUCCESS: Multi Club Member added\n" +
                        "Member ID: " + multiMember.getMemberId() + "\n" +
                        "Name: " + multiMember.getName() + "\n" +
                        "All Clubs Access\n" +
                        "Fee: $" + multiMember.getFee() + "\n" +
                        "Membership Points: " + multiMember.getMembershipPoints());
            }

            // Clear form
            nameTextField.clear();
            clubGroup.selectToggle(null);

        } catch (IOException e) {
            resultLabel.setText("ERROR: Failed to save member to file\n" + e.getMessage());
        }
    }

    private String getClubName(int clubId) {
        return switch (clubId) {
            case 1 -> "Mercury";
            case 2 -> "Neptune";
            case 3 -> "Jupiter";
            default -> "Unknown";
        };
    }
}
