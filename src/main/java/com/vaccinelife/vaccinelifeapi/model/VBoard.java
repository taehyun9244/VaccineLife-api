package com.vaccinelife.vaccinelifeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaccinelife.vaccinelifeapi.dto.VBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class VBoard extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vBoardId")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int hits;

    @Column(nullable = false)
    private int commentCount;

    @Column(nullable = false)
    private int likeCount;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "vBoard", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"vBoard"})
    private List<Comment> comment;
    public void add(Comment comment) {
        comment.setVBoard(this);
        this.comment.add(comment);
    }


//    @Builder
//    public VBoard(VBoardPostRequsetDto vBoardPostRequsetDto, User user){
//        this.user = vBoardPostRequsetDto.getUserId();
//        this.title = vBoardPostRequsetDto.getTitle();
//        this.contents = vBoardPostRequsetDto.getContents();
//    }

    @Builder
    public VBoard(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void update(VBoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


}