package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.UserDAO;
import org.example.mental_therapy_orm.entity.User;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override public boolean save(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        User entity = session.get(User.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public User search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        User entity = session.get(User.class, id);
        session.close();
        return entity;
    }
    @Override public List<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<User> list = session.createQuery("FROM User", User.class).list();
        session.close();
        return list;
    }
}
