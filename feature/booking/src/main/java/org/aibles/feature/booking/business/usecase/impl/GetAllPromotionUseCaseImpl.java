package org.aibles.feature.booking.business.usecase.impl;

import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.business.dto.request.PagingRequest;
import org.aibles.feature.booking.business.dto.response.GetAllPromotionResponse;
import org.aibles.feature.booking.business.dto.response.PagingResponse;
import org.aibles.feature.booking.business.usecase.GetAllPromotionUseCase;
import org.aibles.feature.booking.entity.Promotion;

import java.util.List;

public class GetAllPromotionUseCaseImpl implements GetAllPromotionUseCase {
    private final PromotionRepository promotionRepository;

    public GetAllPromotionUseCaseImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PagingResponse<GetAllPromotionResponse> invoke(PagingRequest request) {
        PagingResponse<Promotion> pagePromotion = promotionRepository.getValidPromotion(request);
        List<GetAllPromotionResponse> pageData = pagePromotion.getPageData().stream().map(GetAllPromotionResponse::from).toList();
        return new PagingResponse<>(
                pagePromotion.getNum() + 1,
                pagePromotion.getSize(),
                pagePromotion.getTotal(),
                pageData
        );
    }
}
