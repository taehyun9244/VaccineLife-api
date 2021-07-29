package com.vaccinelife.vaccinelifeapi.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Boolean isSurvey;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    public User(String username, String password, UserRole role, String nickname, Boolean isSurvey){
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.isSurvey= isSurvey;
    }


}