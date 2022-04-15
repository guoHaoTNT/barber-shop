package com.barber.excel;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author will
 */
@ColumnWidth(25)
@HeadRowHeight(30)
@ContentRowHeight(18)
@ExcelIgnoreUnannotated
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MemberUserExport {

    @ExcelProperty(value = "开票申请序号", index = 0)
    private String invoiceNo;

    @ExcelProperty(value = "ID", index = 1)
    private Long id;

    @ExcelProperty(value = "会员姓名", index = 2)
    private String memberUserName;


    @ExcelProperty(value = "会员手机号", index = 3)
    private String phoneNum;


    @ExcelProperty(value = "生日", index = 4)
    private Date birthday;

    @ExcelProperty(value = "家庭住址", index = 5)
    private String address;

    @ExcelProperty(value = "备注信息", index = 6)
    private String remark;

    @ApiModelProperty("是否已删除")
    @ExcelProperty(value = "是否已删除", index = 0)
    private Integer isDeleted;
}
