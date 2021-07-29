package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity

public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @ManyToOne
    private VBoard vBoard;

    @Column(nullable = false)
    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String comment;

    @Builder
    public Comment(VBoard vBoard, User user, String comment){
        this.vBoard = vBoard;
        this.user = user;
        this.comment = comment;
    }
//    public Comment( String comment){
//        this.comment = comment;
//    }
}
