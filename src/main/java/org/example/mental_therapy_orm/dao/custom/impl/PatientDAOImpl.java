package org.example.mental_therapy_orm.dao.custom.impl;
import org.example.mental_therapy_orm.dao.custom.PatientDAO;
import org.example.mental_therapy_orm.entity.Patient;
import org.example.mental_therapy_orm.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    @Override public boolean save(Patient entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
        return true;
    }
    @Override public boolean update(Patient entity) throws Exception {
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
        Patient entity = session.get(Patient.class, id);
        if(entity != null) session.delete(entity);
        tx.commit();
        session.close();
        return entity != null;
    }
    @Override public Patient search(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Patient entity = session.get(Patient.class, id);
        session.close();
        return entity;
    }
    @Override public List<Patient> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Patient> list = session.createQuery("FROM Patient", Patient.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Patient> getPatientsWithEnrolledPrograms() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        // HQL JOIN FETCH query to fetch patients and their enrolled therapy programs
        String hql = "SELECT DISTINCT p FROM Patient p LEFT JOIN FETCH p.sessions s LEFT JOIN FETCH s.therapyProgram";
        List<Patient> list = session.createQuery(hql, Patient.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Patient> getPatientsEnrolledInAllPrograms() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        // HQL relational division logic using COUNT
        String hql = "SELECT p FROM Patient p WHERE (SELECT COUNT(DISTINCT s.therapyProgram.id) FROM SessionSchedule s WHERE s.patient = p) = (SELECT COUNT(tp) FROM TherapyProgram tp)";
        List<Patient> list = session.createQuery(hql, Patient.class).list();
        session.close();
        return list;
    }
}
