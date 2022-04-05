package com.barber.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.cglib.CglibUtil;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.barber.dao.MemberUser;
import com.barber.excel.HaircutListener;
import com.barber.excel.MemberUserImport;
import com.barber.excel.MemberUserListener;
import com.barber.service.ExcelService;
import com.barber.service.MemberUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.excel.EasyExcelFactory.read;
import static org.springframework.util.StringUtils.endsWithIgnoreCase;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author will
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private MemberUserService memberUserService;

    @Override
    public Map<String, Object> cacheImportHaircut(MultipartFile file, HaircutListener importListener) {

        return null;
    }

    @Override
    public Map<String, Object> cacheImportMemberUser(MultipartFile file, MemberUserListener importListener) {
        Map<String, Object> result = new HashMap<>(6);
        String filename = file.getOriginalFilename();
        if (isEmpty(filename)) {
            result.put("exceptionMsg", "请上传文件!");
            return result;
        }
        boolean check = !endsWithIgnoreCase(filename, ".xls") && !endsWithIgnoreCase(filename, ".xlsx");
        if (check) {
            result.put("exceptionMsg", "请上传正确的excel文件!");
            return result;
        }
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
            ExcelReaderBuilder builder = read(inputStream, importListener).headRowNumber(1);
            builder.doReadAll();
            if (importListener.isSuccess()) {
                List<MemberUserImport> list = importListener.getList();
                //将导入的数据进行赋值
                ArrayList<MemberUser> users = new ArrayList<>();
                for (MemberUserImport memberUserImport : list) {
                    users.add(MemberUser.builder().memberUserName(memberUserImport.getMemberUserName())
                            .phoneNum(memberUserImport.getPhoneNum())
                            .build());
                }
                memberUserService.saveBatch(users);
            }
            result.put("successCount", importListener.getSuccessCount());
            result.put("errorCount", importListener.getErrorCount());
            result.put("warningPrompts", importListener.getWarningPrompts());
            result.put("errorPrompts", importListener.getErrorPrompts());
            result.put("exceptionMsg", importListener.getExceptionMsg());
        } catch (IOException e) {
            e.printStackTrace();
            result.put("exceptionMsg", "上传失败!");
            return result;
        }
        return result;
    }
}
