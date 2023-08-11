package com.koubeisi.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author koubeisi
 * @since 2023/8/4 10:47
 */
@RestController
public class HelloController {



    @GetMapping("/hello")
    public Hello hello() {
        var hello = new Hello();
        hello.setDate(new Date());
        hello.setLocalDate(LocalDate.now());
        hello.setLocalDateTime(LocalDateTime.now());
        return hello;
    }


    @PostMapping("/hello")
    public void hello(@RequestBody Hello hello) {
        System.out.println(hello.toString());
    }


    @Data
    public static class Hello{
        private LocalDate localDate;

//        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;

        private Date date;
    }
}
