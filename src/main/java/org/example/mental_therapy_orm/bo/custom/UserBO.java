package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.UserDTO;
import java.util.List;
public interface UserBO extends SuperBO {
    boolean save(UserDTO dto) throws Exception;
    boolean update(UserDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    UserDTO search(String id) throws Exception;
    List<UserDTO> getAll() throws Exception;
}
