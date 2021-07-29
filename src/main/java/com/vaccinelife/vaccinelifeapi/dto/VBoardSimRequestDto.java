package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.VBoard;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VBoardSimRequestDto {
    private String nickname;
    private String title;
    private int likeCount;
    private int hits;
    private int commentCount;
//    private String createdAt;
//    private String modifiedAt;

    @Builder
    public VBoardSimRequestDto(String nickname, String title, int likeCount, int hits, int commentCount, String createdAt, String modifiedAt) {
        this.nickname = nickname;
        this.title = title;
        this.likeCount = likeCount;
        this.hits = hits;
        this.commentCount = commentCount;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
    }
    
    public static VBoardSimRequestDto of(VBoard vBoard){
        return VBoardSimRequestDto.builder()
                .nickname(vBoard.getUser().getNickname())
                .title(vBoard.getTitle())
                .likeCount(vBoard.getLikeCount())
                .hits(vBoard.getHits())
                .commentCount(vBoard.getCommentCount())
//                .createdAt(vBoard.getCreatedAt())
//                .modifiedAt(vBoard.getModifiedAt())
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
