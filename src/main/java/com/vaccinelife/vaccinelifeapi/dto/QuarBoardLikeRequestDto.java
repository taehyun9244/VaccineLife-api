package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.QuarBoardLike;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class QuarBoardLikeRequestDto {

    private Long quarBoardId;
    private Long userId;

    public static QuarBoardLikeRequestDto of(QuarBoardLike quarBoardLike){
        return QuarBoardLikeRequestDto.builder()
                .quarBoardId(quarBoardLike.getQuarBoard().getId())
                .userId(quarBoardLike.getUser().getId()).build();
    }

    public static List<QuarBoardLikeRequestDto> list(List<QuarBoardLike> quarBoardLike){
        ArrayList<QuarBoardLikeRequestDto> quarLikeRequestDtos = new ArrayList<>();
        for(QuarBoardLike quarBoardLikes : quarBoardLike){
            quarLikeRequestDtos.add(of(quarBoardLikes));
        }
        return quarLikeRequestDtos;
    }
}
