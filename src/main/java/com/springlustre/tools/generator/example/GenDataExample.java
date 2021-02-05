package com.springlustre.tools.generator.example;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.springlustre.tools.generator.bean.TestDataTemplete;
import com.springlustre.tools.generator.service.TestDataService;
import com.springlustre.tools.generator.util.ExcelReadUtil;
import com.springlustre.tools.generator.util.ExcelWriteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author springlustre
 * 2020.12.17
 */

@Component
@Slf4j
public class GenDataExample {
    @Autowired
    TestDataService testDataService;

    @Autowired
    ExcelWriteUtil excelWriteUtil;

    @Autowired
    ExcelReadUtil excelReadUtil;

    /**
     * 根据接口文档生成单条json数据
     * @param templetePath
     * @throws Exception
     */
    public static void genTestDataFromExcel(String templetePath) throws Exception{
        String sheetName = null;
        Map<String, Object> res = TestDataService.getInstance().genTestDataFromExcel(templetePath,sheetName,0);
        List list = Lists.newArrayList();
        list.add(res);
        String str = JSON.toJSONString(res);
        System.out.println(str);
    }


    //批量生成测试数据，并按字段区分存入excel
    public static void genTestDataBatch(String templetePath,String targetPath) throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(templetePath);
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = dataList.stream().map(x->x.getParam()).collect(Collectors.toList());
        List<Map<String, Object>> res = TestDataService.getInstance().genTestDataFromExcelBatch(templetePath,null,20);
        String name = "Sheet1";
        boolean suc = ExcelWriteUtil.writerExcel(targetPath,name,titles,res);
        System.out.println(suc);
    }

    //批量生成测试数据，json的报文
    public static void genTestDataBatchJson(String templetePath,String targetPath) throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(templetePath);
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = Lists.newArrayList("data");
        List<Map<String, Object>> res = TestDataService.getInstance().genTestDataFromExcelBatch(templetePath,null,20);
        List<Map<String, Object>> jsonList = res.stream().map(m->{
            Map<String, Object> x = new HashMap<String, Object>();
            x.put("data",JSON.toJSONString(m));
            return x;
        }).collect(Collectors.toList());
        String path = targetPath;
        String name = "Sheet1";
        boolean suc = ExcelWriteUtil.writerExcel(path,name,titles,jsonList);
        System.out.println(suc);
    }

    public static void main(String[] args) throws Exception {
        String templetePath = "data_templete2.xlsx";
        String targetPath = "result.xlsx";
        genTestDataFromExcel(templetePath);
//        genTestDataBatch(templetePath,targetPath);
//        genTestDataBatchJson(templetePath,targetPath);
    }

}
