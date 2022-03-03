package dao;

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
import org.springblade.core.mp.base.BaseEntity;

/**
 * @author will
 */
@Data
@Builder
@ApiModel(description = "充值卡")
@EqualsAndHashCode(callSuper = true)
@TableName("haircut")
public class Rechargeable extends BaseEntity {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String name;

    @ApiModelProperty(value = "充值金额")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Double recharge_amount;

    @ApiModelProperty(value = "剩余金额")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Double remaining_amount;

    @ApiModelProperty(value = "备注")
    private String remark;





}
