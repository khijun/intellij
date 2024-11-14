package edu.du.sb1114_restful;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class MyController {
    @GetMapping("/")
    public String index() {
        return "/index";
    }
}
