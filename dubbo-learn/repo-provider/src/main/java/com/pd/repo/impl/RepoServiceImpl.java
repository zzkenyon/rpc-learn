package com.pd.repo.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import pd.oreder.IOrderService;
import pd.repo.IRepoService;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020-5-25 16:34
 */
@Service
public class RepoServiceImpl implements IRepoService {
    @Reference
    IOrderService orderService;
    @Override
    public String minusRepo(String s) {
        System.out.println("开始扣减库存");
        System.out.println("库存-1");
        return orderService.createOrder("zzk");
    }
}
