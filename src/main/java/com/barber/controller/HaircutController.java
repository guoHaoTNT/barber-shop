package com.barber.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.barber.common.RetVal;
import com.barber.dao.Haircut;
import com.barber.dao.MemberUser;
import com.barber.dao.vo.HaircutQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin
@Api(value = "剪发卡", tags = "剪发卡")
public class HaircutController {

    private final HaircutService haircutService;
    private final MemberUserService memberUserService;

    @ApiOperation(value = "会员剪发卡充值/保存/修改")
    @PostMapping("/recharge")
    public RetVal recharge(Haircut haircut){
        if (ObjectUtil.isEmpty(haircut.getPhoneNum())){
            return RetVal.error().message("手机号不能为空!");
        }
        MemberUser memberUser = memberUserService.getBaseMapper().selectOne(Wrappers.<MemberUser>lambdaQuery()
                .eq(MemberUser::getPhoneNum, haircut.getPhoneNum()));
        if (ObjectUtil.isEmpty(memberUser)){
            return RetVal.error().message("请先添加会员信息，手机号要和添加会员时对应");
        }
        haircut.setMemberUserId(memberUser.getId());
        haircut.setMemberUserName(memberUser.getMemberUserName());
        haircutService.saveOrUpdate(haircut);
        return RetVal.success().data("id",haircut.getId().toString());
    }

    @ApiModelProperty(value = "查询剪发卡列表信息")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public RetVal queryHaircutList(@PathVariable("pageNum") Long pageNum,
                                      @PathVariable("pageSize") Long pageSize,
                                      Haircut haircut){
        Page<Haircut> haircutPage = new Page<>(pageNum, pageSize);
        IPage<Haircut> spuInfoIPage = haircutService.queryHaircutList(haircutPage,haircut);
        long total = spuInfoIPage.getTotal();
        return RetVal.success().data("total",total).data("haircutList",spuInfoIPage);
    }

    @ApiModelProperty(value = "查看所有会员信息")
    @GetMapping("/all_member")
    public R<List<Haircut>> allMember(){
       return R.ok(haircutService.getBaseMapper().selectList(null));
    }

    @ApiModelProperty(value = "根据id查询剪发卡信息")
    @GetMapping("/queryHaircutById/{id}")
    public RetVal queryHaircutById(@PathVariable("id") Long id) {
        Haircut haircut = haircutService.getById(id);
        return RetVal.success().data("haircutCondition",haircut);
    }
}
