package com.zl.controller;

import com.zl.lb.LoadBalance;
import com.zl.pojo.CommonResult;
import com.zl.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController
{

    //public final static String PAYMENT_URL = "http://localhost:8001";
    public final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalance loadBalance;

    @Autowired
    private DiscoveryClient discoveryClient;

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


    @GetMapping("/order/payment/serverPort")
    public String getServerPort() {


        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        ServiceInstance serviceInstance = loadBalance.getServiceInstance(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/serverPort", String.class);
    }

}
