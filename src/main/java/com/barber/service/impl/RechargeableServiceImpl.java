package com.barber.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.barber.dao.MemberUser;
import com.barber.dao.Rechargeable;
import lombok.extern.slf4j.Slf4j;
import com.barber.mapper.RechargeableMapper;
import org.springframework.stereotype.Service;
import com.barber.service.RechargeableService;

/**
 * @author will
 */
@Slf4j
@Service
public class RechargeableServiceImpl extends ServiceImpl<RechargeableMapper, Rechargeable> implements RechargeableService {
    @Override
    public IPage<Rechargeable> queryRechargeableList(Page<Rechargeable> page, Rechargeable rechargeable) {
        Page<Rechargeable> rechargeablePage = baseMapper.selectPage(page, Wrappers.<Rechargeable>lambdaQuery()
                .like(StrUtil.isNotEmpty(rechargeable.getMemberUserName()),
                        Rechargeable::getMemberUserName, rechargeable.getMemberUserName())
                .like(StrUtil.isNotEmpty(rechargeable.getPhoneNum()),
                        Rechargeable::getPhoneNum, rechargeable.getPhoneNum())
                .orderByDesc(Rechargeable::getUpdateTime));
        return rechargeablePage;
    }
}
