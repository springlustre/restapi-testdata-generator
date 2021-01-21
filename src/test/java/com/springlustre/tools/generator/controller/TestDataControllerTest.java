package com.springlustre.tools.generator.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.springlustre.tools.generator.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

public class TestDataControllerTest {

    @Autowired
    TestDataController testDataController;

    @Test
    public void test2() throws Exception{
        Object res = testDataController.genTestData(Maps.newHashMap());
        System.out.println(res);
    }

}
