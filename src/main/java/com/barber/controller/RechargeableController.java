package com.barber.controller;


import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.barber.common.RetVal;
import com.barber.dao.Haircut;
import com.barber.dao.MemberUser;
import com.barber.dao.Rechargeable;
import com.barber.service.MemberUserService;
import com.barber.service.RechargeableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/rechargeable")
@AllArgsConstructor
@Api(value = "充值卡信息管理", tags = "充值卡")
@CrossOrigin
public class RechargeableController {

    private final RechargeableService rechargeableService;
    private final MemberUserService memberUserService;

    @ApiModelProperty(value = "查询会员列表信息")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public RetVal queryRechargeableList(@PathVariable("pageNum") Long pageNum,
                                      @PathVariable("pageSize") Long pageSize,
                                        Rechargeable rechargeable){
        Page<Rechargeable> rechargeablePage = new Page<>(pageNum, pageSize);
        IPage<Rechargeable> rechargeableIPage = rechargeableService.queryRechargeableList(rechargeablePage,rechargeable);
        long total = rechargeableIPage.getTotal();
        return RetVal.success().data("total",total).data("rechargeableList",rechargeableIPage);
    }

    @ApiOperation(value = "充值卡保存")
    @PostMapping("/save")
    public RetVal save(Rechargeable rechargeable){
        if (ObjectUtil.isEmpty(rechargeable.getPhoneNum())){
            return RetVal.error().message("手机号不能为空!");
        }
        MemberUser memberUser = memberUserService.getBaseMapper().selectOne(Wrappers.<MemberUser>lambdaQuery()
                .eq(MemberUser::getPhoneNum, rechargeable.getPhoneNum()));
        if (ObjectUtil.isEmpty(memberUser)){
            return RetVal.error().message("请先添加会员信息，手机号要和添加会员时对应");
        }
        rechargeable.setMemberUserId(memberUser.getId());
        rechargeable.setMemberUserName(memberUser.getMemberUserName());
        rechargeableService.saveOrUpdate(rechargeable);
        return RetVal.success().data("id",rechargeable.getId().toString());
    }

    @ApiModelProperty(value = "根据id查询充值卡信息")
    @GetMapping("/queryRechargeableById/{id}")
    public RetVal queryRechargeableById(@PathVariable("id") Long id) {
        Rechargeable rechargeable = rechargeableService.getById(id);
        return RetVal.success().data("rechargeableCondition",rechargeable);
    }

}
