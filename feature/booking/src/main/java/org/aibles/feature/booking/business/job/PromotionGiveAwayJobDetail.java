package org.aibles.feature.booking.business.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.adapter.repository.CustomerRepository;
import org.aibles.adapter.repository.EmailHelper;
import org.aibles.entity.Customer;
import org.aibles.feature.booking.adapter.repository.CustomerPromotionRepository;
import org.aibles.feature.booking.adapter.repository.PromotionRepository;
import org.aibles.feature.booking.entity.CustomerPromotion;
import org.aibles.feature.booking.entity.Promotion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PromotionGiveAwayJobDetail implements Job {
  private final PromotionRepository promotionRepository;
  private final CustomerPromotionRepository customerPromotionRepository;
  private final CustomerRepository customerRepository;
  private final EmailHelper emailHelper;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    log.info("execute promotion {}", jobExecutionContext.getJobDetail().getKey().getName());
    String promotionId = jobExecutionContext.getMergedJobDataMap().getString("jobId");
    Optional<Promotion> promotionOpt = promotionRepository.findById(promotionId);
    List<CustomerPromotion> customerPromotionList = new ArrayList<>(customerPromotionRepository.findAllCustomerByPromotionIdAndWinnerStatus(promotionId, false));
    if (promotionOpt.isPresent()) {
      Promotion promotion = promotionOpt.get();
      long quantity = promotion.getQuantity();
      CustomerPromotion customer;
      List<CustomerPromotion> luckyCustomerList = new ArrayList<>();
      while (quantity -- > 0) {
        customer = randomLuckyCustomer(customerPromotionList);
        customer.setWinner(true);
        luckyCustomerList.add(customer);
        customerPromotionList.remove(customer);
        if (customerPromotionList.isEmpty() || quantity == 0) {
          break;
        }
      }
      promotion.setQuantity(quantity);
      if (!luckyCustomerList.isEmpty()) {
        customerPromotionRepository.saveListCustomerPromotion(luckyCustomerList);
        promotionRepository.savePromotion(promotion);
        sendBatchEmail(luckyCustomerList, promotion);
      }
    }
  }

  private CustomerPromotion randomLuckyCustomer(List<CustomerPromotion> customerPromotionList) {
    Random random = new Random();
    int randomIndex = random.nextInt(customerPromotionList.size());
    return customerPromotionList.get(randomIndex);
  }

  private void sendBatchEmail(List<CustomerPromotion> customerPromotionList, Promotion promotion) {
    List<String> cusIdList = customerPromotionList.stream().map(c -> c.getCustomer().getId()).toList();
    List<Customer> customerList = customerRepository.findAllCustomerByListId(cusIdList);
    customerList.forEach(c -> {
      try {
        String subject = "AMS PROMOTION GIVE AWAY CONGRATULATIONS";
        String templateEmail = "promotion-giveaway-template.html";
        var param = new HashMap<String, Object>();
        param.put("code", promotion.getCode());
        param.put("description", promotion.getDescription());
        param.put("expiredDate", promotion.getExpiredDate());
        if (promotion.getDiscountPercent() != null && promotion.getDiscountPercent() > 0) {
          param.put("discountPercent", promotion.getDiscountPercent());
        }
        if (promotion.getPlusMiles() != null && promotion.getPlusMiles() > 0) {
          param.put("plusMiles", promotion.getPlusMiles());
        }
        emailHelper.send(subject, c.getEmail(), templateEmail, param);
      } catch (Exception e) {
        log.error("send Email to {} failed: {}", c.getEmail(), e.getMessage());
      }
    });
  }
}
