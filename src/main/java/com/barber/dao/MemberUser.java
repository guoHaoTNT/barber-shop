package com.barber.dao;

import com.baomidou.mybatisplus.annotation.IdType;
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

import java.util.Date;


/**
 * @author will
 */
@Data
@Builder
@ApiModel(description = "会员表")
@EqualsAndHashCode(callSuper = true)
@TableName("member_user")
public class MemberUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    private Long id;

    @ApiModelProperty(value = "会员姓名")
    private String memberUserName;

    @ApiModelProperty(value = "会员手机号")
    private String phoneNum;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value ="家庭住址")
    private String address;

    @ApiModelProperty(value = "备注信息")
    private String remark;

}
