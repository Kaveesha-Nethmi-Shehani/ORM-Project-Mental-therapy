package org.example.mental_therapy_orm.dto;


public class PaymentDTO {
    private String id;
    private String patientId;
    private String patientName;
    private Double amount;
    private String method;
    private String date;

    public PaymentDTO() {}
    public PaymentDTO(String id, String patientId, String patientName, Double amount, String method, String date) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.amount = amount;
        this.method = method;
        this.date = date;
    }
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getPatientId() { return this.patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getPatientName() { return this.patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public Double getAmount() { return this.amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getMethod() { return this.method; }
    public void setMethod(String method) { this.method = method; }
    public String getDate() { return this.date; }
    public void setDate(String date) { this.date = date; }
}
