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
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.IGNORED;
import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

/**
 *
 * @author will
 */
@Data
@ApiModel(description = "剪发卡")
@EqualsAndHashCode(callSuper = true)
@TableName("haircut")
public class Haircut extends BaseEntity {

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
    private Integer phoneNum;


    @ApiModelProperty(value = "剪发卡充值金额")
    private Double haircutRechargeAmount;

    @ApiModelProperty(value = "剪发总次数")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @NotBlank
    private Integer totalTime;

    @ApiModelProperty(value = "剩余剪发总次数")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Integer remainingTime;

    @ApiModelProperty(value = "是否有效 1-是 0-否")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Integer valid;

    @ApiModelProperty(value = "备注")
    private String remark;

}
