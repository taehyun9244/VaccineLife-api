package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.VBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
@Setter

public class VBoard extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = true)
    private int hits;

    @Column(nullable = true)
    private int commentCount;

    @Column(nullable = true)
    private int likeCount;


    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Builder
    public VBoard(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public VBoard(VBoardPostRequsetDto vBoardPostRequsetDto) {
        this.title = vBoardPostRequsetDto.getTitle();
        this.contents = vBoardPostRequsetDto.getContents();
    }

    public void update(VBoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


}