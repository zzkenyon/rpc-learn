package com.pd.pay;

/**
 * @description: rpc-server
 * @author: zhaozhengkang
 * @date: 2020-05-24 10:42
 */
public class PayServiceImpl implements IPayService {
    @Override
    public String pay(String info) {
        System.out.println("pay service is called");
        return "payed for " + info;
    }
}
