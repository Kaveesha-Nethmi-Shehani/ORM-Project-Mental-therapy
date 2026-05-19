package org.example.mental_therapy_orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionSchedule {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TherapyProgram therapyProgram;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    private Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String date;
    private String time;
}


