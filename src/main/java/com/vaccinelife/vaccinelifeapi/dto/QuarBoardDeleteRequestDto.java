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
    private Long quarBoardId;
    private Long userId;
    @Builder
    public QuarBoardDeleteRequestDto( Long quarBoardId, Long userId) {
        this.quarBoardId = quarBoardId;
        this.userId = userId;
    }

    public static QuarBoardDeleteRequestDto of(QuarComment comment){
        return QuarBoardDeleteRequestDto.builder()

                .quarBoardId(comment.getQuarBoard().getId())
                .userId(comment.getUser().getId())
                .build();

    }
}
