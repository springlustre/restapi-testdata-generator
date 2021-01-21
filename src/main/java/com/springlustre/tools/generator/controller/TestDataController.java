package com.springlustre.tools.generator.controller;

import com.google.common.collect.Lists;
import com.springlustre.tools.generator.bean.TestDataTemplete;
import com.springlustre.tools.generator.service.TestDataService;
import com.springlustre.tools.generator.util.ExcelReadUtil;
import com.springlustre.tools.generator.util.ExcelWriteUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 控制层
 * @author wangchunze
 * @since 2020-02-23
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestDataController {
    @Autowired
    TestDataService testDataService;

    @Autowired
    ExcelWriteUtil excelWriteUtil;

    @Autowired
    ExcelReadUtil excelReadUtil;

    @ApiOperation("生成测试数据")
    @PostMapping(value = "/genTestData")
    public Object genTestData(@RequestBody Map<String, Object> req) throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
        List<TestDataTemplete> dataList = excelReadUtil.getTestDataTemplete(input,null);
        List<String> titles = dataList.stream().map(x->x.getParam()).collect(Collectors.toList());
        Map<String, Object> res = testDataService.genTestDataFromTemplete(dataList,null,0);
        List list = Lists.newArrayList();
        list.add(res);
        String path = "demo.xls";
        String name = "test";
        boolean suc = excelWriteUtil.writerExcel(path,name,titles,list);
        return suc;
    }


}
