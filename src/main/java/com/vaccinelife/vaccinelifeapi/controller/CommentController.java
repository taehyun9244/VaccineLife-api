package com.vaccinelife.vaccinelifeapi.controller;


import com.vaccinelife.vaccinelifeapi.dto.CommentRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Comment;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("api/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

//    @GetMapping("/{vBoardId}")
//    public ResponseEntity<List<CommentRequestDto>> getComment(){
//        return ResponseEntity.ok().body(commentService.getComment());
//    }

        @PostMapping("/{vBoardId}")
    public ResponseEntity<Void> createComment(@RequestBody CommentRequestDto requestDto){
        commentService.createComment(requestDto);
        return ResponseEntity.created(URI.create("api/comment/{vBoardId}")).build();
    }
//    @PostMapping(" ")
//    public ResponseEntity<Void> createComment(@RequestBody CommentRequestDto requestDto) {
//        commentService.createComment(requestDto);
//        return ResponseEntity.created(URI.create("api/comment")).build();
//    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable(name = "commentId") Long id ,@RequestBody CommentRequestDto requestDto) {
        commentService.deleteComment(id, requestDto);
        return ResponseEntity.ok().build();
    }

}
