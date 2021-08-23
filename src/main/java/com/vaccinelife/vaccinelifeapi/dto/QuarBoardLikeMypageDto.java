package com.vaccinelife.vaccinelifeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vaccinelife.vaccinelifeapi.model.QuarBoardLike;
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
public class QuarBoardLikeMypageDto {

    private Long quarBoardId;
    private Long userId;
    private String title;
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
    public QuarBoardLikeMypageDto(Long quarBoardId, Long userId, String title, int likeCount, int commentCount, int totalVisitors, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.quarBoardId = quarBoardId;
        this.userId = userId;
        this.title = title;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.totalVisitors = totalVisitors;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }



    public static QuarBoardLikeMypageDto of(QuarBoardLike quarBoardLike){
        return QuarBoardLikeMypageDto.builder()
                .quarBoardId(quarBoardLike.getQuarBoard().getId())
                .userId(quarBoardLike.getUser().getId())
                .title(quarBoardLike.getQuarBoard().getTitle())
                .likeCount(quarBoardLike.getQuarBoard().getLikeCount())
                .commentCount(quarBoardLike.getQuarBoard().getCommentCount())
                .totalVisitors(quarBoardLike.getQuarBoard().getTotalVisitors())
                .createdAt(quarBoardLike.getQuarBoard().getCreatedAt())
                .modifiedAt(quarBoardLike.getQuarBoard().getModifiedAt())
                .build();
    }

    public static List<QuarBoardLikeMypageDto> list(List<QuarBoardLike> quarBoardLike){
        List<QuarBoardLikeMypageDto> quarLikeRequestDtos = new ArrayList<>();
        for(QuarBoardLike quarBoardLikes : quarBoardLike){
            quarLikeRequestDtos.add(of(quarBoardLikes));
        }
        return quarLikeRequestDtos;
    }
}
