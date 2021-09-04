package com.zl.lb;

import org.springframework.cloud.client.ServiceInstance;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalance{

    AtomicInteger nextAtomic = new AtomicInteger(0);

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {

        if (serviceInstanceList!=null&&serviceInstanceList.size()>0){

            return serviceInstanceList.get(getCountInteger()%serviceInstanceList.size());
        }

        return null;

    }

    int getCountInteger(){
        int count = nextAtomic.get();

        int next = count>=Integer.MAX_VALUE?0:nextAtomic.incrementAndGet();
        System.out.println(next);
        return next;

    }
}
