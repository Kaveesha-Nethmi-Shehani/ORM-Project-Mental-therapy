package org.example.mental_therapy_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mental_therapy_orm.bo.BOFactory;
import org.example.mental_therapy_orm.bo.custom.TherapistBO;
import org.example.mental_therapy_orm.dto.TherapistDTO;

import java.util.List;

public class TherapistsController {

    @FXML private TextField txtTherapistId;
    @FXML private TextField txtName;
    @FXML private TextField txtSpecialization;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    @FXML private TableView<TherapistDTO> tblTherapists;
    @FXML private TableColumn<TherapistDTO, String> colId;
    @FXML private TableColumn<TherapistDTO, String> colName;
    @FXML private TableColumn<TherapistDTO, String> colSpecialization;
    @FXML private TableColumn<TherapistDTO, String> colPhone;

    private TherapistBO bo = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);

    public void initialize() {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colSpecialization != null) colSpecialization.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        if (colPhone != null) colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        if (tblTherapists != null) {
            tblTherapists.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtTherapistId.setText(newValue.getId());
                    txtName.setText(newValue.getName());
                    txtEmail.setText(newValue.getEmail());
                    txtPhone.setText(newValue.getPhone());
                    txtSpecialization.setText(newValue.getSpecialization());
                }
            });
            loadAllTherapists();
        }
    }

    private void loadAllTherapists() {
        try {
            List<TherapistDTO> list = bo.getAll();
            ObservableList<TherapistDTO> observableList = FXCollections.observableArrayList(list);
            tblTherapists.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void validateInputs() {
        if (txtTherapistId.getText().isEmpty() || txtName.getText().isEmpty() || txtEmail.getText().isEmpty() ||
            txtPhone.getText().isEmpty() || txtSpecialization.getText().isEmpty()) {
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
            boolean isSaved = bo.save(new TherapistDTO(
                    txtTherapistId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtSpecialization.getText(),
                    "" // availability
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Saved Successfully!").show();
                if (tblTherapists != null) loadAllTherapists();
                btnClearOnAction(null);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent event) {
        try {
            validateInputs();
            boolean isUpdated = bo.update(new TherapistDTO(
                    txtTherapistId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtSpecialization.getText(),
                    "" // availability
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Updated Successfully!").show();
                if (tblTherapists != null) loadAllTherapists();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = bo.delete(txtTherapistId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist Deleted Successfully!").show();
                if (tblTherapists != null) loadAllTherapists();
                btnClearOnAction(null);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
        if (txtTherapistId != null) txtTherapistId.clear();
        if (txtName != null) txtName.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtPhone != null) txtPhone.clear();
        if (txtSpecialization != null) txtSpecialization.clear();
    }
}
