package org.aibles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("======AMS-PROJECT======");
    log.info("======Contributor======");
    log.info("======   DatTV   ======");
    log.info("======  OanhDTK  ======");
    log.info("======   DaiNT   ======");
    log.info("======   LinhTG  ======");
    log.info("======   AnhDD   ======");
    log.info("======   DatHD   ======");
    log.info("======   AnhNHS  ======");
  }
}