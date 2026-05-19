package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.SessionScheduleDAO;
import org.example.mental_therapy_orm.entity.SessionSchedule;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class SessionScheduleDAOImpl implements SessionScheduleDAO {
    @Override public boolean save(SessionSchedule entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(SessionSchedule entity) throws Exception {
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
        SessionSchedule entity = session.get(SessionSchedule.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public SessionSchedule search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        SessionSchedule entity = session.get(SessionSchedule.class, id);
        session.close();
        return entity;
    }
    @Override public List<SessionSchedule> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<SessionSchedule> list = session.createQuery("FROM SessionSchedule", SessionSchedule.class).list();
        session.close();
        return list;
    }
}
