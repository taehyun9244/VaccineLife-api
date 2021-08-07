package com.vaccinelife.vaccinelifeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


    @Builder
    public VacBoardTopRequestDto(Long vacBoardId, String title, String contents, int likeCount, int totalVisitors, int commentCount, String type, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.vacBoardId = vacBoardId;
        this.title = title;
        this.contents = contents;
        this.likeCount = likeCount;
        this.totalVisitors = totalVisitors;
        this.commentCount = commentCount;
        this.type = type;
        this.createdAt=createdAt;
        this.modifiedAt=modifiedAt;
    }

    public static VacBoardTopRequestDto of(VacBoard vacBoard){
        return VacBoardTopRequestDto.builder()
                .vacBoardId(vacBoard.getId())
                .title(vacBoard.getTitle())
                .contents(vacBoard.getContents())
                .likeCount(vacBoard.getLikeCount())
                .totalVisitors(vacBoard.getTotalVisitors())
                .createdAt(vacBoard.getCreatedAt())
                .type(vacBoard.getUser().getType())
                .commentCount(vacBoard.getCommentCount())
                .createdAt(vacBoard.getCreatedAt())
                .modifiedAt(vacBoard.getModifiedAt())
                .build();
    }

    public static List<VacBoardTopRequestDto> list(List<VacBoard> boards){
        List<VacBoardTopRequestDto> vacBoardTopRequestDtos = new ArrayList<>();
        for(VacBoard vacBoard : boards){
            vacBoardTopRequestDtos.add(of(vacBoard));
        }
        return vacBoardTopRequestDtos;
    }

}
