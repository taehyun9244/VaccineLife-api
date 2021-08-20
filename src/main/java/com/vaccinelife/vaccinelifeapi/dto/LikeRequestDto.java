package com.vaccinelife.vaccinelifeapi.dto;



import com.vaccinelife.vaccinelifeapi.model.VacBoardLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeRequestDto {
    private Long vacBoardId;
    private Long userId;


    public static LikeRequestDto of(VacBoardLike vacBoardLike){
        return LikeRequestDto.builder()
                .vacBoardId(vacBoardLike.getVacBoard().getId())
                .userId(vacBoardLike.getUser().getId()).build();
    }

    public static List<LikeRequestDto> list(List<VacBoardLike> vacBoardLike){
      List<LikeRequestDto> likeRequestDtos = new ArrayList<>();
        for(VacBoardLike vacBoardLikes : vacBoardLike){
            likeRequestDtos.add(of(vacBoardLikes));
        }
        return likeRequestDtos;
    }
}