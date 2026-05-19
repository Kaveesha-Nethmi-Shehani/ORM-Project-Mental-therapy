package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.TherapistDTO;
import java.util.List;
public interface TherapistBO extends SuperBO {
    boolean save(TherapistDTO dto) throws Exception;
    boolean update(TherapistDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    TherapistDTO search(String id) throws Exception;
    List<TherapistDTO> getAll() throws Exception;
}
