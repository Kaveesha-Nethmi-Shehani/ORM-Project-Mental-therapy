package org.example.mental_therapy_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.example.mental_therapy_orm.App;
import javafx.scene.control.Button;

import java.io.IOException;

public class DashBoardController {

    @FXML private AnchorPane rootNode;
    
    @FXML private Button btnPatients;
    @FXML private Button btnTherapists;
    @FXML private Button btnPrograms;
    @FXML private Button btnSessions;
    @FXML private Button btnPayments;

    public void initialize() {
        // Load the patients view by default on startup
        loadView("Patients");
        highlightButton(btnPatients);
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFile + ".fxml"));
            Parent root = loader.load();
            rootNode.getChildren().clear();
            rootNode.getChildren().add(root);
            
            // Anchor it to fill the pane
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);
        } catch (IOException e) {
            System.err.println("Error loading " + fxmlFile + ".fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void highlightButton(Button activeButton) {
        // Reset all buttons to transparent
        Button[] buttons = {btnPatients, btnTherapists, btnPrograms, btnSessions, btnPayments};
        for (Button btn : buttons) {
            if (btn != null) {
                btn.setStyle("-fx-background-radius: 8; -fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: center-left; -fx-padding: 0 0 0 20;");
            }
        }
        // Highlight active
        if (activeButton != null) {
            activeButton.setStyle("-fx-background-radius: 8; -fx-background-color: #6C63FF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-alignment: center-left; -fx-padding: 0 0 0 20;");
        }
    }

    @FXML
    public void btnPatientsOnAction(ActionEvent actionEvent) {
        loadView("Patients");
        highlightButton(btnPatients);
    }

    @FXML
    public void btnTherapistsOnAction(ActionEvent actionEvent) {
        loadView("Therapists");
        highlightButton(btnTherapists);
    }

    @FXML
    public void btnProgramsOnAction(ActionEvent actionEvent) {
        loadView("TherapyProgram");
        highlightButton(btnPrograms);
    }

    @FXML
    public void btnSessionsOnAction(ActionEvent actionEvent) {
        loadView("SessionSchedule");
        highlightButton(btnSessions);
    }

    @FXML
    public void btnPaymentsOnAction(ActionEvent actionEvent) {
        loadView("Payment");
        highlightButton(btnPayments);
    }

    @FXML
    public void btnLogoutOnAction(ActionEvent actionEvent) {
        try {
            App.setRoot("LoginForm");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
