package com.zl.controller;

import com.zl.pojo.CommonResult;
import com.zl.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController
{

    //public final static String PAYMENT_URL = "http://localhost:8001";
    public final static String PAYMENT_URL = "http://COULD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/insert")
    public CommonResult<Payment> insert(Payment payment)
    {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/insert", payment, CommonResult.class);
    }

    @GetMapping("/order/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id)
    {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}
