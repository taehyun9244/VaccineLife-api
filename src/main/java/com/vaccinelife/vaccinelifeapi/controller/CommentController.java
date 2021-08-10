package com.vaccinelife.vaccinelifeapi.controller;


import com.vaccinelife.vaccinelifeapi.dto.CommentDeleteRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.CommentPostRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import com.vaccinelife.vaccinelifeapi.exception.ApiException;
import com.vaccinelife.vaccinelifeapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RequestMapping("/api/comment")
@RestController
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<Void> createComment( @RequestBody CommentPostRequestDto requestDto){
        commentService.createComment(requestDto);
        return ResponseEntity.created(URI.create("/api/comment")).build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteComment(@RequestBody CommentDeleteRequestDto requestDto) {
        commentService.deleteComment( requestDto);
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
}