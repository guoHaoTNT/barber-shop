package com.barber.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
