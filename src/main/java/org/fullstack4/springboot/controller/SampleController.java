package org.fullstack4.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("msg", "HELLO==");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        List<String> list = Arrays.asList("AAA", "BBB", "CCC");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        List<String> strList = IntStream.range(1,10)
                .mapToObj(i->"Data " + i)
                .collect(Collectors.toList());
        model.addAttribute("list", strList);

        Map<String, Object> map = new HashMap<>();
        map.put("A", "aaa");
        map.put("B", "bbb");
        map.put("C", "ccc");
        model.addAttribute("map", map);

        SampleDTO dto = new SampleDTO();
        dto.p1 = "p----111";
        dto.p2 = "p----222";
        dto.p3 = "p----333";
        model.addAttribute("dto", dto);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model) {

    }

    class SampleDTO {
        public String p1;
        public String p2;
        public String p3;

        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
    }
}
