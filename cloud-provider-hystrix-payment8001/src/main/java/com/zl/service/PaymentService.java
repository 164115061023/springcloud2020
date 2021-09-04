package com.zl.service;

import com.zl.pojo.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int insert(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
