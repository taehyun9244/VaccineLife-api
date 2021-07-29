package com.vaccinelife.vaccinelifeapi.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Survey {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private Boolean disease;
    @Column(nullable = false)
    private int degree;
    @Column(nullable = false)
    private String aftereffect;

    @OneToOne
    private User user;

    @Builder
    public Survey(String type, String gender, int age, Boolean disease, int degree, String aftereffect, User user) {
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.disease = disease;
        this.degree = degree;
        this.aftereffect = aftereffect;
        this.user = user;
    }
}
