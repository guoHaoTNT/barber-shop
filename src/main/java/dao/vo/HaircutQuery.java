package dao.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author will
 */
@Data
@ApiModel(description = "剪发卡查询条件")
public class HaircutQuery {

    @ApiModelProperty(value = "手机号")
    @JsonSerialize(nullsUsing = NullSerializer.class)
    private Integer phoneNum;

    @ApiModelProperty(value = "用户姓名")
    private String MemberUserName;
}
