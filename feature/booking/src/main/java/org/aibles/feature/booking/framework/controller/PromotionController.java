package org.aibles.feature.booking.framework.controller;

import static org.aibles.util.SecurityUtil.getCredential;

import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.GetAllPromotionResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.usecase.ActivePromotionUseCase;
import org.aibles.feature.booking.business.usecase.DeactivePromotionUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.PromotionValidationRequest;
import org.aibles.feature.booking.business.dto.response.PromotionDetailResponse;
import org.aibles.feature.booking.business.dto.response.PromotionResponse;
import org.aibles.feature.booking.business.dto.response.ValidatePromotionCodeResponse;
import org.aibles.feature.booking.business.usecase.GetDetailPromotionUseCase;
import org.aibles.feature.booking.business.usecase.GetValidPromotionUseCase;
import org.aibles.feature.booking.business.usecase.GetAllPromotionUseCase;
import org.aibles.feature.booking.business.usecase.ImportPromotionUseCase;
import org.aibles.feature.booking.business.usecase.RegisterPromotionUseCase;
import org.aibles.feature.booking.framework.controller.dto.request.PagingReq;
import org.aibles.feature.booking.business.usecase.ValidatePromotionUseCase;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.quartz.SchedulerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PromotionController {

  private final GetValidPromotionUseCase getValidPromotionUseCase;
  private final ImportPromotionUseCase importPromotionUseCase;
  private final RegisterPromotionUseCase registerPromotionUseCase;
  private final GetDetailPromotionUseCase getDetailPromotionUseCase;
  private final ValidatePromotionUseCase validatePromotionUseCase;
  private final GetAllPromotionUseCase getAllPromotionUseCase;
  private final DeactivePromotionUseCase deactivePromotionUseCase;
  private final ActivePromotionUseCase activePromotionUseCase;

  @GetMapping("/customers/self/promotions")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PagingResponse<PromotionResponse>> getValidPromotion(
      @Valid PagingReq pagingReq,
      @RequestParam(required = false) String[] sortBy
  ) {
    log.info("(getValidPromotion)");
    PagingRequest pagingRequest =
        new PagingRequest(pagingReq.getNum(), pagingReq.getPage(), sortBy);

    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
        getValidPromotionUseCase.invoke(pagingRequest, getCredential()));
  }

  @GetMapping("/promotions")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PagingResponse<GetAllPromotionResponse>> getAllPromotion(
          @Valid PagingReq pagingReq,
          @RequestParam(required = false) String[] sortBy
  ) {
    log.info("(getAllPromotion)");
    PagingRequest pagingRequest =
            new PagingRequest(pagingReq.getNum(), pagingReq.getPage(), sortBy);

    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(),
            getAllPromotionUseCase.invoke(pagingRequest));
  }

  @PostMapping("/promotions/import")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> importPromotions(@RequestPart(name = "file") MultipartFile file)
      throws IOException {
    importPromotionUseCase.invoke(file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @PostMapping("/promotions/{promotions_id}/registration")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> registerPromotions(
      @PathVariable(value = "promotions_id") String promotionsId) {
    registerPromotionUseCase.invoke(promotionsId, getCredential());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @GetMapping("/promotions/{id}")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<PromotionDetailResponse> getDetailPromotion(@PathVariable(value = "id") String id) {
    return new  BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), getDetailPromotionUseCase.invoke(id, getCredential()));
  }

  @PostMapping("/promotions/validation")
  public BaseResponse<ValidatePromotionCodeResponse> validatePromotionCode(@Valid @RequestBody PromotionValidationRequest request) {
    return new  BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), validatePromotionUseCase.invoke(getCredential(), request.getCode()));
  }

  @PatchMapping("/promotions/{promotion_id}/deactivation")
  public BaseResponse<Void> deactivePromotion(@PathVariable(value = "promotion_id") String promotionId)
      throws SchedulerException {
    deactivePromotionUseCase.invoke(promotionId);
    return new  BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

  @PatchMapping("/promotions/{promotion_id}/activation")
  public BaseResponse<Void> activePromotion(@PathVariable(value = "promotion_id") String promotionId)
      throws SchedulerException {
    activePromotionUseCase.invoke(promotionId);
    return new  BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }

}
