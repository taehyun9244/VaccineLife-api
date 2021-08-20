package com.vaccinelife.vaccinelifeapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
public class MedicalLike {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "medicalId")
    private Medical medical;

    public MedicalLike(Medical medical, User user){
        this.medical = medical;
        medical.getMedicalLikes().add(this);
        this.user = user;
    }

}
