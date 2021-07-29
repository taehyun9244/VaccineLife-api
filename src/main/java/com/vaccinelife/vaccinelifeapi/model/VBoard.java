package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class VBoard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Long surveyId;

    @Column(nullable = false)
    private int hits;

    @Column(nullable = false)
    private int commentCount;

    @Column(nullable = false)
    private int likeCount;



    @ManyToOne
    private User user;
    @ManyToOne
    private Survey survey;


//    public VBoard(String title, String contents, int hits, int commentCount, int likeCount, User user, Survey survey) {
//        this.title = title;
//        this.contents = contents;
//        this.hits = hits;
//        this.commentCount = commentCount;
//        this.likeCount = likeCount;
//        this.user = user;
//        this.survey = survey;
//    }
//    public VBoard(VBoardRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.contents = requestDto.getContents();
//        this.hits = requestDto.getHits();
//        this.commentCount = requestDto.getCommentCount();
//        this.likeCount = requestDto.getLikeCount();
//        this.user = requestDto.getUserId();
//        this.survey = requestDto.getSurveyId();
//    }
//    public void update(VBoardRequestDto requestDto){
//        this.title = requestDto.getTitle();
//        this.contents = requestDto.getContents();
//        this.hits = requestDto.getHits();
//        this.commentCount = requestDto.getCommentCount();
//        this.likeCount = requestDto.getLikeCount();
//        this.user = requestDto.getUserId();
//        this.survey = requestDto.getSurveyId();
//    }


}