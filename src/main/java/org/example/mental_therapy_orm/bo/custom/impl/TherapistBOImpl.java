package org.example.mental_therapy_orm.bo.custom.impl;
import org.example.mental_therapy_orm.bo.custom.TherapistBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.TherapistDAO;
import org.example.mental_therapy_orm.dto.TherapistDTO;
import org.example.mental_therapy_orm.entity.Therapist;
import java.util.ArrayList;
import java.util.List;

public class TherapistBOImpl implements TherapistBO {
    private TherapistDAO dao = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    @Override public boolean save(TherapistDTO dto) throws Exception {
        return dao.save(new Therapist(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getSpecialization(), dto.getAvailability(), null));
    }
    @Override public boolean update(TherapistDTO dto) throws Exception {
        return dao.update(new Therapist(dto.getId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getSpecialization(), dto.getAvailability(), null));
    }
    @Override public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }
    @Override public TherapistDTO search(String id) throws Exception {
        Therapist entity = dao.search(id);
        if(entity != null) return new TherapistDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getSpecialization(), entity.getAvailability());
        return null;
    }
    @Override public List<TherapistDTO> getAll() throws Exception {
        List<Therapist> list = dao.getAll();
        List<TherapistDTO> dtoList = new ArrayList<>();
        for (Therapist entity : list) dtoList.add(new TherapistDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getPhone(), entity.getSpecialization(), entity.getAvailability()));
        return dtoList;
    }
}
