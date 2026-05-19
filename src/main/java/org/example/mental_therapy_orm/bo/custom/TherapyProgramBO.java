package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.TherapyProgramDTO;
import java.util.List;
public interface TherapyProgramBO extends SuperBO {
    boolean save(TherapyProgramDTO dto) throws Exception;
    boolean update(TherapyProgramDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    TherapyProgramDTO search(String id) throws Exception;
    List<TherapyProgramDTO> getAll() throws Exception;
}
