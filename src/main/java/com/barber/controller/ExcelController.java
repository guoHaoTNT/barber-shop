package com.barber.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.barber.excel.HaircutListener;
import com.barber.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/excel")
@AllArgsConstructor
@Api(value = "excel表格导入导出", tags = "excel表格导入导出")
public class ExcelController {
    @Autowired
    private final ExcelService excelService;

    /**
     * 费率表导入
     * 表头需要是五行，数据从第六行开始
     */
    @PostMapping("/import")
    @ApiOperation(value = "首次费率表导入", notes = "首次费率表导入")
    public R<Map<String, Object>> importPolicy(MultipartFile file){
        Map<String, Object> result  = excelService.cacheImportPolicy(file,new HaircutListener());
        Object exceptionMsg = result.get("exceptionMsg");
        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }


}
