package org.example.mental_therapy_orm.dto;

public class SessionScheduleDTO {
    private String id;
    private String programId;
    private String therapistId;
    private String patientId;
    private String date;
    private String time;

    public SessionScheduleDTO() {}
    public SessionScheduleDTO(String id, String programId, String therapistId, String patientId, String date, String time) {
        this.id = id;
        this.programId = programId;
        this.therapistId = therapistId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
    }
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getProgramId() { return this.programId; }
    public void setProgramId(String programId) { this.programId = programId; }
    public String getTherapistId() { return this.therapistId; }
    public void setTherapistId(String therapistId) { this.therapistId = therapistId; }
    public String getPatientId() { return this.patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getDate() { return this.date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return this.time; }
    public void setTime(String time) { this.time = time; }
}
