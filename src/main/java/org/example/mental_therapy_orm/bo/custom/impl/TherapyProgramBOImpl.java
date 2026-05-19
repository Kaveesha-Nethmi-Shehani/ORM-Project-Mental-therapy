package org.example.mental_therapy_orm.bo.custom.impl;

import org.example.mental_therapy_orm.bo.custom.TherapyProgramBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.TherapyProgramDAO;
import org.example.mental_therapy_orm.dto.TherapyProgramDTO;
import org.example.mental_therapy_orm.entity.TherapyProgram;

import java.util.ArrayList;
import java.util.List;

public class TherapyProgramBOImpl implements TherapyProgramBO {
    private TherapyProgramDAO dao = (TherapyProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPYPROGRAM);
    
    @Override 
    public boolean save(TherapyProgramDTO dto) throws Exception {
        return dao.save(new TherapyProgram(dto.getId(), dto.getName(), dto.getDescription(), dto.getDuration(), dto.getCost(), null));
    }
    
    @Override 
    public boolean update(TherapyProgramDTO dto) throws Exception {
        return dao.update(new TherapyProgram(dto.getId(), dto.getName(), dto.getDescription(), dto.getDuration(), dto.getCost(), null));
    }
    
    @Override 
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }
    
    @Override 
    public TherapyProgramDTO search(String id) throws Exception {
        TherapyProgram entity = dao.search(id);
        if(entity != null) return new TherapyProgramDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getDuration(), entity.getCost());
        return null;
    }
    
    @Override 
    public List<TherapyProgramDTO> getAll() throws Exception {
        List<TherapyProgram> list = dao.getAll();
        List<TherapyProgramDTO> dtoList = new ArrayList<>();
        for (TherapyProgram entity : list) dtoList.add(new TherapyProgramDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getDuration(), entity.getCost()));
        return dtoList;
    }
}
