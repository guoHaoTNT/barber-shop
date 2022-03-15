package com.barber.vo;

import com.barber.dao.Haircut;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author will
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@ApiModel(value = "剪发卡查询vo", description = "剪发卡查询vo")
public class HaircutVo extends Haircut {
}
