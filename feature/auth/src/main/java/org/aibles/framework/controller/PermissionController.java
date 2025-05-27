package org.aibles.framework.controller;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.bussiness.usecase.ImportPermissionUseCase;
import org.aibles.framework.controller.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/permissions")
@Slf4j
@RequiredArgsConstructor
public class PermissionController {
  private final ImportPermissionUseCase importPermissionUseCase;

  @PostMapping("/import")
  BaseResponse<Void> importExcel(@RequestBody MultipartFile file) throws IOException {
    importPermissionUseCase.invoke(file.getInputStream());
    return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), System.currentTimeMillis(), null);
  }
}
