package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import dao.Haircut;
import lombok.extern.slf4j.Slf4j;
import mapper.HaircutMapper;
import org.springframework.stereotype.Service;
import service.HaircutService;

/**
 * @author will
 */
@Slf4j
@Service
public class HaircutServiceImpl extends ServiceImpl<HaircutMapper, Haircut> implements HaircutService {

}
