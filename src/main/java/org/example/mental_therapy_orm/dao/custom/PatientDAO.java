package org.example.mental_therapy_orm.dao.custom;

import org.example.mental_therapy_orm.dao.CrudDAO;
import org.example.mental_therapy_orm.entity.Patient;
import java.util.List;

public interface PatientDAO extends CrudDAO<Patient> {
    List<Patient> getPatientsWithEnrolledPrograms() throws Exception;
    List<Patient> getPatientsEnrolledInAllPrograms() throws Exception;
}
