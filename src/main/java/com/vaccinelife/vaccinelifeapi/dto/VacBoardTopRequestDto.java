package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VacBoardTopRequestDto {
    private Long vacBoardId;
    private String title;
    private String contents;
    private int likeCount;
    private int totalVisitors;
    private int commentCount;
    private String type;

    @Builder
    public VacBoardTopRequestDto(Long vacBoardId, String title, String contents, int likeCount, int totalVisitors, int commentCount, String type) {
        this.vacBoardId = vacBoardId;
        this.title = title;
        this.contents = contents;
        this.likeCount = likeCount;
        this.totalVisitors = totalVisitors;
        this.commentCount = commentCount;
        this.type = type;
    }

    public static VacBoardTopRequestDto of(VacBoard vacBoard){
        return VacBoardTopRequestDto.builder()
                .vacBoardId(vacBoard.getId())
                .title(vacBoard.getTitle())
                .contents(vacBoard.getContents())
                .likeCount(vacBoard.getLikeCount())
                .totalVisitors(vacBoard.getTotalVisitors())
                .type(vacBoard.getUser().getType())
                .commentCount(vacBoard.getCommentCount())
                .build();
    }

    public static List<VacBoardTopRequestDto> list(List<VacBoard> boards){
        ArrayList<VacBoardTopRequestDto> vacBoardTopRequestDtos = new ArrayList<>();
        for(VacBoard vacBoard : boards){
            vacBoardTopRequestDtos.add(of(vacBoard));
        }
        return vacBoardTopRequestDtos;
    }

}
