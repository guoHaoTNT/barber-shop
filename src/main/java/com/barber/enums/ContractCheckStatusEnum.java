package com.barber.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author will
 * 测试枚举类
 */
@Getter
@AllArgsConstructor
public enum ContractCheckStatusEnum implements ICodeEnum{
    /**
     * 合同审核状态
     */
    NO_SUBMIT(0,"待提交"),
    NO_CHECK(1,"待审核"),
    CHECK_APPROVAL(2,"审核通过"),
    CHECK_REFUSE(3,"审核不通过");

    private final Integer code;
    private final String desc;
}
