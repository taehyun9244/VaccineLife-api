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
public class VacBoardLikeMypageDto {

    private Long vacBoardId;
    private Long userId;
    private String title;
    private int likeCount;
    private int totalVisitors;
    private int commentCount;
    private String type;

    public static VacBoardLikeMypageDto of(VacBoardLike vacBoardLike) {
        return VacBoardLikeMypageDto.builder()
                .vacBoardId(vacBoardLike.getVacBoard().getId())
                .userId(vacBoardLike.getUser().getId())
                .title(vacBoardLike.getVacBoard().getTitle())
                .likeCount(vacBoardLike.getVacBoard().getLikeCount())
                .totalVisitors(vacBoardLike.getVacBoard().getTotalVisitors())
                .commentCount(vacBoardLike.getVacBoard().getCommentCount())
                .type(vacBoardLike.getUser().getType())
                .build();

    }

    public static List<VacBoardLikeMypageDto> list(List<VacBoardLike> vacBoardLike) {
        List<VacBoardLikeMypageDto> vacBoardLikeRequestDtos = new ArrayList<>();
        for (VacBoardLike vacBoardLikes : vacBoardLike) {
            vacBoardLikeRequestDtos.add(of(vacBoardLikes));
        }
        return vacBoardLikeRequestDtos;
    }
}
