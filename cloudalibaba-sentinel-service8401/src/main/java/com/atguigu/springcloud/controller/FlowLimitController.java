package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        log.info(Thread.currentThread().getName() + " : " + "testA");
        return "******testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "******testB";
    }
    @GetMapping("/testC")
    public String testC(){
        log.info("TestC: Exception Rate");
        int age = 10 / 0;
        return "******testC";
    }
    @GetMapping("/testD")
    public String testD(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("TestD: testing RT");
        return "******testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2){
        return "*****testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "*****deal_testHotKey";
    }
}
