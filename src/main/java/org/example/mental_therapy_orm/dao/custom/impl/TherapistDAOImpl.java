package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.TherapistDAO;
import org.example.mental_therapy_orm.entity.Therapist;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TherapistDAOImpl implements TherapistDAO {
    @Override public boolean save(Therapist entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(Therapist entity) throws Exception {
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
        Therapist entity = session.get(Therapist.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public Therapist search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Therapist entity = session.get(Therapist.class, id);
        session.close();
        return entity;
    }
    @Override public List<Therapist> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Therapist> list = session.createQuery("FROM Therapist", Therapist.class).list();
        session.close();
        return list;
    }
}
