package org.example.mental_therapy_orm.bo.custom.impl;
import org.example.mental_therapy_orm.bo.custom.PatientBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.PatientDAO;
import org.example.mental_therapy_orm.dto.PatientDTO;
import org.example.mental_therapy_orm.entity.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientBOImpl implements PatientBO {
    private PatientDAO dao = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);
    @Override public boolean save(PatientDTO dto) throws Exception {
        return dao.save(new Patient(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAddress(), dto.getMedicalHistory(), null, null));
    }
    @Override public boolean update(PatientDTO dto) throws Exception {
        return dao.update(new Patient(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAddress(), dto.getMedicalHistory(), null, null));
    }
    @Override public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }
    @Override public PatientDTO search(String id) throws Exception {
        Patient entity = dao.search(id);
        if(entity != null) return new PatientDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getMedicalHistory());
        return null;
    }
    @Override public List<PatientDTO> getAll() throws Exception {
        List<Patient> list = dao.getAll();
        List<PatientDTO> dtoList = new ArrayList<>();
        for (Patient entity : list) dtoList.add(new PatientDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getMedicalHistory()));
        return dtoList;
    }

    @Override
    public List<PatientDTO> getPatientsWithEnrolledPrograms() throws Exception {
        List<Patient> list = dao.getPatientsWithEnrolledPrograms();
        List<PatientDTO> dtoList = new ArrayList<>();
        for (Patient entity : list) dtoList.add(new PatientDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getMedicalHistory()));
        return dtoList;
    }

    @Override
    public List<PatientDTO> getPatientsEnrolledInAllPrograms() throws Exception {
        List<Patient> list = dao.getPatientsEnrolledInAllPrograms();
        List<PatientDTO> dtoList = new ArrayList<>();
        for (Patient entity : list) dtoList.add(new PatientDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getAddress(), entity.getMedicalHistory()));
        return dtoList;
    }
}
