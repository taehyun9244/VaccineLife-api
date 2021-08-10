package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.QuarComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class QuarBoardDeleteRequestDto {
    private  Long id;
    private Long quarBoardId;
    private Long userId;
    @Builder
    public QuarBoardDeleteRequestDto(Long id, Long quarBoardId, Long userId) {
        this.id = id;
        this.quarBoardId = quarBoardId;
        this.userId = userId;
    }

    public static QuarBoardDeleteRequestDto of(QuarComment comment){
        return QuarBoardDeleteRequestDto.builder()
                .id(comment.getId())
                .quarBoardId(comment.getQuarBoard().getId())
                .userId(comment.getUser().getId())
                .build();

    }
}
