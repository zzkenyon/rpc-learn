package com.pd.pay.impl;

import org.apache.dubbo.config.annotation.Service;
import pd.pay.IPayService;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-5-25 16:30
 */
@Service
public class PayServiceImpl implements IPayService {
    @Override
    public String payFor(String s) {
        System.out.println("开始支付");
        return "下单成功，完成支付！";
    }
}
