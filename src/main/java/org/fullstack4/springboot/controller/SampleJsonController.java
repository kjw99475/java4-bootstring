package org.fullstack4.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class SampleJsonController {
    @GetMapping("/arrhello")
    public String[] arrhello() {
        log.info("===========================");
        return new String[]{"AAA", "BBB", "CCC"};
    }
}
