package com.barber.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author will
 */
@Getter
@AllArgsConstructor
public enum TaskResultEnum implements IEnum{
    /**
     * 错误类型
     */
    SUCCESS(200, "处理成功"),

    UPLOAD_FAILURE(500, "文件上传失败"),

    UNKNOWN_EXPORT_FAILURE(501, "网络超时"),

    CALLBACK_FAILURE(502, "任务回调异常"),

    IMPORT_FORMAT_ERROR(503, "表格格式不正确"),

    IMPORT_DATA_ERROR(504, "数据校验不通过，问题详见导入结果"),

    IMPORT_COUNT_ERROR(505, "导入数据最多不可超过5000条数据"),

    IMPORT_FILE_ERROR(506, "请上传正确的EXCEL文件"),

    IMPORT_DOWNLOAD_ERROR(507, "原文件下载失败"),

    IMPORT_FILE_LINK_ERROR(508, "获取文件链接失败"),

    FILE_TYPE_ERROR(509, "文件类型错误"),

    UPLOAD_FILE_BLANK(510, "上传文件为空"),

    CREATE_USER_ERROR(511, "获取录入员信息失败"),

    TOKEN_ERROR(512, "TOKEN生成失败"),

    IMPORT_DATA_EMPTY_ERROR(513, "导入的模板中数据为空"),

    CUSTOM_FAILURE(999, "自定义错误");

    private final Integer value;

    private final String desc;
}
