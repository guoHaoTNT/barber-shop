package com.barber.excel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author will
 * @date 2022/5/4 18:43
 */
@Builder
@Data
public class HaircutImport implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "会员姓名")
    private String memberUserName;

    @ApiModelProperty(value = "会员手机号")
    private String phoneNum;


    @ApiModelProperty(value = "剪发卡充值金额")
    private Double haircutRechargeAmount;

    @ApiModelProperty(value = "剪发总次数")
    @NotBlank
    private Integer totalTime;

    @ApiModelProperty(value = "剩余剪发总次数")
    private Integer remainingTime;
}
