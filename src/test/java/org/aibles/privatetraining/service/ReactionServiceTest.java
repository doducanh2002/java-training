package org.aibles.privatetraining.service;

import org.aibles.privatetraining.dto.request.ReactionRequest;
import org.aibles.privatetraining.dto.response.ReactionResponse;
import org.aibles.privatetraining.entity.Reaction;
import org.aibles.privatetraining.exception.ReactionNotFoundException;
import org.aibles.privatetraining.repository.ReactionRepository;
import org.aibles.privatetraining.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@WebMvcTest(ReactionService.class)
public class ReactionServiceTest {

    @Autowired
    private ReactionService reactionService;

    @MockBean
    private ReactionRepository reactionRepository;

    @MockBean
    private UserProfileService userProfileService;

    @MockBean
    private PostService postService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private ReactionRequest mockReactionRequest() {
        ReactionRequest request = new ReactionRequest();
        request.setReactionType("LIKE");
        return request;
    }

    private Reaction mockReactionEntity() {
        Reaction reaction = new Reaction();
        reaction.setId("reactionId");
        reaction.setUserId("userId");
        reaction.setPostId("postId");
        reaction.setReactionType("LIKE");
        return reaction;
    }

    @Test
    public void test_CreateReaction_Successful() {
        String userId = "userId";
        String postId = "postId";
        ReactionRequest reactionRequest = mockReactionRequest();
        Reaction mockEntity = mockReactionEntity();

        Mockito.when(reactionRepository.save(ArgumentMatchers.any(Reaction.class))).thenReturn(mockEntity);

        ReactionResponse response = reactionService.createReaction(userId, postId, reactionRequest);

        Assertions.assertEquals(reactionRequest.getReactionType(), response.getReactionType());
    }

    @Test
    public void test_GetReactionById_Successful() {
        String postId = "postId";
        String id = "reactionId";
        Reaction mockEntity = mockReactionEntity();

        Mockito.doNothing().when(postService).checkPostId(postId);

        Mockito.when(reactionRepository.findById(id)).thenReturn(Optional.of(mockEntity));

        ReactionResponse response = reactionService.getReactionById(postId, id);

        Assertions.assertEquals(mockEntity.getReactionType(), response.getReactionType());
    }

    @Test
    public void test_GetReactionById_NotFound() {
        String postId = "postId";
        String id = "invalidReactionId";

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(reactionRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ReactionNotFoundException.class, () -> reactionService.getReactionById(postId, id));
    }

    @Test
    public void test_UpdateReaction_Successful() {
        String userId = "userId";
        String postId = "postId";
        String id = "reactionId";
        ReactionRequest reactionRequest = mockReactionRequest();
        Reaction mockEntity = mockReactionEntity();

        Mockito.doNothing().when(postService).checkPostId(mockEntity.getPostId());
        Mockito.when(reactionRepository.findById(id)).thenReturn(Optional.of(mockEntity));
        Mockito.when(reactionRepository.save(mockEntity)).thenReturn(mockEntity);

        ReactionResponse response = reactionService.updateReaction(userId, postId, id, reactionRequest);

        Assertions.assertEquals(reactionRequest.getReactionType(), response.getReactionType());
    }

    @Test
    public void test_UpdateReaction_NotFound() {
        String userId = "userId";
        String postId = "postId";
        String id = "invalidReactionId";
        ReactionRequest reactionRequest = mockReactionRequest();

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(reactionRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ReactionNotFoundException.class, () -> reactionService.updateReaction(userId, postId, id, reactionRequest));
    }

    @Test
    public void test_DeleteReaction_Successful() {
        String postId = "postId";
        String reactionId = "reactionId";

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(reactionRepository.existsById(reactionId)).thenReturn(true);
        Mockito.doNothing().when(reactionRepository).deleteById(reactionId);

        Assertions.assertDoesNotThrow(() -> reactionService.deleteReaction(postId, reactionId));
    }

    @Test
    public void test_DeleteReaction_NotFound() {
        String postId = "postId";
        String reactionId = "invalidReactionId";

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(reactionRepository.existsById(reactionId)).thenReturn(false);

        Assertions.assertThrows(ReactionNotFoundException.class, () -> reactionService.deleteReaction(postId, reactionId));
    }

    @Test
    public void test_SearchReaction_Successful() {
        String postId = "postId";
        Reaction mockEntity = mockReactionEntity();

        Mockito.doNothing().when(postService).checkPostId(postId);
        Mockito.when(reactionRepository.findAllByPostId(postId)).thenReturn(List.of(mockEntity));

        List<ReactionResponse> responses = reactionService.searchReaction(postId);

        Assertions.assertFalse(responses.isEmpty());
        Assertions.assertEquals(mockEntity.getReactionType(), responses.get(0).getReactionType());
    }
}
