package com.pd.order.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pd.oreder.IOrderService;
import pd.pay.IPayService;

import java.util.UUID;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-5-25 16:03
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Reference
    IPayService payService;
    @Override
    public String createOrder(String s) {
        System.out.println("开始下单：");
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单号为： "+ orderId);
        return payService.payFor(orderId);
    }
}
