package org.example.mental_therapy_orm.dto;


public class PatientDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String medicalHistory;

    public PatientDTO() {}
    public PatientDTO(String id, String name, String email, String phone, String address, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }
    public String getMedicalHistory() { return this.medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
}
