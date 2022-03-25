package com.barber.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author will
 */
public interface IEnum {
    /**
     * 获取值
     *
     * @return 值
     */
    Integer getValue();

    /**
     * 获取描述
     *
     * @return 描述
     */
    String getDesc();

    /**
     * 状态判断
     *
     * @param value 状态值
     * @return 返回结果
     */
    default boolean equals(Integer value) {
        return getValue().equals(value);
    }

    /**
     * 查询枚举
     *
     * @param value     值
     * @param enumClass 类型
     * @param <E>       枚举类实例
     * @return 返回结果
     */
    static <E extends Enum<E> & IEnum> Optional<E> of(Integer value, final Class<E> enumClass) {
        if (value != null) {
            return Arrays.stream(enumClass.getEnumConstants())
                    .filter(v -> v.getValue().equals(value))
                    .findFirst();
        }
        return Optional.empty();
    }
}
