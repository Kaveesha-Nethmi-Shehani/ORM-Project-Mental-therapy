package org.example.mental_therapy_orm.dao;
import org.example.mental_therapy_orm.dao.custom.impl.*;
public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() { return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory; }
    public enum DAOTypes { PATIENT, THERAPIST, PAYMENT, USER, THERAPYPROGRAM, SESSIONSCHEDULE }
    public SuperDAO getDAO(DAOTypes type) {
        switch (type) {
            case PATIENT: return new PatientDAOImpl();
            case THERAPIST: return new TherapistDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
            case USER: return new UserDAOImpl();
            case THERAPYPROGRAM: return new TherapyProgramDAOImpl();
            case SESSIONSCHEDULE: return new SessionScheduleDAOImpl();
            default: return null;
        }
    }
}
