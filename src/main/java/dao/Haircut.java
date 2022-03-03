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

import javax.validation.constraints.NotBlank;

/**
 *
 * @author will
 */
@Data
@ApiModel(description = "剪发卡")
@EqualsAndHashCode(callSuper = true)
@TableName("haircut")
public class Haircut extends BaseEntity {

    @ApiModelProperty(value = "ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullSerializer.class)
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "电话号码")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @NotBlank
    private Integer phoneNum;

    @ApiModelProperty(value = "剪发总次数")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @NotBlank
    private Integer totalTime;

    @ApiModelProperty(value = "剩余剪发总次数")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Integer remainingTime;

    @ApiModelProperty(value = "备注")
    private String remark;

}
