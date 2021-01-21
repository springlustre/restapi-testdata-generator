package com.springlustre.tools.generator.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springlustre.tools.generator.bank.BankCardNumberGenerator;
import com.springlustre.tools.generator.bean.TestDataTemplete;
import com.springlustre.tools.generator.generator.*;
import com.springlustre.tools.generator.util.ChineseCharUtils;
import com.springlustre.tools.generator.util.ExcelReadUtil;
import com.springlustre.tools.generator.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author wangchunze
 * 2020.11.17
 */

@Component
@Slf4j
public class TestDataService {
    private static TestDataService instance = new TestDataService();
    public static TestDataService getInstance() {
        return instance;
    }
    ExcelReadUtil excelReadUtil = ExcelReadUtil.getInstance();

    /**
     * 随机获取一批枚举的结果
     * @param enumSource
     * @param num
     * @return
     */
    public List<Object> getEnumData(String enumSource, int num){
        List<String> list = Arrays.asList(enumSource.split("\\|"));
//        System.out.println(JSON.toJSON(list));
        int listSize = list.size();
        List<Object> res = IntStream.range(0,num).mapToObj(x->{
            int i = new Random().nextInt(listSize);
            return (String)list.get(i);
        }).collect(Collectors.toList());
        return res;
    }

    public Object getEnumData(String enumSource){
        List<String> list = Arrays.asList(enumSource.split("\\|"));
        int listSize = list.size();
        int i = new Random().nextInt(listSize);
        return (String)list.get(i);
    }

    /**
     * 生成中文字符串
     * @param sizeMin 最小长度
     * @param sizeMax 最大长度
     * @param num  数量
     * @return
     */
    public List<String> genStringCh(int sizeMin, int sizeMax, int num){
        List<String> res = IntStream.range(0,num).mapToObj(x->{
            String s = ChineseCharUtils.genRandomLengthChineseChars(Math.max(1,sizeMin), Math.min(100,sizeMax));
            return s;
        }).collect(Collectors.toList());
        return res;
    }

    public String genStringCh(int sizeMin, int sizeMax){
        String s = ChineseCharUtils.genRandomLengthChineseChars(Math.max(1,sizeMin), Math.min(100,sizeMax));
        return s;
    }


    /**
     * 生成英文字符串
     * @param sizeMin 最小长度
     * @param sizeMax 最大长度
     * @param num  数量
     * @return
     */
    public List<String> genStringEn(int sizeMin, int sizeMax, int num){
        List<String> res = IntStream.range(0,num).mapToObj(x->{
//            String s = ChineseCharUtils.genRandomLengthChineseChars(Math.max(1,sizeMin), Math.min(100,sizeMax));
            String s = RandomStringUtils.randomAlphanumeric(sizeMin,sizeMax);
            return s;
        }).collect(Collectors.toList());
        return res;
    }

    public String genStringEn(int sizeMin, int sizeMax){
        String s = RandomStringUtils.randomAlphanumeric(sizeMin,sizeMax);
        return s;
    }

    /**
     * 根据类别生成数据
     * 所属种类
     * 1：时间戳(13位)
     * 2：银行卡
     * 3：地址
     * 4：手机号
     * 5：中文姓名
     * 6：邮箱
     * 7：英文姓名
     * 8：身份证号
     * @param cata
     * @return
     */
    public String genDataByCatagory(String cata){
        switch(cata){
            case "1"://时间戳
                return String.valueOf(System.currentTimeMillis());
            case "2"://银行卡
                String bankCardNo = BankCardNumberGenerator.getInstance().generate();
                return bankCardNo;
            case "3"://地址
                String address = ChineseAddressGenerator.getInstance().generate();
                return address;
            case "4"://手机号
                String mobileNum = ChineseMobileNumberGenerator.getInstance().generate();
                return mobileNum;
            case "5"://中文姓名
                String name = ChineseNameGenerator.getInstance().generate();
                return name;
            case "6"://邮箱
                String email = EmailAddressGenerator.getInstance().generate();
                return email;
            case "7"://英文姓名
                String nameEn = EnglishNameGenerator.getInstance().generate();
                return nameEn;
            case "8":
                String idCard = ChineseIDCardNumberGenerator.getInstance().generate();
                return idCard;
            default:
                return null;
        }
    }

    /**
     * 根据参数类型生成数据
     * @param data
     * @return
     */
    private Object genStringByParamType(TestDataTemplete data){
        Object value;
        switch (data.getParamType()){
            case "string":
                if(Strings.isNullOrEmpty(data.getParamEnum())){
                    value = genStringEn(1,data.getStringLength());
                }else{
                    value = getEnumData(data.getParamEnum());
                }
                break;
            case "int":
                value = new Random().nextInt(Integer.MAX_VALUE);
                break;
            case "long":
                value = new Random().nextLong();
                break;
            case "double":
                value = new Random().nextDouble();
                break;
            default:
                value = null;
                break;
        }
        return value;
    }

    /**
     * 根据模板生成测试数据
     * @param dataList
     * @param excelPath
     * @param cycleCnt
     * @return
     * @throws Exception
     */
    public Map<String, Object> genTestDataFromTemplete(List<TestDataTemplete> dataList, String excelPath, int cycleCnt) throws Exception{
        if(cycleCnt > 20){
            throw new Exception("cycle counts over 20 times, there may be some trouble in your excel");
        }
//        System.out.println(JSON.toJSON(dataList));
        Map<String, Object> map = Maps.newHashMap();
        dataList.stream().forEach(data->{
            Object value;
            if(!Strings.isNullOrEmpty(data.getParamEnum())){
                //存在枚举，优先级最高
                value = getEnumData(data.getParamEnum());
            }else if(!Strings.isNullOrEmpty(data.getCategory())) {
                //有固定类别，优先级第二
                value = genDataByCatagory(data.getCategory());
            }else if(!Strings.isNullOrEmpty(data.getParamRule())){
                // 有正则条件，优先级第三
                value = RegexUtil.getInstance().genDataByReg(data.getParamRule());
            }else if(!Strings.isNullOrEmpty(data.getResourceType())){
                //存在来源类别,"来源类别
                //1：来源于文件
                //2：来源于其它sheet页的枚举
                //3：来源于其它sheet页的接口文档，json格式"
                switch(data.getResourceType()){
//                    case "1":
//                        break;
//                    case "2":
//                        break;
                    case "3":
                        value = JSON.toJSONString(genTestDataFromExcel(excelPath,data.getResource(),cycleCnt+1));
                        break;
                    default:
                        value = genStringByParamType(data);
                        break;
                }
            }else{
                //最后按照参数的字符类型生成
                value = genStringByParamType(data);
            }
            if(value!=null) {
                map.put(data.getParam(), value);
            }
        });

        return map;
    }

    /**
     * 批量生成测试数据，返回批量的list
     * @param excelPath excel的路径，resource路径下
     * @param sheetName sheet页名称
     * @param batchNum  批量数据的数量
     * @return
     */
    public List<Map<String, Object>> genTestDataFromExcelBatch(String excelPath,String sheetName, int batchNum){
        List list = Lists.newArrayList();
        List<TestDataTemplete> dataList;
        try{
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelPath);
            dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input, sheetName);
        }catch (Exception e){
            log.error("ExcelReadUtil genTestDataFromExcel exception",e);
            return null;
        }
        for(int i=0; i<batchNum; i++){
            try {
                Map<String, Object> res = TestDataService.getInstance().genTestDataFromTemplete(dataList, excelPath, 0);
                list.add(res);
            }catch (Exception e){
                log.error("TestDataService genTestDataFromTemplete exception",e);
            }
        }
        return list;
    }


    /**
     * 从excel来源生成单条测试数据，
     * @param excelPath excel的路径，resource下的相对路径
     * @param sheetName sheet页的名称
     * @param cycleCnt  循环次数，初始为0
     * @return  Map结构数据
     * @throws Exception
     */
    public Map<String, Object> genTestDataFromExcel(String excelPath,String sheetName,int cycleCnt){
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelPath);
            List<TestDataTemplete> dataList = ExcelReadUtil.getInstance().getTestDataTemplete(input, sheetName);
            List<String> titles = dataList.stream().map(x -> x.getParam()).collect(Collectors.toList());
            Map<String, Object> res = TestDataService.getInstance().genTestDataFromTemplete(dataList, excelPath, cycleCnt + 1);
            return res;
        }catch (Exception e){
            log.error("genTestDataFromExcel exception",e);
            return null;
        }
    }




}








