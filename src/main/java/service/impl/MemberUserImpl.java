package service.impl;

import dao.MemberUser;
import lombok.extern.slf4j.Slf4j;
import mapper.MemberUserMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import service.MemberUserService;

/**
 * @author will
 */
@Slf4j
@Service
public class MemberUserImpl extends BaseServiceImpl<MemberUserMapper, MemberUser> implements MemberUserService {
}
