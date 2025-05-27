package org.aibles.feature.booking.framework.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.feature.booking.business.usecase.ImportFareUseCase;
import org.aibles.feature.booking.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/fares")
@RequiredArgsConstructor
@Slf4j
public class FareController {

  private final ImportFareUseCase importFareUseCase;

  @PostMapping("/import")
  @ResponseStatus(HttpStatus.OK)
  public BaseResponse<Void> importFare(@RequestBody MultipartFile file) throws IOException {
    importFareUseCase.invoke(file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}
