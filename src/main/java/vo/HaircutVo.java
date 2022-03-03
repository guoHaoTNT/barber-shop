package vo;

import dao.Haircut;
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
@ApiModel(value = "RegisterVO对象", description = "注册表对象")
public class HaircutVo extends Haircut {
}
