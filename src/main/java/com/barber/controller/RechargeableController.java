package com.barber.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.barber.dao.Rechargeable;
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

    @ApiOperation(value = "充值卡保存")
    @PostMapping("/save")
    public R<String> save(@RequestBody Rechargeable rechargeable){
        rechargeableService.save(rechargeable);
        return R.ok(rechargeable.getId().toString());
    }

    @ApiModelProperty("根据手机号查询")
    @GetMapping()
    public R<List<Rechargeable>> query(){

        return R.ok(null);
    }

}
