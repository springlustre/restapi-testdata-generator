package com.springlustre.tools.generator.util;

import org.testng.annotations.Test;
import org.testng.collections.Lists;
import org.testng.collections.Maps;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RegexUtilTest {

    @Test
    public void test(){
        String reg = "[0-3]([a-c]|[e-g]{3,8})";
        System.out.println(RegexUtil.getInstance().genDataByReg(reg));
    }

}
