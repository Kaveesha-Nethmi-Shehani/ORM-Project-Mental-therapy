package org.example.mental_therapy_orm.dto;


public class TherapistDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String availability;

    public TherapistDTO() {}
    public TherapistDTO(String id, String name, String email, String phone, String specialization, String availability) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.availability = availability;
    }
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getSpecialization() { return this.specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getAvailability() { return this.availability; }
    public void setAvailability(String availability) { this.availability = availability; }
}
