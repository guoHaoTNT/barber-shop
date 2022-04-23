package com.barber.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.barber.common.RetVal;
import com.barber.dao.MemberUser;
import com.barber.service.MemberUserService;
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
@RequestMapping("/haircut")
@AllArgsConstructor
@Api(value = "会员管理系统", tags = "会员管理")
@CrossOrigin
public class MemberUserController {

    private final MemberUserService memberUserService;

    @ApiModelProperty(value = "查询会员列表信息")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public RetVal queryHaircutList(@PathVariable("pageNum") Long pageNum,
                                                       @PathVariable("pageSize") Long pageSize,
                                                       MemberUser memberUser){
        Page<MemberUser> memberUserPage = new Page<>(pageNum, pageSize);
        IPage<MemberUser> spuInfoIPage = memberUserService.queryHaircutList(memberUserPage,memberUser);
        return RetVal.success().data("userList",spuInfoIPage);
    }

    /**
     * 会员卡，手机号和姓名模糊查询
     *
     * @param name
     * @return
     */
    @ApiOperation(value = "根据剪发卡信息")
    @GetMapping("/rechargeInfoByPhoneNum/{name}/{phone_num}")
    public R<List<MemberUser>> queryByName(@PathVariable("name") String name, @PathVariable("phone_num") Integer phoneNum) {
        List<MemberUser> list = memberUserService.list(Wrappers.<MemberUser>lambdaQuery()
                .like(ObjectUtil.isNotEmpty(name), MemberUser::getMemberUserName, name)
                .like(ObjectUtil.isNotEmpty(phoneNum), MemberUser::getPhoneNum, phoneNum));
        return R.ok(list);
    }

    /**
     * 会员保存/修改
     *
     * @param memberUser
     * @return
     */
    @ApiModelProperty(value = "会员保存")
    @PostMapping("/save")
    public R<String> save(@RequestBody MemberUser memberUser) {
        //手机号是唯一标识校验
        List<MemberUser> list = memberUserService.list(Wrappers.<MemberUser>lambdaQuery()
                .eq(MemberUser::getPhoneNum, memberUser.getPhoneNum()));
        if (list.size() > 0) {
            return R.failed("该手机号已经绑定会员，请检查后再次输入");
        }
        memberUserService.saveOrUpdate(memberUser);
        return R.ok(memberUser.getId().toString());
    }

    /**
     * 根据手机号查询会员信息
     *
     * @param phoneNum
     * @return
     */
    @ApiModelProperty(value = "根据手机号查询会员信息")
    @GetMapping("/queryByPhone/{phone_num}")
    public R<MemberUser> queryByPhone(@PathVariable("phone_num") Integer phoneNum) {
        MemberUser memberUser = memberUserService.getOne(Wrappers.<MemberUser>lambdaQuery()
                .eq(MemberUser::getPhoneNum, phoneNum));
        return R.ok(memberUser);
    }

    /**
     * 会员删除
     *
     * @param id
     * @return
     */
    @ApiModelProperty(value = "会员删除")
    @DeleteMapping("/delMemBerUser/{id}")
    public R<Boolean> delMemBerUser(@PathVariable("id") Long id) {
        boolean b = memberUserService.removeById(id);
        return R.ok(b);
    }

}
