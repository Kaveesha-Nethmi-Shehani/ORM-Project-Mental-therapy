package org.example.mental_therapy_orm.bo.custom.impl;

import org.example.mental_therapy_orm.bo.custom.UserBO;
import org.example.mental_therapy_orm.dao.DAOFactory;
import org.example.mental_therapy_orm.dao.custom.UserDAO;
import org.example.mental_therapy_orm.dto.UserDTO;
import org.example.mental_therapy_orm.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private UserDAO dao = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean save(UserDTO dto) throws Exception {
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        return dao.save(new User(dto.getUsername(), hashedPassword, dto.getRole()));
    }

    @Override
    public boolean update(UserDTO dto) throws Exception {
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        return dao.update(new User(dto.getUsername(), hashedPassword, dto.getRole()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public UserDTO search(String id) throws Exception {
        User entity = dao.search(id);
        if(entity != null) return new UserDTO(entity.getUsername(), entity.getPassword(), entity.getRole());
        return null;
    }

    @Override
    public List<UserDTO> getAll() throws Exception {
        List<User> list = dao.getAll();
        List<UserDTO> dtoList = new ArrayList<>();
        for (User entity : list) {
            dtoList.add(new UserDTO(entity.getUsername(), entity.getPassword(), entity.getRole()));
        }
        return dtoList;
    }
}
