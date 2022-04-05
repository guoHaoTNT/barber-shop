package com.barber.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;
import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

/**
 * @author will
 */
@Data
@Builder
@ApiModel(description = "充值卡")
@EqualsAndHashCode(callSuper = true)
@TableName("rechargeable")
public class Rechargeable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    private Long id;

    @ApiModelProperty(value = "会员id")
    @TableField(updateStrategy = IGNORED, whereStrategy = NOT_EMPTY)
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
