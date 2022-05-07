package com.barber.service;

import com.barber.excel.HaircutListener;
import com.barber.excel.MemberUserListener;
import com.barber.excel.RechargeableListener;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author will
 */
public interface ExcelService {
    /**
     * 剪发卡导入
     * @param file 文件
     * @param haircutListener 监听器
     * @return 错误信息
     */
    Map<String, Object> cacheImportHaircut(MultipartFile file, HaircutListener haircutListener);

    /**
     * 会员导入
     * @param file 文件
     * @param memberUserListener 监听器
     * @return
     */
    Map<String, Object> cacheImportMemberUser(MultipartFile file, MemberUserListener memberUserListener);

    /**
     * 充值卡导入
     * @param file
     * @param rechargeableListener
     * @return
     */
    Map<String, Object> cacheImportRechargeable(MultipartFile file, RechargeableListener rechargeableListener);
}
