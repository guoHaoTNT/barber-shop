package com.barber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.barber.dao.Rechargeable;

/**
 * @author will
 */
public interface RechargeableService extends IService<Rechargeable> {
    /**
     * 分页查询充值信息
     * @param rechargeablePage
     * @param rechargeable
     * @return
     */
    IPage<Rechargeable> queryRechargeableList(Page<Rechargeable> rechargeablePage, Rechargeable rechargeable);
}
