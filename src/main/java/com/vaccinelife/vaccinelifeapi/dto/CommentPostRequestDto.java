package com.vaccinelife.vaccinelifeapi.dto;

import com.vaccinelife.vaccinelifeapi.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostRequestDto {
    private Long vacBoardId;
    private Long userId;
    private String comment;
}
