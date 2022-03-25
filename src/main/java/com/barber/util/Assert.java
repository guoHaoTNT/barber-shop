package com.barber.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Collection;
import java.util.Map;

/**
 * @author will
 * 业务断言工具类
 */
public class Assert {

    /**
     * 断言是否为真，如果为 {@code false} 抛出校验异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param msg        异常Msg
     */
    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            ExceptionUtil.throwBizException(msg);
        }
    }

    /**
     * 断言是否为真，如果为 {@code false} 抛出业务响应异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param resultCode 业务响应异常枚举
     */
    public static void isTrue(boolean expression, IResultCode resultCode) {
        if (!expression) {
            ExceptionUtil.throwBizException(resultCode);
        }
    }

    /**
     * 断言是否为假，如果为 {@code true} 抛出校验异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param msg        异常Msg
     */
    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            ExceptionUtil.throwBizException(msg);
        }
    }

    /**
     * 断言是否为假，如果为 {@code true} 抛出业务响应异常
     *
     * @param expression 布尔值 or 布尔值表达式
     * @param resultCode 业务响应异常枚举
     */
    public static void isFalse(boolean expression, IResultCode resultCode) {
        if (expression) {
            ExceptionUtil.throwBizException(resultCode);
        }
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出校验异常
     *
     * @param object 被检查的对象
     * @param msg    业务响应异常Msg
     */
    public static void isNull(Object object, String msg) {
        if (object != null) {
            ExceptionUtil.throwBizException(msg);
        }
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出业务响应异常
     *
     * @param object     被检查的对象
     * @param resultCode 业务响应异常枚举
     */
    public static void isNull(Object object, IResultCode resultCode) {
        if (object != null) {
            ExceptionUtil.throwBizException(resultCode);
        }
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出校验异常
     *
     * @param object 被检查对象泛型类型
     * @param msg    校验异常Msg
     * @param <T>    被检查对象泛型类型
     * @return 被检查后的对象
     */
    public static <T> T notNull(T object, String msg) {
        if (object == null) {
            ExceptionUtil.throwBizException(msg);
        }
        return object;
    }

    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出业务响应异常
     *
     * @param object     被检查对象泛型类型
     * @param resultCode 业务响应异常枚举
     * @param <T>        被检查对象泛型类型
     * @return 被检查后的对象
     */
    public static <T> T notNull(T object, IResultCode resultCode) {
        if (object == null) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return object;
    }

    /**
     * 检查给定字符串是否为空，为空抛出校验异常
     *
     * @param text 被检查字符串
     * @param msg  校验异常Msg
     * @param <T>  字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notEmpty(T text, String msg) {
        if (StrUtil.isEmpty(text)) {
            ExceptionUtil.throwBizException(msg);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空，为空抛出业务响应异常
     *
     * @param text       被检查字符串
     * @param resultCode 业务响应异常枚举
     * @param <T>        字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notEmpty(T text, IResultCode resultCode) {
        if (StrUtil.isEmpty(text)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出校验异常
     *
     * @param text 被检查字符串
     * @param msg  校验异常Msg
     * @param <T>  字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notBlank(T text, String msg) {
        if (StrUtil.isBlank(text)) {
            ExceptionUtil.throwBizException(msg);
        }
        return text;
    }

    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出业务响应异常
     *
     * @param text       被检查字符串
     * @param resultCode 业务响应异常枚举
     * @param <T>        字符串类型
     * @return 非空字符串
     */
    public static <T extends CharSequence> T notBlank(T text, IResultCode resultCode) {
        if (StrUtil.isBlank(text)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return text;
    }

    /**
     * 断言给定字符串是否不被另一个字符串包含（即是否为子串）, false 抛出业务响应异常
     *
     * @param textToSearch 被搜索的字符串
     * @param substring    被检查的子串
     * @param msg          业务响应异常Msg
     * @return 被检查的子串
     */
    public static String notContain(String textToSearch, String substring, String msg) {
        if (StrUtil.isNotEmpty(textToSearch) && StrUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            ExceptionUtil.throwBizException(msg);
        }
        return substring;
    }

    /**
     * 断言给定字符串是否不被另一个字符串包含（即是否为子串）, false 抛出业务响应异常
     *
     * @param textToSearch 被搜索的字符串
     * @param substring    被检查的子串
     * @param resultCode   业务响应异常枚举
     * @return 被检查的子串
     */
    public static String notContain(String textToSearch, String substring, IResultCode resultCode) {
        if (StrUtil.isNotEmpty(textToSearch) && StrUtil.isNotEmpty(substring) && textToSearch.contains(substring)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return substring;
    }

    /**
     * 断言给定数组是否包含元素，数组必须不为 {@code null} 且至少包含一个元素, false 抛出业务响应异常
     *
     * @param array 被检查的数组
     * @return 被检查的数组
     */
    public static Object[] notEmpty(Object[] array, String msg) {
        if (ArrayUtil.isEmpty(array)) {
            ExceptionUtil.throwBizException(msg);
        }
        return array;
    }

    /**
     * 断言给定数组是否包含元素，数组必须不为 {@code null} 且至少包含一个元素, false 抛出业务响应异常
     *
     * @param array      被检查的数组
     * @param resultCode 业务响应异常枚举
     * @return 被检查的数组
     */
    public static Object[] notEmpty(Object[] array, IResultCode resultCode) {
        if (ArrayUtil.isEmpty(array)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return array;
    }

    /**
     * 断言给定数组是否不包含{@code null}元素，如果数组为空或 {@code null}将被认为不包含, false 抛出业务响应异常
     *
     * @param <T>   数组元素类型
     * @param array 被检查的数组
     * @param msg   业务响应异常Msg
     * @return 被检查的数组
     */
    public static <T> T[] noNullElements(T[] array, String msg) {
        if (ArrayUtil.hasNull(array)) {
            ExceptionUtil.throwBizException(msg);
        }
        return array;
    }

    /**
     * 断言给定数组是否不包含{@code null}元素，如果数组为空或 {@code null}将被认为不包含, false 抛出业务响应异常
     *
     * @param <T>        数组元素类型
     * @param array      被检查的数组
     * @param resultCode 业务响应异常枚举
     * @return 被检查的数组
     */
    public static <T> T[] noNullElements(T[] array, IResultCode resultCode) {
        if (ArrayUtil.hasNull(array)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return array;
    }

    /**
     * 断言给定集合非空, false 抛出校验异常
     *
     * @param <T>        集合元素类型
     * @param collection 被检查的集合
     * @param msg        校验异常Msg
     * @return 被检查集合
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection, String msg) {
        if (CollUtil.isEmpty(collection)) {
            ExceptionUtil.throwBizException(msg);
        }
        return collection;
    }

    /**
     * 断言给定集合非空, false 抛出业务响应异常
     *
     * @param <T>        集合元素类型
     * @param collection 被检查的集合
     * @param resultCode 业务响应异常枚举
     * @return 非空集合
     */
    public static <T> Collection<T> notEmpty(Collection<T> collection, IResultCode resultCode) {
        if (CollUtil.isEmpty(collection)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return collection;
    }

    /**
     * 断言给定Map非空, false 抛出业务响应异常
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @param map 被检查的Map
     * @param msg 业务响应异常Msg
     * @return 被检查的Map
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, String msg) {
        if (MapUtil.isEmpty(map)) {
            ExceptionUtil.throwBizException(msg);
        }
        return map;
    }

    /**
     * 断言给定Map非空, false 抛出业务响应异常
     *
     * @param <K>        Key类型
     * @param <V>        Value类型
     * @param map        被检查的Map
     * @param resultCode 业务响应异常枚举
     * @return 被检查的Map
     */
    public static <K, V> Map<K, V> notEmpty(Map<K, V> map, IResultCode resultCode) {
        if (MapUtil.isEmpty(map)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return map;
    }

    /**
     * 断言给定对象是否是给定类的实例, false 抛出业务响应异常
     *
     * @param <T>  被检查对象泛型类型
     * @param type 被检查对象匹配的类型
     * @param obj  被检查对象
     * @param msg  业务响应异常Msg
     * @return 被检查的对象
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, String msg) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            ExceptionUtil.throwBizException(msg);
        }
        return obj;
    }

    /**
     * 断言给定对象是否是给定类的实例, false 抛出业务响应异常
     *
     * @param <T>        被检查对象泛型类型
     * @param type       被检查对象匹配的类型
     * @param obj        被检查对象
     * @param resultCode 业务响应异常枚举
     * @return 被检查的对象
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, IResultCode resultCode) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return obj;
    }

    /**
     * 断言子类是否继承父类, false 抛出业务响应异常
     *
     * @param superType 需要检查的父类或接口
     * @param subType   需要检查的子类
     * @param msg       业务响应异常Msg
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String msg) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            ExceptionUtil.throwBizException(msg);
        }
    }

    /**
     * 断言子类是否继承父类, false 抛出业务响应异常
     *
     * @param superType  需要检查的父类或接口
     * @param subType    需要检查的子类
     * @param resultCode 业务响应异常枚举
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, IResultCode resultCode) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            ExceptionUtil.throwBizException(resultCode);
        }
    }

    /**
     * 检查下标（数组、集合、字符串）是否符合要求，下标必须满足：0 < index <= size, false 抛出业务响应异常
     *
     * @param index 下标
     * @param size  长度
     * @param msg   业务响应异常Msg
     * @return 检查后的下标
     */
    public static int checkIndex(int index, int size, String msg) {
        if (index < 0 || index >= size) {
            ExceptionUtil.throwBizException(msg);
        }
        return index;
    }

    /**
     * 检查下标（数组、集合、字符串）是否符合要求，下标必须满足：0 < index <= size, false 抛出业务响应异常
     *
     * @param index      下标
     * @param size       长度
     * @param resultCode 业务响应异常枚举
     * @return 检查后的下标
     */
    public static int checkIndex(int index, int size, IResultCode resultCode) {
        if (index < 0 || index >= size) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return index;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @param msg   业务响应异常Msg
     * @return 检查后的长度值
     */
    public static int checkBetween(int value, int min, int max, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value      值
     * @param min        最小值（包含）
     * @param max        最大值（包含）
     * @param resultCode 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static int checkBetween(int value, int min, int max, IResultCode resultCode) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @param msg   业务响应异常Msg
     * @return 检查后的长度值
     */
    public static long checkBetween(long value, long min, long max, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value      值
     * @param min        最小值（包含）
     * @param max        最大值（包含）
     * @param resultCode 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static long checkBetween(long value, long min, long max, IResultCode resultCode) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @param msg   业务响应异常Msg
     * @return 检查后的长度值
     */
    public static double checkBetween(double value, double min, double max, String msg) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value      值
     * @param min        最小值（包含）
     * @param max        最大值（包含）
     * @param resultCode 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static double checkBetween(double value, double min, double max, IResultCode resultCode) {
        if (value < min || value > max) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value 值
     * @param min   最小值（包含）
     * @param max   最大值（包含）
     * @param msg   业务响应异常Msg
     * @return 检查后的长度值
     */
    public static Number checkBetween(Number value, Number min, Number max, String msg) {
        notNull(value, "value 不能为空");
        notNull(min, "min 不能为空");
        notNull(max, "max 不能为空");
        double valueDouble = value.doubleValue();
        double minDouble = min.doubleValue();
        double maxDouble = max.doubleValue();
        if (valueDouble < minDouble || valueDouble > maxDouble) {
            ExceptionUtil.throwBizException(msg);
        }
        return value;
    }

    /**
     * 检查值是否在指定范围内, false 抛出业务响应异常
     *
     * @param value      值
     * @param min        最小值（包含）
     * @param max        最大值（包含）
     * @param resultCode 业务响应异常枚举
     * @return 检查后的长度值
     */
    public static Number checkBetween(Number value, Number min, Number max, IResultCode resultCode) {
        notNull(value, "value 不能为空");
        notNull(min, "min 不能为空");
        notNull(max, "max 不能为空");
        double valueDouble = value.doubleValue();
        double minDouble = min.doubleValue();
        double maxDouble = max.doubleValue();
        if (valueDouble < minDouble || valueDouble > maxDouble) {
            ExceptionUtil.throwBizException(resultCode);
        }
        return value;
    }

}