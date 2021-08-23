package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.QuarBoardLike;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter @Builder
@NoArgsConstructor
public class QuarBoardLikeMypageDto {

    private Long quarBoardId;
    private Long userId;
    private String title;
    private int likeCount;
    private int commentCount;
    private int totalVisitors;


    public static QuarBoardLikeMypageDto of(QuarBoardLike quarBoardLike){
        return QuarBoardLikeMypageDto.builder()
                .quarBoardId(quarBoardLike.getQuarBoard().getId())
                .userId(quarBoardLike.getUser().getId())
                .title(quarBoardLike.getQuarBoard().getTitle())
                .likeCount(quarBoardLike.getQuarBoard().getLikeCount())
                .commentCount(quarBoardLike.getQuarBoard().getCommentCount())
                .totalVisitors(quarBoardLike.getQuarBoard().getTotalVisitors())
                .build();
    }

    public static List<QuarBoardLikeMypageDto> list(List<QuarBoardLike> quarBoardLike){
        List<QuarBoardLikeMypageDto> quarLikeRequestDtos = new ArrayList<>();
        for(QuarBoardLike quarBoardLikes : quarBoardLike){
            quarLikeRequestDtos.add(of(quarBoardLikes));
        }
        return quarLikeRequestDtos;
    }
}
