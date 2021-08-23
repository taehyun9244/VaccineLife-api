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
public class VacBoardLikeRequestDto {
    private Long vacBoardId;
    private Long userId;


    public static VacBoardLikeRequestDto of(VacBoardLike vacBoardLike){
        return VacBoardLikeRequestDto.builder()
                .vacBoardId(vacBoardLike.getVacBoard().getId())
                .userId(vacBoardLike.getUser().getId()).build();
    }

    public static List<VacBoardLikeRequestDto> list(List<VacBoardLike> vacBoardLike){
      List<VacBoardLikeRequestDto> vacBoardLikeRequestDtos = new ArrayList<>();
        for(VacBoardLike vacBoardLikes : vacBoardLike){
            vacBoardLikeRequestDtos.add(of(vacBoardLikes));
        }
        return vacBoardLikeRequestDtos;
    }
}