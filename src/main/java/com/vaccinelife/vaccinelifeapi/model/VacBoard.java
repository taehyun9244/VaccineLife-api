package com.vaccinelife.vaccinelifeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaccinelife.vaccinelifeapi.dto.VacBoardRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class VacBoard extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacBoardId")
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
    @OneToMany(mappedBy = "vacBoard", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"vacBoard"})
    private List<Comment> comment;
    public void add(Comment comment) {
        comment.setVacBoard(this);
        this.comment.add(comment);
    }


//    @Builder
//    public VBoard(VBoardPostRequsetDto vBoardPostRequsetDto, User user){
//        this.user = vBoardPostRequsetDto.getUserId();
//        this.title = vBoardPostRequsetDto.getTitle();
//        this.contents = vBoardPostRequsetDto.getContents();
//    }

    @Builder
    public VacBoard(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void update(VacBoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    @OneToMany(mappedBy = "vacBoard", cascade = {CascadeType.REMOVE})
    private List<VacBoardLike> vacBoardLikeList = new ArrayList<>();

    public void updateLikeNum(int count) {
        this.likeCount += count;
    }

    public void deleteLikeNum(int count) {
        this.likeCount -= count;
    }

}