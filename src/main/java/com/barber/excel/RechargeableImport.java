package com.barber.excel;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author will
 * @date 2022/5/7
 */
@Builder
@Data
public class RechargeableImport implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "会员id")
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    private Long memberUserId;

    @ApiModelProperty(value = "会员姓名")
    private String memberUserName;

    @ApiModelProperty(value = "会员手机号")
    private String phoneNum;


    @ApiModelProperty(value = "充值金额")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Double rechargeAmount;

    @ApiModelProperty(value = "剩余金额")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Double remainingAmount;

    @ApiModelProperty(value = "备注")
    private String remark;
}
