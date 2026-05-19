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
import org.example.mental_therapy_orm.bo.custom.TherapyProgramBO;
import org.example.mental_therapy_orm.dto.TherapyProgramDTO;

import java.util.List;

public class TherapyProgramController {

    @FXML private TextField txtProgramId;
    @FXML private TextField txtName;
    @FXML private TextField txtDescription;
    @FXML private TextField txtDuration;
    @FXML private TextField txtCost;

    @FXML private TableView<TherapyProgramDTO> tblPrograms;
    @FXML private TableColumn<TherapyProgramDTO, String> colId;
    @FXML private TableColumn<TherapyProgramDTO, String> colName;
    @FXML private TableColumn<TherapyProgramDTO, String> colDescription;
    @FXML private TableColumn<TherapyProgramDTO, String> colDuration;
    @FXML private TableColumn<TherapyProgramDTO, Double> colCost;

    private TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPYPROGRAM);

    public void initialize() {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colDescription != null) colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        if (colDuration != null) colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        if (colCost != null) colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        if (tblPrograms != null) {
            tblPrograms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtProgramId.setText(newValue.getId());
                    txtName.setText(newValue.getName());
                    txtDescription.setText(newValue.getDescription());
                    txtDuration.setText(newValue.getDuration());
                    if (newValue.getCost() != null) {
                        txtCost.setText(String.valueOf(newValue.getCost()));
                    }
                }
            });
            loadAllPrograms();
        }
    }

    private void loadAllPrograms() {
        try {
            List<TherapyProgramDTO> list = therapyProgramBO.getAll();
            ObservableList<TherapyProgramDTO> observableList = FXCollections.observableArrayList(list);
            tblPrograms.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void validateInputs() {
        if (txtProgramId.getText().isEmpty() || txtName.getText().isEmpty() || 
            txtDescription.getText().isEmpty() || txtDuration.getText().isEmpty() || txtCost.getText().isEmpty()) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("All fields must be filled!");
        }
        if (!txtCost.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("Invalid cost format! (Must be a number)");
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            validateInputs();
            boolean isSaved = therapyProgramBO.save(new TherapyProgramDTO(
                    txtProgramId.getText(),
                    txtName.getText(),
                    txtDescription.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtCost.getText())
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program Saved Successfully!").show();
                if (tblPrograms != null) loadAllPrograms();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            validateInputs();
            boolean isUpdated = therapyProgramBO.update(new TherapyProgramDTO(
                    txtProgramId.getText(),
                    txtName.getText(),
                    txtDescription.getText(),
                    txtDuration.getText(),
                    Double.parseDouble(txtCost.getText())
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program Updated Successfully!").show();
                if (tblPrograms != null) loadAllPrograms();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = therapyProgramBO.delete(txtProgramId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy Program Deleted Successfully!").show();
                if (tblPrograms != null) loadAllPrograms();
                clearFields();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        if (txtProgramId != null) txtProgramId.clear();
        if (txtName != null) txtName.clear();
        if (txtDescription != null) txtDescription.clear();
        if (txtDuration != null) txtDuration.clear();
        if (txtCost != null) txtCost.clear();
    }
}
