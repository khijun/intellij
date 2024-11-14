package edu.du.sb1114_restful;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class MyRestController {
    @GetMapping("/hello")
    @ResponseBody
    public LocalDateTime hello() {
        return LocalDateTime.now();
    }

    @GetMapping("/account/{name}")
    @ResponseBody
    public String account(@PathVariable String name) {
        log.info("Account name is {}", name);
        return name;
    }

//    @PostMapping("/account")
//    public Account account(@RequestBody Account account) {
//        log.info(account.toString());
//        return account;
//    }
//
    @PostMapping("/account")
    public ResponseEntity<?> account(@RequestBody Account account) {
        log.info(account.toString());
        return ResponseEntity.notFound().build();
    }

}
