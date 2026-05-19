package org.example.mental_therapy_orm.bo.custom.impl;

import org.example.mental_therapy_orm.bo.custom.SessionScheduleBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.PatientDAO;
import org.example.mental_therapy_orm.dao.custom.SessionScheduleDAO;
import org.example.mental_therapy_orm.dao.custom.TherapistDAO;
import org.example.mental_therapy_orm.dao.custom.TherapyProgramDAO;
import org.example.mental_therapy_orm.dto.SessionScheduleDTO;
import org.example.mental_therapy_orm.entity.Patient;
import org.example.mental_therapy_orm.entity.SessionSchedule;
import org.example.mental_therapy_orm.entity.Therapist;
import org.example.mental_therapy_orm.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class SessionScheduleBOImpl implements SessionScheduleBO {
    private SessionScheduleDAO dao = (SessionScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SESSIONSCHEDULE);
    private TherapyProgramDAO programDAO = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPYPROGRAM);
    private TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    private PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean save(SessionScheduleDTO dto) throws Exception {
        TherapyProgram tp = programDAO.search(dto.getProgramId());
        Therapist th = therapistDAO.search(dto.getTherapistId());
        Patient p = null;
        if (dto.getPatientId() != null && !dto.getPatientId().isEmpty()) {
            p = patientDAO.search(dto.getPatientId());
        }
        return dao.save(new SessionSchedule(dto.getId(), tp, th, p, dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean update(SessionScheduleDTO dto) throws Exception {
        TherapyProgram tp = programDAO.search(dto.getProgramId());
        Therapist th = therapistDAO.search(dto.getTherapistId());
        Patient p = null;
        if (dto.getPatientId() != null && !dto.getPatientId().isEmpty()) {
            p = patientDAO.search(dto.getPatientId());
        }
        return dao.update(new SessionSchedule(dto.getId(), tp, th, p, dto.getDate(), dto.getTime()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public SessionScheduleDTO search(String id) throws Exception {
        SessionSchedule entity = dao.search(id);
        if (entity != null) {
            String progId = entity.getTherapyProgram() != null ? entity.getTherapyProgram().getId() : "";
            String thId = entity.getTherapist() != null ? entity.getTherapist().getId() : "";
            String pId = entity.getPatient() != null ? entity.getPatient().getId() : "";
            return new SessionScheduleDTO(entity.getId(), progId, thId, pId, entity.getDate(), entity.getTime());
        }
        return null;
    }

    @Override
    public List<SessionScheduleDTO> getAll() throws Exception {
        List<SessionSchedule> list = dao.getAll();
        List<SessionScheduleDTO> dtoList = new ArrayList<>();
        for (SessionSchedule entity : list) {
            String progId = entity.getTherapyProgram() != null ? entity.getTherapyProgram().getId() : "";
            String thId = entity.getTherapist() != null ? entity.getTherapist().getId() : "";
            String pId = entity.getPatient() != null ? entity.getPatient().getId() : "";
            dtoList.add(new SessionScheduleDTO(entity.getId(), progId, thId, pId, entity.getDate(), entity.getTime()));
        }
        return dtoList;
    }
}
