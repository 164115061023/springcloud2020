package com.zl.controller;

import com.zl.pojo.CommonResult;
import com.zl.pojo.Payment;
import com.zl.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/payment/insert")
    public CommonResult<Payment> insert(@RequestBody Payment payment){
        int result = paymentService.insert(payment);
        log.info("****插入"+result);
        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(444, "插入失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询"+payment);
        if (payment != null) {
            return new CommonResult<Payment>(200, "查询成功"+serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败", null);
        }
    }

    @GetMapping("/payment/serverPort")
    public String getServerPort() {
        return serverPort;
    }


    @GetMapping("/payment/timeOut")
    public String timeOut() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName();
    }

    @GetMapping("/payment/OK")
    public String OK() {

        return Thread.currentThread().getName()+ " O(^_^O)";
    }

}