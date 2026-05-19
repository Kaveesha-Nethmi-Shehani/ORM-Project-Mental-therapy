package org.example.mental_therapy_orm.dto;

public class TherapyProgramDTO {
    private String id;
    private String name;
    private String description;
    private String duration;
    private Double cost;

    public TherapyProgramDTO() {}
    public TherapyProgramDTO(String id, String name, String description, String duration, Double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
    }
    public String getId() { return this.id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }
    public String getDuration() { return this.duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public Double getCost() { return this.cost; }
    public void setCost(Double cost) { this.cost = cost; }
}
