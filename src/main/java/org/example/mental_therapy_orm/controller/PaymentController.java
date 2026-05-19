package org.example.mental_therapy_orm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.mental_therapy_orm.bo.BOFactory;
import org.example.mental_therapy_orm.bo.custom.PatientBO;
import org.example.mental_therapy_orm.bo.custom.PaymentBO;
import org.example.mental_therapy_orm.dto.PatientDTO;
import org.example.mental_therapy_orm.dto.PaymentDTO;
import org.example.mental_therapy_orm.dto.SessionScheduleDTO;
import org.example.mental_therapy_orm.dto.TherapyProgramDTO;
import org.example.mental_therapy_orm.bo.custom.SessionScheduleBO;
import org.example.mental_therapy_orm.bo.custom.TherapyProgramBO;

import java.time.LocalDate;
import java.util.List;

public class PaymentController {

    @FXML private TextField txtPaymentId;
    @FXML private ComboBox<String> cmbPatientId;
    @FXML private Label lblPatientDetails;
    @FXML private TextField txtAmount;
    @FXML private DatePicker datePicker;

    @FXML private TableView<PaymentDTO> tblPayments;
    @FXML private TableColumn<PaymentDTO, String> colId;
    @FXML private TableColumn<PaymentDTO, String> colPatientId;
    @FXML private TableColumn<PaymentDTO, Double> colAmount;
    @FXML private TableColumn<PaymentDTO, String> colDate;

    private PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    private PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);
    private SessionScheduleBO sessionScheduleBO = (SessionScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SESSIONSCHEDULE);
    private TherapyProgramBO therapyProgramBO = (TherapyProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPYPROGRAM);

    public void initialize() {
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colPatientId != null) colPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        if (colAmount != null) colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        if (colDate != null) colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        if (tblPayments != null) {
            tblPayments.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    txtPaymentId.setText(newValue.getId());
                    cmbPatientId.setValue(newValue.getPatientId());
                    txtAmount.setText(String.valueOf(newValue.getAmount()));
                    if (newValue.getDate() != null && !newValue.getDate().isEmpty()) {
                        try {
                            datePicker.setValue(LocalDate.parse(newValue.getDate()));
                        } catch(Exception ignored) {}
                    }
                }
            });
            loadAllPayments();
        }
        
        loadComboBoxes();
        
        if (cmbPatientId != null) {
            cmbPatientId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        for(PatientDTO p : patientBO.getAll()){
                            if(p.getId().equals(newValue)){
                                lblPatientDetails.setText("Name: " + p.getName() + " | Phone: " + p.getPhone());
                                
                                // Auto-calculate total therapy program amount for this patient
                                double totalCost = 0.0;
                                List<SessionScheduleDTO> allSessions = sessionScheduleBO.getAll();
                                for (SessionScheduleDTO session : allSessions) {
                                    if (session.getPatientId() != null && session.getPatientId().equals(p.getId())) {
                                        TherapyProgramDTO prog = therapyProgramBO.search(session.getProgramId());
                                        if (prog != null && prog.getCost() != null) {
                                            totalCost += prog.getCost();
                                        }
                                    }
                                }
                                if (totalCost > 0) {
                                    txtAmount.setText(String.valueOf(totalCost));
                                } else {
                                    txtAmount.clear();
                                }
                                
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
                for (PatientDTO p : patientBO.getAll()) {
                    patientIds.add(p.getId());
                }
                cmbPatientId.setItems(patientIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllPayments() {
        try {
            List<PaymentDTO> list = paymentBO.getAll();
            ObservableList<PaymentDTO> observableList = FXCollections.observableArrayList(list);
            tblPayments.setItems(observableList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void validateInputs() {
        if (txtPaymentId.getText().isEmpty() || cmbPatientId.getValue() == null || 
            txtAmount.getText().isEmpty() || datePicker.getValue() == null) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("All fields must be filled!");
        }
        if (!txtAmount.getText().matches("^\\d+(\\.\\d{1,2})?$")) {
            throw new org.example.mental_therapy_orm.exception.ValidationException("Invalid amount format! (Must be a number)");
        }
    }

    @FXML
    public void btnSaveOnAction(ActionEvent event) {
        try {
            validateInputs();
            boolean isSaved = paymentBO.save(new PaymentDTO(
                    txtPaymentId.getText(),
                    cmbPatientId.getValue(),
                    "", // patientName
                    Double.parseDouble(txtAmount.getText()),
                    "Card", // method
                    datePicker.getValue() != null ? datePicker.getValue().toString() : ""
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Saved Successfully!").show();
                if (tblPayments != null) loadAllPayments();
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
            boolean isUpdated = paymentBO.update(new PaymentDTO(
                    txtPaymentId.getText(),
                    cmbPatientId.getValue(),
                    "", // patientName
                    Double.parseDouble(txtAmount.getText()),
                    "Card", // method
                    datePicker.getValue() != null ? datePicker.getValue().toString() : ""
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Updated Successfully!").show();
                if (tblPayments != null) loadAllPayments();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean isDeleted = paymentBO.delete(txtPaymentId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Payment Deleted Successfully!").show();
                if (tblPayments != null) loadAllPayments();
                btnClearOnAction(null);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete: " + e.getMessage()).show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
        if (txtPaymentId != null) txtPaymentId.clear();
        if (cmbPatientId != null) cmbPatientId.setValue(null);
        if (lblPatientDetails != null) lblPatientDetails.setText("...");
        if (txtAmount != null) txtAmount.clear();
        if (datePicker != null) datePicker.setValue(null);
    }
}
