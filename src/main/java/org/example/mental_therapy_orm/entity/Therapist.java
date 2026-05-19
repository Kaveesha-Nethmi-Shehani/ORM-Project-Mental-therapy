package org.example.mental_therapy_orm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Therapist {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String specialization;
    private String availability;

    @OneToMany(mappedBy = "therapist", cascade = CascadeType.ALL)
    private List<SessionSchedule> sessions;
}


