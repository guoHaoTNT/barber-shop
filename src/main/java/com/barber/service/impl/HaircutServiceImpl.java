package com.barber.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.barber.dao.Haircut;
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

}
