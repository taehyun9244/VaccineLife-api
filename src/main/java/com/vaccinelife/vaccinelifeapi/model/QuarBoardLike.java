package com.vaccinelife.vaccinelifeapi.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;

@Getter
@RestController
@Setter
@NoArgsConstructor
@Entity
public class QuarBoardLike{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quarBoardId")
    private QuarBoard quarBoard;

    public QuarBoardLike(QuarBoard quarBoard, User user){
        this.quarBoard = quarBoard;
        quarBoard.getQuarBoardLikeList().add(this);
        this.user = user;
    }
}
