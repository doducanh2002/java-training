package org.aibles.privatetraining.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;
import org.aibles.privatetraining.dto.response.Response;
import org.aibles.privatetraining.service.ReactionService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/reactions")
@RequiredArgsConstructor
public class ReactionController {

    private final ReactionService reactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createReaction(@Validated @RequestBody ReactionRequest request) {
        log.info("(createReaction) Request: {}", request);
        return Response.of(
                HttpStatus.CREATED.value(),
                reactionService.createReaction(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteReaction(@PathVariable String id) {
        reactionService.deleteReaction(id);
        return Response.of(
                HttpStatus.OK.value(),
                "Delete reaction successfully");
    }

    @GetMapping("/{id}")
    public Response getReactionById(@PathVariable String id) {
        log.info("(getReactionById) ID: {}", id);
        return Response.of(HttpStatus.OK.value(), reactionService.getReactionById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response updateReaction(@PathVariable String id, @Validated @RequestBody ReactionRequest request) {
        log.info("(updateReaction) ID: {}, Request: {}", id, request);
        return Response.of(
                HttpStatus.CREATED.value(),
                reactionService.updateReaction(id, request));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(){
        return Response.of(HttpStatus.OK.value(), reactionService.getAllReactions());
    }


    @GetMapping("/search")
    public Response search(@RequestParam(required = false) String postId){
        return Response.of(HttpStatus.OK.value(), reactionService.searchReaction(postId));
    }
}
