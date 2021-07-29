package com.vaccinelife.vaccinelifeapi.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Boolean isVaccine;


    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private int degree;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private int age;

    @Column(nullable = true)
    private Boolean disease;

    @Column(nullable = true)
    private String afterEffect;



    @OneToMany(mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();
    public void add(Comment comment) {
        comment.setUser(this);
        this.comments.add(comment);
    }

    @OneToMany(mappedBy = "user")
    private Set<VBoard> vBoards = new HashSet<>();
    public void add(VBoard vBoard){
        vBoard.setUser(this);
        this.vBoards.add(vBoard);
    }



    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;


    public User(String username, String password, UserRole role, String nickname, Boolean isVaccine, String type,int degree, String gender, int age, Boolean disease, String afterEffect) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.isVaccine=isVaccine;
        this.type=type;
        this.degree=degree;
        this.gender=gender;
        this.age= age;
        this.disease= disease;
        this.afterEffect=afterEffect;

    }



}