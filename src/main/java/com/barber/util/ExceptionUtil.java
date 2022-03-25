package com.barber.util;

/**
 * @author will
 * 抛异常工具
 */
public class ExceptionUtil {

    public static void throwBizException(IResultCode resultCode) {
        throw new ServiceException(resultCode);
    }

    public static void throwBizException(String message) {
        throw new ServiceException(message);
    }

}
