package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VBoardSimRequestDto {
    private Long id;
    private String title;
    private int likeCount;
    private int hits;
    private int commentCount;
    private User user;


    @Builder
    public VBoardSimRequestDto( Long id, String title, int likeCount, int hits, int commentCount,User user) {
        this.id = id;
        this.title = title;
        this.likeCount = likeCount;
        this.hits = hits;
        this.commentCount = commentCount;
        this.user = user;

    }
    
    public static VBoardSimRequestDto of(VBoard vBoard){
        return VBoardSimRequestDto.builder()
                .id(vBoard.getId())
                .title(vBoard.getTitle())

                .likeCount(vBoard.getLikeCount())
                .hits(vBoard.getHits())
                .commentCount(vBoard.getCommentCount())
                .user(vBoard.getUser())
                .build();
    }
    
    public static List<VBoardSimRequestDto> list(List<VBoard> boards){
        ArrayList<VBoardSimRequestDto> VBoardSimRequestDtos = new ArrayList<>();
        for(VBoard vBoard : boards){
            VBoardSimRequestDtos.add(of(vBoard));
        }
        return VBoardSimRequestDtos;
    }
    
}
