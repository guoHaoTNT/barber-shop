package controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import dao.Haircut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import service.HaircutService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/haircut")
@AllArgsConstructor
@Api(value = "剪发卡", tags = "剪发卡")
public class HaircutController {

    private final HaircutService haircutService;

    @ApiOperation(value = "会员剪发卡充值")
    @PostMapping("/recharge")
    public R<String> recharge(@Valid @RequestBody Haircut haircut){
        haircutService.save(haircut);
        return R.data(haircut.getId().toString());
    }

    @ApiOperation(value = "根据手机号查出会员剪发卡信息")
    @GetMapping("/rechargeInfoByPhoneNum/{phone_num}")
    public R<List<Haircut>> rechargeInfoByPhoneNum(@PathVariable("phone_num")Integer phoneNum){
        List<Haircut> haircuts = haircutService.getBaseMapper().selectList(Wrappers.<Haircut>lambdaQuery()
                .eq(Haircut::getPhoneNum, phoneNum));
        return R.data(haircuts);
    }

    @ApiOperation(value = "剪发卡消费，修改操作")
    @PostMapping("/consumption")
    public R<Boolean> consumption(@Valid @RequestBody Haircut haircut){
        return R.data(null);
    }

    @ApiModelProperty(value = "查看所有会员信息")
    @GetMapping("/all_member")
    public R<List<Haircut>> allMember(){
       return R.data(haircutService.getBaseMapper().selectList(null));
    }
}
