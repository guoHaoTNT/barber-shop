package com.barber.service.impl;

import com.barber.dao.MemberUser;
import lombok.extern.slf4j.Slf4j;
import com.barber.mapper.MemberUserMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.barber.service.MemberUserService;

/**
 * @author will
 */
@Slf4j
@Service
public class MemberUserImpl extends BaseServiceImpl<MemberUserMapper, MemberUser> implements MemberUserService {
}
