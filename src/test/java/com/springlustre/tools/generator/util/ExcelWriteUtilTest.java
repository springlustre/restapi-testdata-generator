package com.springlustre.tools.generator.util;

import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelWriteUtilTest {

    @Test
    public void test(){
        String path = "demo.xls";
        String name = "test";
        List<String> titles = Lists.newArrayList();
        titles.add("id");
        titles.add("姓名");
        titles.add("age");
        titles.add("birthday");
        titles.add("gender");
        titles.add("date");
        List<Map<String, Object>> values = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", i + 1D);
            map.put("姓名", "test_" + i);
            map.put("age", i * 1.5);
            map.put("gender", "man");
            map.put("birthday", new Date());
            map.put("date",  Calendar.getInstance());
            values.add(map);
        }
        System.out.println(ExcelWriteUtil.writerExcel(path, name, titles, values));
    }

}
