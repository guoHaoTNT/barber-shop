package com.barber.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author will
 */
@Slf4j
@RestController
@RequestMapping("/excel")
@AllArgsConstructor
@Api(value = "excel表格导入导出", tags = "excel表格导入导出")
public class ExcelController {


}
