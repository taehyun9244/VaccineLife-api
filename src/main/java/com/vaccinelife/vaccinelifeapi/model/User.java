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

    @OneToOne
    private Survey survey;

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

    public User(String username, String password, UserRole role, String nickname, Boolean isSurvey) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.isSurvey = isSurvey;
    }


}