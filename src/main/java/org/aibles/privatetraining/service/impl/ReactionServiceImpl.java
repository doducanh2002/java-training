package org.aibles.privatetraining.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;
import org.aibles.privatetraining.entity.Reaction;
import org.aibles.privatetraining.exception.ReactionNotFoundException;
import org.aibles.privatetraining.repository.ReactionRepository;
import org.aibles.privatetraining.service.PostService;
import org.aibles.privatetraining.service.ReactionService;
import org.aibles.privatetraining.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReactionServiceImpl implements ReactionService {

    private final ReactionRepository repository;

    private final UserProfileService userProfileService;
    private final PostService postService;

    @Autowired
    public ReactionServiceImpl(ReactionRepository repository, UserProfileService userProfileService, PostService postService) {
        this.repository = repository;
        this.userProfileService = userProfileService;
        this.postService = postService;
    }

    @Override
    public ReactionResponse createReaction(String userId, String postId, ReactionRequest reactionRequest) {
        log.info("(createReaction) Request: {}", reactionRequest);
        postService.checkPostId(postId);
        Reaction reaction = Reaction.of(reactionRequest);
        reaction.setPostId(postId);
        reaction.setUserId(userId);
        repository.save(reaction);
        return ReactionResponse.from(reaction);
    }

    @Override
    public ReactionResponse getReactionById(String postId,String id) {
        log.info("(getReactionById) ID: {}", id);
        postService.checkPostId(postId);
        Reaction reaction = repository.findById(id)
                                .orElseThrow(() -> new ReactionNotFoundException(id));
        return ReactionResponse.from(reaction);
    }

    @Override
    @Transactional
    public ReactionResponse updateReaction(String userId, String postId, String id, ReactionRequest reactionRequest) {
        log.info("(updateReaction) ID: {}, Request: {}", id, reactionRequest);
        postService.checkPostId(postId);
        Reaction reaction = repository.findById(id)
                .orElseThrow(() -> new ReactionNotFoundException(id));
        reaction.setReactionType(reactionRequest.getReactionType());
        repository.save(reaction);
        return ReactionResponse.from(reaction);
    }

    @Override
    public void deleteReaction(String postId, String reactionId) {
        log.info("(deleteReaction) ID: {}", reactionId);
        postService.checkPostId(postId);
        checkReactionId(reactionId);
        repository.deleteById(reactionId);
    }

    @Override
    public void checkReactionId(String reactionId) {
        if (!repository.existsById(reactionId)) {
            throw new ReactionNotFoundException(reactionId);
        }
    }

    @Override
    public List<ReactionResponse> searchReaction(String postId) {
        postService.checkPostId(postId);
        List<Reaction> reactions = repository.findAllByPostId(postId);
        log.info("list: {}", reactions);
        return reactions.stream()
                .map(ReactionResponse::from)
                .collect(Collectors.toList());
    }
}
