package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vBoardId")
    private VBoard vBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Comment(VBoard vBoard, User user, String comment){
        this.vBoard = vBoard;
        this.user = user;
        this.comment = comment;
    }
    public Comment( String comment){
        this.comment = comment;
    }
}
