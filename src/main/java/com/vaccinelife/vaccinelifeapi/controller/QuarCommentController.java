package com.vaccinelife.vaccinelifeapi.controller;


import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.QuarCommentPostRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.QuarCommentRequestDto;
import com.vaccinelife.vaccinelifeapi.exception.ApiException;
import com.vaccinelife.vaccinelifeapi.service.QuarCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/quarcomment")
@RestController
@RequiredArgsConstructor

public class QuarCommentController {

    private final QuarCommentService quarCommentService;

//    댓글 작성
   @PostMapping("")
    public ResponseEntity<Void> createQuarComment(@RequestBody QuarCommentPostRequestDto requestDto){
        quarCommentService.createQuarComment(requestDto);
        return ResponseEntity.created(URI.create("/api/quarcomment")).build();
    }

//    댓글 삭제
    @DeleteMapping("/{quarcommentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "quarcommentId") Long id , @RequestBody QuarCommentRequestDto requestDto) {
        quarCommentService.deleteComment(id, requestDto);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handle(IllegalArgumentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handle(IllegalArgumentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(
                apiException,
                HttpStatus.BAD_REQUEST
        );
    }

}
