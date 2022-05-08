package com.barber.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.api.R;
import com.barber.dao.MemberUser;
import com.barber.excel.HaircutListener;
import com.barber.excel.MemberUserExport;
import com.barber.excel.MemberUserListener;
import com.barber.excel.RechargeableListener;
import com.barber.service.ExcelService;
import com.barber.service.MemberUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.net.HttpHeaders.CONTENT_DISPOSITION;
import static org.apache.commons.codec.Charsets.UTF_8;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/excel")
@AllArgsConstructor
@Api(value = "excel表格导入导出", tags = "excel表格导入导出")
@CrossOrigin
public class ExcelController {

    private final ExcelService excelService;
    private final MemberUserService memberUserService;

    static final String EXCEL_SUFFIX = ".xlsx";

    /**
     * 会员导入
     */
    @PostMapping("/import/memberUser")
    @ApiOperation(value = "会员导入", notes = "会员导入")
    public R<Map<String, Object>> importMemberUser(MultipartFile file) {
        Map<String, Object> result = excelService.cacheImportMemberUser(file, new MemberUserListener());
        Object exceptionMsg = result.get("exceptionMsg");

        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }

    /**
     * 剪发卡读入
     */
    @PostMapping("/import/haircut")
    @ApiOperation(value = "剪发卡读入", notes = "剪发卡读入")
    public R<Map<String, Object>> importHaircut(MultipartFile file) {
        Map<String, Object> result = excelService.cacheImportHaircut(file, new HaircutListener());
        Object exceptionMsg = result.get("exceptionMsg");
        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }

    /**
     *  充值卡读入
     */
    @PostMapping("/import/rechargeable")
    @ApiOperation(value = "剪发卡读入", notes = "剪发卡读入")
    public R<Map<String, Object>> importRechargeable(MultipartFile file) {
        Map<String, Object> result = excelService.cacheImportRechargeable(file, new RechargeableListener());
        Object exceptionMsg = result.get("exceptionMsg");
        return null == result.get("exceptionMsg") ? R.ok(result) : R.failed(exceptionMsg.toString());
    }

    /**
     * 导出全部用户信息
     */
    @PostMapping(value = "/exportUser}")
    @ApiOperation(value = "导出全部用户信息", notes = "导出全部用户信息")
    public void exportContract(HttpServletResponse response) throws Exception {
        //检验，删除的受否也查询出来
        List<MemberUser> userList = memberUserService.list(null);
        List<MemberUserExport> collect = userList.stream().map(c -> invoiceExcelHandler(c)).collect(Collectors.toList());
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(UTF_8.name());
        response.setHeader(CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(DateUtil.today() + "用户信息", UTF_8.name()) + EXCEL_SUFFIX);
        EasyExcel.write(response.getOutputStream(), MemberUserExport.class).sheet("用户信息").doWrite(collect);
    }

    private MemberUserExport invoiceExcelHandler(MemberUser vo) {
        MemberUserExport excel = new MemberUserExport();
        BeanUtils.copyProperties(vo, excel);
        return excel;
    }

}
