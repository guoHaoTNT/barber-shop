package com.barber.enums;

import java.util.Arrays;
import java.util.Optional;
/**
 * 枚举 code msg 类型接口
 *
 * @author DuChao
 * @date 2020/7/7 2:00 下午
 */
public interface ICodeEnum {
    /**
     * 获取code的方法，用于后续循环对比状态。
     * @return 枚举类型
     */
    Integer getCode();

    /**
     * 获取msg的方法，用于后续循环对比状态。
     * @return 枚举类型
     */
    String getDesc();

    /**
     * 状态判断
     *
     * @param value 状态值
     * @return 返回结果
     */
    default boolean equals(Integer value) {
        return getCode().equals(value);
    }

    /**
     * 查询枚举
     *
     * @param value     值
     * @param enumClass 类型
     * @param <E>       枚举类实例
     * @return 返回结果
     */
    static <E extends Enum<E> & ICodeEnum> Optional<E> find(Integer value, final Class<E> enumClass) {
        if (value != null) {
            return Arrays.stream(enumClass.getEnumConstants())
                    .filter(v -> v.getCode().equals(value))
                    .findFirst();
        }
        return Optional.empty();
    }
}
