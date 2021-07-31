package com.vaccinelife.vaccinelifeapi.model;

import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
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
    public Comment(String comment, VBoard vBoard, User user) {
        this.comment = comment;
        this.vBoard = vBoard;
        this.user = user;
    }




}
