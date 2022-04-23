package com.barber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.barber.dao.MemberUser;

/**
 * @author will
 */
public interface MemberUserService extends IService<MemberUser> {
    /**
     * 会员管理分页列表
     * @param page 分页信息
     * @param memberUser 条件
     * @return 数据
     */
    IPage<MemberUser> queryHaircutList(Page<MemberUser> page, MemberUser memberUser);
}
