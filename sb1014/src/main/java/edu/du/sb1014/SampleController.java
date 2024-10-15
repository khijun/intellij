package edu.du.sb1014;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("")
    public String[] hello() {
        return new String[]{"Hello", "World"};
    }
}
