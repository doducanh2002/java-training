package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;
import org.aibles.privatetraining.service.ReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.aibles.privatetraining.util.SecurityService.getUserId;

@RestController
@Slf4j
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping("/{post_id}/reactions")
    @ResponseStatus(HttpStatus.CREATED)
    public ReactionResponse createReaction(@Validated @PathVariable("post_id")String postId, @RequestBody ReactionRequest request) {
        log.info("(createReaction) Request: {}, postId: {}", request, postId);
        return reactionService.createReaction(getUserId(),postId,request);
    }

    @DeleteMapping("/{post_id}/reactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReaction(@PathVariable("post_id") String postId,@PathVariable("id") String id) {
        reactionService.deleteReaction(postId,id);
    }

    @GetMapping("/{post_id}/reactions/{id}")
    public ReactionResponse getReactionById(@PathVariable("post_id")String postId,@PathVariable("id") String id) {
        log.info("(getReactionById) ID: {}", id);
        return reactionService.getReactionById(postId,id);
    }

    @PutMapping("/{post_id}/reactions/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReactionResponse updateReaction(@PathVariable("post_id")String postId, @PathVariable("id") String id, @Validated @RequestBody ReactionRequest request) {
        log.info("(updateReaction) ID: {}, Request: {}", id, request);
        return reactionService.updateReaction(getUserId(),postId,id, request);
    }

    @GetMapping("/{post_id}/reactions")
    public List<ReactionResponse> search(@PathVariable("post_id") String postId){
        return reactionService.searchReaction(postId);
    }
}
