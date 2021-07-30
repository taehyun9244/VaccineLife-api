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


    @ManyToOne
    @JoinColumn(name = "v_board_id")
    private VBoard vBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String comment;

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
