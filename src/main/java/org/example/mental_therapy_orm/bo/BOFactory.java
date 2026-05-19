package org.example.mental_therapy_orm.bo;
import org.example.mental_therapy_orm.bo.custom.impl.*;
public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() { return boFactory == null ? boFactory = new BOFactory() : boFactory; }
    public enum BOTypes { PATIENT, THERAPIST, PAYMENT, USER, THERAPYPROGRAM, SESSIONSCHEDULE }
    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case PATIENT: return new PatientBOImpl();
            case THERAPIST: return new TherapistBOImpl();
            case PAYMENT: return new PaymentBOImpl();
            case USER: return new UserBOImpl();
            case THERAPYPROGRAM: return new TherapyProgramBOImpl();
            case SESSIONSCHEDULE: return new SessionScheduleBOImpl();
            default: return null;
        }
    }
}
