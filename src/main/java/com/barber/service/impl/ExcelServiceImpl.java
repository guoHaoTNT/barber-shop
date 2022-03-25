package com.barber.service.impl;

import com.barber.excel.HaircutListener;
import com.barber.service.ExcelService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author will
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public Map<String, Object> cacheImportPolicy(MultipartFile file, HaircutListener haircutListener) {

        return null;
    }
}
