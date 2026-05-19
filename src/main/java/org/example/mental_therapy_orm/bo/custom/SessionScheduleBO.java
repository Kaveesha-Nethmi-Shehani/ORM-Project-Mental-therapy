package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.SessionScheduleDTO;
import java.util.List;
public interface SessionScheduleBO extends SuperBO {
    boolean save(SessionScheduleDTO dto) throws Exception;
    boolean update(SessionScheduleDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    SessionScheduleDTO search(String id) throws Exception;
    List<SessionScheduleDTO> getAll() throws Exception;
}
