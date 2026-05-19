package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.TherapyProgramDAO;
import org.example.mental_therapy_orm.entity.TherapyProgram;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TherapyProgramDAOImpl implements TherapyProgramDAO {
    @Override public boolean save(TherapyProgram entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(TherapyProgram entity) throws Exception {
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
        TherapyProgram entity = session.get(TherapyProgram.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public TherapyProgram search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        TherapyProgram entity = session.get(TherapyProgram.class, id);
        session.close();
        return entity;
    }
    @Override public List<TherapyProgram> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<TherapyProgram> list = session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        session.close();
        return list;
    }
}
