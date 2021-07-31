package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VBoardRequestDto {
    private Long vBoardId;
    private String title;
    private String contents;
    private int hits;
    private int commentCount;
    private int likeCount;
    private User user;
    private List<Comment> comments;

    public static VBoardRequestDto of(VBoard vBoard){
        return VBoardRequestDto.builder()
                .vBoardId(vBoard.getId())
                .title(vBoard.getTitle())
                .contents(vBoard.getContents())
                .hits(vBoard.getHits())
                .commentCount(vBoard.getCommentCount())
                .likeCount(vBoard.getLikeCount())
                .user(vBoard.getUser())
                .comments(vBoard.getComment())
                .build();
    }
}