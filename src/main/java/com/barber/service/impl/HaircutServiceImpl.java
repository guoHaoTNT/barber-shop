package com.barber.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.barber.dao.Haircut;
import com.barber.dao.MemberUser;
import lombok.extern.slf4j.Slf4j;
import com.barber.mapper.HaircutMapper;
import org.springframework.stereotype.Service;
import com.barber.service.HaircutService;

/**
 * @author will
 */
@Slf4j
@Service
public class HaircutServiceImpl extends ServiceImpl<HaircutMapper, Haircut> implements HaircutService {

    @Override
    public IPage<Haircut> queryHaircutList(Page<Haircut> page, Haircut haircut) {
        Page<Haircut> haircutPage = baseMapper.selectPage(page, Wrappers.<Haircut>lambdaQuery()
                .like(StrUtil.isNotEmpty(haircut.getMemberUserName()),
                        Haircut::getMemberUserName, haircut.getMemberUserName())
                .like(StrUtil.isNotEmpty(haircut.getPhoneNum()),
                        Haircut::getPhoneNum, haircut.getPhoneNum())
                .orderByDesc(Haircut::getUpdateTime));
        return haircutPage;
    }
}
