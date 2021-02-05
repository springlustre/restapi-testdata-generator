package com.springlustre.tools.generator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.springlustre.tools.generator.bean.TestDataTemplete;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 * @author springlustre
 * 2020.11.17
 */
@Component
public class ExcelReadUtil {
    private static ExcelReadUtil instance = new ExcelReadUtil();
    public static ExcelReadUtil getInstance() {
        return instance;
    }


    /**
     * 读取2003excel
     *
     * @param file
     * @return
     */
    private List<List<Object>> read2003Excel(File file) throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        Object val = null;
        DecimalFormat df = new DecimalFormat("0");// 格式化数字
        DecimalFormat df2 = new DecimalFormat("#0.00");// 格式化小数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
        for (int i = sheet.getFirstRowNum() + 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> objList = new ArrayList<Object>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            if (j == 3) {
                                val = df2.format(cell.getNumericCellValue());
                            } else {
                                val = df.format(cell.getNumericCellValue());
                            }
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            if (j == 3) {
                                val = df2.format(cell.getNumericCellValue());
                            } else {
                                val = df.format(cell.getNumericCellValue());
                            }
                        } else {
                            val = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }


    /**
     * 读取2007excel
     * @return
     */

    private static List<List<Object>> read2007Excel(InputStream input, String sheetName) throws IOException {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        XSSFWorkbook xwb = new XSSFWorkbook(input);
        XSSFSheet sheet = Strings.isNullOrEmpty(sheetName) ? xwb.getSheetAt(0) : xwb.getSheet(sheetName);
        XSSFRow row = null;
        XSSFCell cell = null;
        Object val = null;
        DecimalFormat df = new DecimalFormat("0");// 格式化数字
        DecimalFormat df2 = new DecimalFormat("#0.00");// 格式化小数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
        for (int i = sheet.getFirstRowNum() + 2; i < sheet.getPhysicalNumberOfRows(); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> objList = new ArrayList<Object>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    val = null;
                    objList.add(val);
                    continue;
                }
                switch (cell.getCellType()) {
                    case XSSFCell.CELL_TYPE_STRING:
                        val = cell.getStringCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_NUMERIC:
                        if ("@".equals(cell.getCellStyle().getDataFormatString())) {
                            if (j == 3) {
                                val = df2.format(cell.getNumericCellValue());
                            } else {
                                val = df.format(cell.getNumericCellValue());
                            }
                        } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            if (j == 3) {
                                val = df2.format(cell.getNumericCellValue());
                            } else {
                                val = df.format(cell.getNumericCellValue());
                            }
                        } else {
                            val = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                        }
                        break;
                    case XSSFCell.CELL_TYPE_BOOLEAN:
                        val = cell.getBooleanCellValue();
                        break;
                    case XSSFCell.CELL_TYPE_BLANK:
                        val = "";
                        break;
                    default:
                        val = cell.toString();
                        break;
                }
                objList.add(val);
            }
            dataList.add(objList);
        }
        return dataList;
    }


    /**
     * 读取接口文档模板
     * @param input
     * @return
     * @throws Exception
     */
    public List<TestDataTemplete> getTestDataTemplete(InputStream input, String sheetName) throws Exception{
        List<List<Object>> res = read2007Excel(input, sheetName);
        List<TestDataTemplete> list = new ArrayList<TestDataTemplete>();
        res.stream().forEach(row->{
            TestDataTemplete dataTemplete = new TestDataTemplete();
            if(!row.isEmpty() && row.size()>=12) {
                int c1 = row.get(0) == null || Strings.isNullOrEmpty((String) row.get(0)) ? 0 : Double.valueOf((String) row.get(0)).intValue();
                int c7 = row.get(7) == null || Strings.isNullOrEmpty((String) row.get(7)) ? 0 : Double.valueOf((String) row.get(7)).intValue();
                dataTemplete.setIndex(c1);
                dataTemplete.setParam((String) row.get(1));
                dataTemplete.setParamName((String) row.get(2));
                dataTemplete.setParamType((String) row.get(3));
                dataTemplete.setRequire((String) row.get(4));
                dataTemplete.setParamEnum((String) row.get(5));
                dataTemplete.setStringLength(c7);
                dataTemplete.setCategory((String) row.get(8));
                dataTemplete.setParamRule((String) row.get(9));
                dataTemplete.setResourceType((String) row.get(10));
                dataTemplete.setResource((String) row.get(11));
                list.add(dataTemplete);
            }
        });
//        System.out.println(JSON.toJSON(list));
        return list;
    }



    public static void main(String[] args) throws Exception {
//        readXlsxByEventModel();
//        readXlsxByEventModelWithoutEntity();
//        readXlsxByUserModel();
//        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("data_templete.xlsx");
//        List<TestDataTemplete> res = getTestDataTemplete(input);
//        System.out.println(JSON.toJSON(res));
    }

}
