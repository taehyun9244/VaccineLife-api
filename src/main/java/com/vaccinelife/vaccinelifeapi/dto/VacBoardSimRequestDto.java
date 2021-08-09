package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VacBoardSimRequestDto {
    private Long id;
    private String title;
    private int likeCount;
    private int totalVisitors;
    private int commentCount;
    private String type;


    @Builder
    public VacBoardSimRequestDto(Long id, String title, int likeCount, int totalVisitors, int commentCount, String type) {
        this.id = id;
        this.title = title;
        this.likeCount = likeCount;
        this.totalVisitors = totalVisitors;
        this.commentCount=commentCount;
        this.type = type;

    }
    
    public static VacBoardSimRequestDto of(VacBoard vacBoard){
        return VacBoardSimRequestDto.builder()
                .id(vacBoard.getId())
                .title(vacBoard.getTitle())
                .likeCount(vacBoard.getLikeCount())
                .totalVisitors(vacBoard.getTotalVisitors())
                .commentCount(vacBoard.getCommentCount())
                .type(vacBoard.getUser().getType())
                .build();
    }
    
    public static List<VacBoardSimRequestDto> list(List<VacBoard> boards){
       List<VacBoardSimRequestDto> VacBoardSimRequestDtos = new ArrayList<>();
        for(VacBoard vacBoard : boards){
            VacBoardSimRequestDtos.add(of(vacBoard));
        }
        return VacBoardSimRequestDtos;
    }
    
}
