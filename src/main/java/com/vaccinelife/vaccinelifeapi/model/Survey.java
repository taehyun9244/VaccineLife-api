package com.vaccinelife.vaccinelifeapi.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
public class Survey {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private String age;
    @Column(nullable = false)
    private int disease;
    @Column(nullable = false)
    private int degree;
    @Column(nullable = false)
    private String aftereffect;

    @OneToOne
    private User user;

}
