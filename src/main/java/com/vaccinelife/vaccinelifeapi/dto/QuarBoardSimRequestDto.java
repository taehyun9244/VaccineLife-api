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
public class QuarBoardSimRequestDto {
    private Long quarBoardId;
    private String title;
    private String nickname;
    private int likeCount;
    private int commentCount;
    private int totalVisitors;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "asia/seoul")
    @CreatedDate // 최초 생성 시점
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "asia/seoul")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @LastModifiedDate // 마지막 변경 시점
    private LocalDateTime modifiedAt;

    @Builder
    public QuarBoardSimRequestDto(Long quarBoardId, String title,String nickname, int likeCount, int commentCount, int totalVisitors, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.quarBoardId = quarBoardId;
        this.title = title;
        this.nickname = nickname;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.totalVisitors = totalVisitors;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static QuarBoardSimRequestDto of(QuarBoard quarBoard){
        return QuarBoardSimRequestDto.builder()
                .quarBoardId(quarBoard.getId())
                .title(quarBoard.getTitle())
                .nickname(quarBoard.getUser().getNickname())
                .likeCount(quarBoard.getLikeCount())
                .totalVisitors(quarBoard.getTotalVisitors())
                .commentCount(quarBoard.getCommentCount())
                .createdAt(quarBoard.getCreatedAt())
                .modifiedAt(quarBoard.getModifiedAt())
                .build();
    }
    public static List<QuarBoardSimRequestDto> list(List<QuarBoard> boards){
       List<QuarBoardSimRequestDto> QuarBoardSimRequestDto = new ArrayList<>();
        for(QuarBoard quarBoard : boards){
            QuarBoardSimRequestDto.add(of(quarBoard));
        }
        return QuarBoardSimRequestDto;
    }
}
