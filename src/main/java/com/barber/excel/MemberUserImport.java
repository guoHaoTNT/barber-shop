package com.barber.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author guohao
 */
@Builder
@Data
public class MemberUserImport implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String memberUserName;
    @ApiModelProperty(value = "手机号")
    private String phoneNum;
}
