package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.PatientDTO;
import java.util.List;
public interface PatientBO extends SuperBO {
    boolean save(PatientDTO dto) throws Exception;
    boolean update(PatientDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    PatientDTO search(String id) throws Exception;
    List<PatientDTO> getAll() throws Exception;
    List<PatientDTO> getPatientsWithEnrolledPrograms() throws Exception;
    List<PatientDTO> getPatientsEnrolledInAllPrograms() throws Exception;
}
