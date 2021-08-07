package com.vaccinelife.vaccinelifeapi.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vaccinelife.vaccinelifeapi.model.QuarBoard;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuarBoardTopRequestDto {
    private Long quarBoardId;
    private String title;
    private String contents;
    private int likeCount;
    private int totalVisitors;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public QuarBoardTopRequestDto(Long quarBoardId, String title, String contents, int likeCount, int totalVisitors, int commentCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.quarBoardId = quarBoardId;
        this.title = title;
        this.contents = contents;
        this.likeCount = likeCount;
        this.totalVisitors = totalVisitors;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static QuarBoardTopRequestDto of(QuarBoard quarBoard){
        return QuarBoardTopRequestDto.builder()
                .quarBoardId(quarBoard.getId())
                .title(quarBoard.getTitle())
                .contents(quarBoard.getContents())
                .likeCount(quarBoard.getLikeCount())
                .totalVisitors(quarBoard.getTotalVisitors())
                .commentCount(quarBoard.getCommentCount())
                .createdAt(quarBoard.getCreatedAt())
                .modifiedAt(quarBoard.getModifiedAt())
                .build();
    }
    public static List<QuarBoardTopRequestDto> list(List<QuarBoard> boards){
        ArrayList<QuarBoardTopRequestDto> quarBoardTopRequestDtos = new ArrayList<>();
        for(QuarBoard quarBoard : boards){
            quarBoardTopRequestDtos.add(of(quarBoard));
        }
        return quarBoardTopRequestDtos;
    }
}
