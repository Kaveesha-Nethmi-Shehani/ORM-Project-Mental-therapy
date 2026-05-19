package org.example.mental_therapy_orm.bo.custom.impl;

import org.example.mental_therapy_orm.bo.custom.PaymentBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.PatientDAO;
import org.example.mental_therapy_orm.dao.custom.PaymentDAO;
import org.example.mental_therapy_orm.dto.PaymentDTO;
import org.example.mental_therapy_orm.entity.Patient;
import org.example.mental_therapy_orm.entity.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    private PaymentDAO dao = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean save(PaymentDTO dto) throws Exception {
        Patient patient = null;
        if (dto.getPatientId() != null && !dto.getPatientId().isEmpty()) {
            patient = patientDAO.search(dto.getPatientId());
            if (patient == null) throw new RuntimeException("Patient ID not found!");
        }
        return dao.save(new Payment(dto.getId(), patient, dto.getAmount(), dto.getMethod(), dto.getDate()));
    }

    @Override
    public boolean update(PaymentDTO dto) throws Exception {
        Patient patient = null;
        if (dto.getPatientId() != null && !dto.getPatientId().isEmpty()) {
            patient = patientDAO.search(dto.getPatientId());
            if (patient == null) throw new RuntimeException("Patient ID not found!");
        }
        return dao.update(new Payment(dto.getId(), patient, dto.getAmount(), dto.getMethod(), dto.getDate()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public PaymentDTO search(String id) throws Exception {
        Payment entity = dao.search(id);
        if (entity != null) {
            String patientId = entity.getPatient() != null ? entity.getPatient().getId() : "";
            return new PaymentDTO(entity.getId(), patientId, "", entity.getAmount(), entity.getMethod(), entity.getDate());
        }
        return null;
    }

    @Override
    public List<PaymentDTO> getAll() throws Exception {
        List<Payment> list = dao.getAll();
        List<PaymentDTO> dtoList = new ArrayList<>();
        for (Payment entity : list) {
            String patientId = entity.getPatient() != null ? entity.getPatient().getId() : "";
            dtoList.add(new PaymentDTO(entity.getId(), patientId, "", entity.getAmount(), entity.getMethod(), entity.getDate()));
        }
        return dtoList;
    }
}
