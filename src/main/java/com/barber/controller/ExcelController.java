package com.barber.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.barber.excel.HaircutListener;
import com.barber.excel.MemberUserListener;
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

    private final ExcelService excelService;

    /**
     * 会员导入
     */
    @PostMapping("/import/memberUser")
    @ApiOperation(value = "会员导入", notes = "会员导入")
    public R<Map<String, Object>> importMemberUser(MultipartFile file){
        Map<String, Object> result  = excelService.cacheImportMemberUser(file,new MemberUserListener());
        Object exceptionMsg = result.get("exceptionMsg");
        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }

    /**
     * 剪发卡读入
     */
    @PostMapping("/import/haircut")
    @ApiOperation(value = "剪发卡读入", notes = "剪发卡读入")
    public R<Map<String, Object>> importHaircut(MultipartFile file){
        Map<String, Object> result  = excelService.cacheImportHaircut(file,new HaircutListener());
        Object exceptionMsg = result.get("exceptionMsg");
        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }


}
