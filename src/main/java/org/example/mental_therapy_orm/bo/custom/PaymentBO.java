package org.example.mental_therapy_orm.bo.custom;
import org.example.mental_therapy_orm.bo.SuperBO;
import org.example.mental_therapy_orm.dto.PaymentDTO;
import java.util.List;
public interface PaymentBO extends SuperBO {
    boolean save(PaymentDTO dto) throws Exception;
    boolean update(PaymentDTO dto) throws Exception;
    boolean delete(String id) throws Exception;
    PaymentDTO search(String id) throws Exception;
    List<PaymentDTO> getAll() throws Exception;
}
