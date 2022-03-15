package com.barber.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.barber.dao.Haircut;
import com.barber.dao.vo.HaircutQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.barber.service.HaircutService;
import com.barber.service.MemberUserService;

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
    private final MemberUserService memberUserService;

    @ApiOperation(value = "会员剪发卡充值/保存/修改")
    @PostMapping("/recharge")
    public R<String> recharge(@Valid @RequestBody Haircut haircut){
        haircutService.save(haircut);
        return R.data(haircut.getId().toString());
    }

    @ApiOperation(value = "根据剪发卡信息")
    @GetMapping("/rechargeInfoByPhoneNum")
    public R<List<Haircut>> rechargeInfoByPhoneNum(HaircutQuery haircutQuery){
        List<Haircut> haircuts = haircutService.getBaseMapper().selectList(Wrappers.<Haircut>lambdaQuery()
                .like(Func.isNotEmpty(haircutQuery.getPhoneNum()), Haircut::getPhoneNum, haircutQuery.getPhoneNum())
                .like(Func.isNotEmpty(haircutQuery.getMemberUserName()),Haircut::getMemberUserName,haircutQuery.getMemberUserName()));
        return R.data(haircuts);
    }

    @ApiModelProperty(value = "查看所有会员信息")
    @GetMapping("/all_member")
    public R<List<Haircut>> allMember(){
       return R.data(haircutService.getBaseMapper().selectList(null));
    }
}
