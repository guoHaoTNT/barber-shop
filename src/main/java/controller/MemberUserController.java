package controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import dao.MemberUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MemberUserService;

import java.util.List;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/haircut")
@AllArgsConstructor
@Api(value = "会员管理系统", tags = "会员管理")
public class MemberUserController {

    private final MemberUserService memberUserService;

    /**
     * 修改数据需要把信息同步到 剪发卡和充值卡
     */

    /**
     * 根据剪发卡信息
     *
     * @return
     */
    @ApiOperation(value = "根据剪发卡信息")
    @GetMapping("/rechargeInfoByPhoneNum/{name}")
    public R<List<MemberUser>> queryByName(@PathVariable("name") String name) {
        List<MemberUser> memberUsers = memberUserService.getBaseMapper().selectList(Wrappers.<MemberUser>lambdaQuery()
                .like(MemberUser::getMemberUserName, name));
        return R.data(memberUsers);
    }

}
