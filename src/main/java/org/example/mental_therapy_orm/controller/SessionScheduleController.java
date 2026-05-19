package org.example.mental_therapy_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mental_therapy_orm.bo.BOFactory;
import org.example.mental_therapy_orm.bo.custom.PatientBO;
import org.example.mental_therapy_orm.bo.custom.SessionScheduleBO;
import org.example.mental_therapy_orm.bo.custom.TherapistBO;
import org.example.mental_therapy_orm.bo.custom.TherapyProgramBO;
import org.example.mental_therapy_orm.dto.PatientDTO;
import org.example.mental_therapy_orm.dto.SessionScheduleDTO;
import org.example.mental_therapy_orm.dto.TherapistDTO;
import org.example.mental_therapy_orm.dto.TherapyProgramDTO;
import org.example.mental_therapy_orm.exception.ValidationException;

import java.time.LocalDate;
import java.util.List;

public class SessionScheduleController {

    @FXML private TextField txtScheduleId;
    
    @FXML private ComboBox<String> cmbPatientId;
    @FXML private Label lblPatientDetails;

    @FXML private ComboBox<String> cmbProgramId;
    @FXML private Label lblProgramDetails;
    
    @FXML private ComboBox<String> cmbTherapistId;
    @FXML private Label lblTherapistDetails;
    
    @FXML private DatePicker datePicker;
    @FXML private TextField txtTime;

    @FXML private TableView<SessionScheduleDTO> tblSchedules;
    @FXML private TableColumn<SessionScheduleDTO, String> colId;
    @FXML private TableColumn<SessionScheduleDTO, String> colPatientId;
    @FXML private TableColumn<SessionScheduleDTO, String> colProgramId;
    @FXML private TableColumn<SessionScheduleDTO, String> colTherapistId;
    @FXML private TableColumn<SessionScheduleDTO, String> colDate;
    @FXML private TableColumn<SessionScheduleDTO, String> colTime;

    private SessionScheduleBO sessionScheduleBO = (SessionScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SESSIONSCHEDULE);
    private TherapyProgramBO programBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPYPROGRAM);
    private TherapistBO therapistBO = (TherapistBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);
    private PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);

    public void initialize() {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colPatientId != null) colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        if (colProgramId != null) colProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        if (colTherapistId != null) colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        if (colDate != null) colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        if (colTime != null) colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        if (tblSchedules != null) {
            tblSchedules.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtScheduleId.setText(newValue.getId());
                    cmbPatientId.setValue(newValue.getPatientId());
                    cmbProgramId.setValue(newValue.getProgramId());
                    cmbTherapistId.setValue(newValue.getTherapistId());
                    if(newValue.getDate() != null && !newValue.getDate().isEmpty()) {
                        try {
                            datePicker.setValue(LocalDate.parse(newValue.getDate()));
                        }catch (Exception e){}
                    }
                    txtTime.setText(newValue.getTime());
                }
            });
            loadAllSchedules();
        }
        
        loadComboBoxes();
        
        if (cmbPatientId != null) {
            cmbPatientId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        for(PatientDTO p : patientBO.getAll()){
                            if(p.getId().equals(newValue)){
                                lblPatientDetails.setText("Name: " + p.getName());
                                break;
                            }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }
            });
        }
        
        if (cmbProgramId != null) {
            cmbProgramId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        for(TherapyProgramDTO p : programBO.getAll()){
                            if(p.getId().equals(newValue)){
                                lblProgramDetails.setText("Name: " + p.getName() + " | Duration: " + p.getDuration() + " | Cost: " + p.getCost());
                                break;
                            }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }
            });
        }
        
        if (cmbTherapistId != null) {
            cmbTherapistId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        for(TherapistDTO t : therapistBO.getAll()){
                            if(t.getId().equals(newValue)){
                                lblTherapistDetails.setText("Name: " + t.getName() + " | Specialization: " + t.getSpecialization());
                                break;
                            }
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                }
            });
        }
    }

    private void loadComboBoxes() {
        try {
            if (cmbPatientId != null) {
                ObservableList<String> patientIds = FXCollections.observableArrayList();
                for (PatientDTO p : patientBO.getAll()) patientIds.add(p.getId());
                cmbPatientId.setItems(patientIds);
            }
            if (cmbProgramId != null) {
                ObservableList<String> programIds = FXCollections.observableArrayList();
                for (TherapyProgramDTO p : programBO.getAll()) programIds.add(p.getId());
                cmbProgramId.setItems(programIds);
            }
            if (cmbTherapistId != null) {
                ObservableList<String> therapistIds = FXCollections.observableArrayList();
                for (TherapistDTO t : therapistBO.getAll()) therapistIds.add(t.getId());
                cmbTherapistId.setItems(therapistIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllSchedules() {
        try {
            List<SessionScheduleDTO> list = sessionScheduleBO.getAll();
            ObservableList<SessionScheduleDTO> observableList = FXCollections.observableArrayList(list);
            tblSchedules.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void validateInputs() {
        if (txtScheduleId.getText().isEmpty() || cmbPatientId.getValue() == null ||
            cmbProgramId.getValue() == null || cmbTherapistId.getValue() == null || datePicker.getValue() == null) {
            throw new ValidationException("All fields must be filled!");
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            validateInputs();
            boolean isSaved = sessionScheduleBO.save(new SessionScheduleDTO(
                    txtScheduleId.getText(),
                    cmbProgramId.getValue(),
                    cmbTherapistId.getValue(),
                    cmbPatientId.getValue(),
                    datePicker.getValue().toString(),
                    txtTime.getText()
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Session Schedule Saved Successfully!").show();
                if (tblSchedules != null) loadAllSchedules();
                btnClearOnAction(null);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            validateInputs();
            boolean isUpdated = sessionScheduleBO.update(new SessionScheduleDTO(
                    txtScheduleId.getText(),
                    cmbProgramId.getValue(),
                    cmbTherapistId.getValue(),
                    cmbPatientId.getValue(),
                    datePicker.getValue().toString(),
                    txtTime.getText()
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Session Schedule Updated Successfully!").show();
                if (tblSchedules != null) loadAllSchedules();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = sessionScheduleBO.delete(txtScheduleId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Session Schedule Deleted Successfully!").show();
                if (tblSchedules != null) loadAllSchedules();
                btnClearOnAction(null);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
        if (txtScheduleId != null) txtScheduleId.clear();
        if (cmbPatientId != null) cmbPatientId.setValue(null);
        if (cmbProgramId != null) cmbProgramId.setValue(null);
        if (cmbTherapistId != null) cmbTherapistId.setValue(null);
        if (lblPatientDetails != null) lblPatientDetails.setText("...");
        if (lblProgramDetails != null) lblProgramDetails.setText("...");
        if (lblTherapistDetails != null) lblTherapistDetails.setText("...");
        if (datePicker != null) datePicker.setValue(null);
        if (txtTime != null) txtTime.clear();
    }
}
