package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.PaymentDAO;
import org.example.mental_therapy_orm.entity.Payment;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override public boolean save(Payment entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(Payment entity) throws Exception {
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
        Payment entity = session.get(Payment.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public Payment search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Payment entity = session.get(Payment.class, id);
        session.close();
        return entity;
    }
    @Override public List<Payment> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Payment> list = session.createQuery("FROM Payment", Payment.class).list();
        session.close();
        return list;
    }
}
