package com.barber.service.impl;

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
}
