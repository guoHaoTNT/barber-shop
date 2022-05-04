package com.barber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.barber.dao.Haircut;

/**
 * 剪发卡
 * @author will
 */
public interface HaircutService extends IService<Haircut> {
    /**
     * 剪发卡分页列表
     * @param haircutPage
     * @param haircut
     * @return
     */
    IPage<Haircut> queryHaircutList(Page<Haircut> haircutPage, Haircut haircut);
}
