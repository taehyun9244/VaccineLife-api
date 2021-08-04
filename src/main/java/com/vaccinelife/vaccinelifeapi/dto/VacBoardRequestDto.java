package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacBoardRequestDto {
    private Long vacBoardId;
    private String title;
    private String contents;
    private int totalVisitors;
    private int commentCount;
    private int likeCount;
    private User user;
    private List<Comment> comments;

    public static VacBoardRequestDto of(VacBoard vacBoard){
        return VacBoardRequestDto.builder()
                .vacBoardId(vacBoard.getId())
                .title(vacBoard.getTitle())
                .contents(vacBoard.getContents())
                .totalVisitors(vacBoard.getTotalVisitors())
                .commentCount(vacBoard.getCommentCount())
                .likeCount(vacBoard.getLikeCount())
                .user(vacBoard.getUser())
                .comments(vacBoard.getComment())
                .build();
    }
}