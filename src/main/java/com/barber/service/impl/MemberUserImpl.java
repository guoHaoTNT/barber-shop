package com.barber.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.barber.dao.MemberUser;
import com.barber.mapper.MemberUserMapper;
import com.barber.service.MemberUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author will
 */
@Slf4j
@Service
public class MemberUserImpl extends ServiceImpl<MemberUserMapper, MemberUser> implements MemberUserService {


    @Override
    public IPage<MemberUser> queryHaircutList(Page<MemberUser> page, MemberUser memberUser) {
        Page<MemberUser> memberUserPage = baseMapper.selectPage(page, Wrappers.<MemberUser>lambdaQuery()
                .like(StrUtil.isNotEmpty(memberUser.getMemberUserName()),
                        MemberUser::getMemberUserName, memberUser.getMemberUserName())
                .like(StrUtil.isNotEmpty(memberUser.getPhoneNum()),
                        MemberUser::getPhoneNum, memberUser.getPhoneNum())
                .orderByDesc(MemberUser::getUpdateTime));
        return memberUserPage;
    }
}
