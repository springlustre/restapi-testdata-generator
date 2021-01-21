package com.springlustre.tools.generator.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.springlustre.tools.generator.bean.TestDataTemplete;
import com.springlustre.tools.generator.generator.ChineseAddressGenerator;
import com.springlustre.tools.generator.util.ExcelReadUtil;
import com.springlustre.tools.generator.util.ExcelWriteUtil;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testng.Assert.assertNotNull;

/**
 * @author wangchunze
 * 2020.11.17
 */

public class TestDataServiceTest {

    @Test
    public void test1() {
        String enumData = "a|b|c|d|e|f|g";
        List<Object> res = TestDataService.getInstance().getEnumData(enumData,10);
        System.err.println(JSON.toJSON(res));
    }

    @Test
    public void genStringCh(){
        List<String> res = TestDataService.getInstance().genStringCh(2,10,10);
        System.out.println(JSON.toJSON(res));
    }

    @Test
    public void genStringEn(){
        List<String> res = TestDataService.getInstance().genStringEn(2,10,10);
        System.out.println(JSON.toJSON(res));
    }

    @Test
    public void genTestDataFromTempleteTest() throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = dataList.stream().map(x->x.getParam()).collect(Collectors.toList());
        Map<String, Object> res = TestDataService.getInstance().genTestDataFromTemplete(dataList,"data_templete.xlsx",0);
        List list = Lists.newArrayList();
        list.add(res);
        String path = "demo.xls";
        String name = "test";
        boolean suc = ExcelWriteUtil.writerExcel(path,name,titles,list);
        System.out.println(suc);
    }

    @Test
    public void genTestDataFromExcelTest() throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = dataList.stream().map(x->x.getParam()).collect(Collectors.toList());
        String sheetName = null; //CUST_BASIC_INFO
        Map<String, Object> res = TestDataService.getInstance().genTestDataFromExcel("data_templete.xlsx",sheetName,0);
        List list = Lists.newArrayList();
        list.add(res);
        String path = "demo.xls";
        String name = "test";
        String str = JSON.toJSONString(res);
        System.out.println(str);
    }

    @Test
    public void genTestDataBatchTest() throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = dataList.stream().map(x->x.getParam()).collect(Collectors.toList());
        List<Map<String, Object>> res = TestDataService.getInstance().genTestDataFromExcelBatch("data_templete.xlsx",null,20);
        String path = "demo.xls";
        String name = "test";
        boolean suc = ExcelWriteUtil.writerExcel(path,name,titles,res);
        System.out.println(suc);
    }

    @Test
    public void genTestDataBatchJsonTest() throws Exception{
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
        List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input,null);
        List<String> titles = Lists.newArrayList("data");
        List<Map<String, Object>> res = TestDataService.getInstance().genTestDataFromExcelBatch("data_templete.xlsx",null,20);
        List<Map<String, Object>> jsonList = res.stream().map(m->{
            Map<String, Object> x = new HashMap<String, Object>();
            x.put("data",JSON.toJSONString(m));
            return x;
        }).collect(Collectors.toList());
        String path = "json.xls";
        String name = "test";
        boolean suc = ExcelWriteUtil.writerExcel(path,name,titles,jsonList);
        System.out.println(suc);
    }


}
