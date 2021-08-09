package com.vaccinelife.vaccinelifeapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaccinelife.vaccinelifeapi.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Entity
@Getter
@NoArgsConstructor
public class User extends Timestamped{


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
    private Boolean isVaccine;


    @Column(nullable = true)
    private String type;

    @Column(nullable = true)
    private Integer degree;

    @Column(nullable = true)
    private String gender;

    @Column(nullable = true)
    private String age;

    @Column(nullable = true)
    private String disease;

    @Column(nullable = true)
    private String afterEffect;


    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"comment"})
    private List<Comment> comment = new ArrayList<>();
    public void add(Comment comment) {
        comment.setUser(this);
        this.comment.add(comment);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"quarcomment"})
    private List<QuarComment> quarComment = new ArrayList<>();
    public void add(QuarComment quarComment) {
        quarComment.setUser(this);
        this.quarComment.add(quarComment);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"vacBoard"})
    private Set<VacBoard> vacBoard = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<VacBoardLike> vacBoardLike = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"quarBoard"})
    private Set<QuarBoard> quarBoard = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<QuarBoardLike> quarBoardLike = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"medical"})
    private Set<Medical> medicals = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;




    public User( Long id, String username, String password, UserRole role, String nickname, Boolean isVaccine, String type,Integer degree, String gender, String age, String disease, String afterEffect) {
        this.id=id;
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