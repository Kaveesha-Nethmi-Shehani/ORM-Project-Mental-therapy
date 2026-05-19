package org.example.mental_therapy_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mental_therapy_orm.bo.BOFactory;
import org.example.mental_therapy_orm.bo.custom.PatientBO;
import org.example.mental_therapy_orm.dto.PatientDTO;

import java.util.List;

public class PatientController {

    @FXML private TextField txtPatientId;
    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private TextArea txtAddress;
    @FXML private TextArea txtMedicalHistory;

    @FXML private TableView<PatientDTO> tblPatients;
    @FXML private TableColumn<PatientDTO, String> colId;
    @FXML private TableColumn<PatientDTO, String> colName;
    @FXML private TableColumn<PatientDTO, String> colPhone;
    @FXML private TableColumn<PatientDTO, String> colEmail;

    private PatientBO bo = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);

    public void initialize() {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colPhone != null) colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        if (tblPatients != null) {
            tblPatients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtPatientId.setText(newValue.getId());
                    txtName.setText(newValue.getName());
                    txtEmail.setText(newValue.getEmail());
                    txtPhone.setText(newValue.getPhone());
                    txtAddress.setText(newValue.getAddress());
                    txtMedicalHistory.setText(newValue.getMedicalHistory());
                }
            });
            loadAllPatients();
        }
    }

    private void loadAllPatients() {
        try {
            List<PatientDTO> list = bo.getAll();
            ObservableList<PatientDTO> observableList = FXCollections.observableArrayList(list);
            tblPatients.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void validateInputs() {
        if (txtPatientId.getText().isEmpty() || txtName.getText().isEmpty() || txtEmail.getText().isEmpty() ||
            txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtMedicalHistory.getText().isEmpty()) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("All fields must be filled!");
        }
        if (!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("Invalid email format!");
        }
        if (!txtPhone.getText().matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$")) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("Invalid phone number format! (Must be 10 digits)");
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        try {
            validateInputs();
            boolean isSaved = bo.save(new PatientDTO(
                    txtPatientId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtMedicalHistory.getText()
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Saved Successfully!").show();
                if (tblPatients != null) loadAllPatients();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent event) {
        try {
            validateInputs();
            boolean isUpdated = bo.update(new PatientDTO(
                    txtPatientId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAddress.getText(),
                    txtMedicalHistory.getText()
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Updated Successfully!").show();
                if (tblPatients != null) loadAllPatients();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bo.delete(txtPatientId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Deleted Successfully!").show();
                if (tblPatients != null) loadAllPatients();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnResetOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        if (txtPatientId != null) txtPatientId.clear();
        if (txtName != null) txtName.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtPhone != null) txtPhone.clear();
        if (txtAddress != null) txtAddress.clear();
        if (txtMedicalHistory != null) txtMedicalHistory.clear();
    }
}
