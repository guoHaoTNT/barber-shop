package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.Rechargeable;
import lombok.extern.slf4j.Slf4j;
import mapper.RechargeableMapper;
import org.springframework.stereotype.Service;
import service.RechargeableService;

/**
 * @author will
 */
@Slf4j
@Service
public class RechargeableServiceImpl extends ServiceImpl<RechargeableMapper, Rechargeable> implements RechargeableService {
}
