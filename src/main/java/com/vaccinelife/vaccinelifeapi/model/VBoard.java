package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.VBoardPostRequsetDto;
import com.vaccinelife.vaccinelifeapi.dto.VBoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class VBoard extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "v_board_id")
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

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @OneToMany
    private Set<Comment> comment = new HashSet<>();
    public void add(Comment comment){
        comment.setVBoard(this);
        this.comment.add(comment);
    }



    public VBoard(VBoardPostRequsetDto vBoardPostRequsetDto){
        this.title = vBoardPostRequsetDto.getTitle();
        this.contents = vBoardPostRequsetDto.getContents();
    }

    public void update(VBoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }


}