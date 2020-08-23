package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private static String[] uuids = new String[]{
            "21b57f1b-56d1-410c-a4ab-9ffb255961f2",
            "0d833249-4774-4fe7-a40c-f567d07a8419",
            "61d67dd5-0e98-4e88-88f0-cf683b2fae18",
            "f1651b3e-3774-4117-bdae-f4d0c4059a39",
            "c69d876a-6cae-4a6e-9333-1ad42bc7bb34",
            "42ee56c1-96ae-4466-87e9-aee881d1b810",
            "155fae9c-3a34-4c6a-9efc-4c0eaf3043e9",
            "5e0c166c-1820-42bf-91e5-e0a9fb15968d",
            "b7a6608b-aa05-45cc-b6c6-c2e3198ce683",
            "cc48b26a-e84e-4e73-af5c-3f4868da24b8"};

    public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();
    static {
        for (int i = 0; i < uuids.length; i++) {
            hashMap.put((long)i + 1, new Payment((long)i + 1, uuids[i]));
        }
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, "from mysql, serverPort: " + serverPort, payment);
        return result;
    }
}
