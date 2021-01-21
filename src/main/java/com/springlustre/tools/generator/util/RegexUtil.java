package com.springlustre.tools.generator.util;

import com.mifmif.common.regex.Generex;
import com.mifmif.common.regex.util.Iterator;

import java.util.List;

/**
 * @author wangchunze
 * 2020.11.17
 */

public class RegexUtil {

    private static RegexUtil instance = new RegexUtil();
    public static RegexUtil getInstance() {
        return instance;
    }

    public Object genDataByReg(String reg){
//        System.out.println(reg);
        Generex generex = new Generex(reg);
        // Generate random String
        String randomStr = generex.random();
//        System.out.println(randomStr);// a random value from the previous String list
//
//        // generate the second String in lexicographical order that match the given Regex.
//        String secondString = generex.getMatchedString(2);
//        System.out.println(secondString);// it print '0b'
        return randomStr;
    }

}
