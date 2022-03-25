package com.barber.service;

import com.barber.excel.HaircutListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author will
 */
public interface ExcelService {
    /**
     * 导入数据
     * @param file 文件
     * @param haircutListener 监听器
     * @return 错误信息
     */
    Map<String, Object> cacheImportPolicy(MultipartFile file, HaircutListener haircutListener);
}
