package com.springlustre.tools.generator.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * databaseTable:TestDataTemplete
 */
public class TestDataTemplete implements Serializable{

    /**
     *    序号
     */
    private int index;

    /**
     *   参数
     */
    private String param;

    /**
     *   参数中文名称
     */
    private String paramName;

    /**
     *   参数类型，只能是：string，int，long，double
     */
    private String paramType;

    /**
     *   字符长度，范围[0,65535]
     */
    private int stringLength;

    /**
     *   "所属种类
     * 1：时间戳
     * 2：银行卡
     * 3：地址
     * 4：手机号
     * 5：中文姓名
     * 6：邮箱
     * 7：英文姓名"
     */
    private String category;

    /**
     *   出现要求，只能Y或N
     */
    private String require;


    /**
     * "来源类别
     * 1：来源于文件
     * 2：来源于其它sheet页的枚举
     * 3：来源于其它sheet页的接口文档，json格式"
     */
    private String resourceType;

    /**
     *  可以来源于某个文件，或者来源于枚举
     */
    private String resource;

    /**
     * *  枚举：使用竖线|分割"
     */
    private String paramEnum;

    /**
     *   备注
     */
    private String remark;

    private String paramRule;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public int getStringLength() {
        return stringLength;
    }

    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRequire() {
        return require;
    }

    public void setRequire(String require) {
        this.require = require;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getParamEnum() {
        return paramEnum;
    }

    public void setParamEnum(String paramEnum) {
        this.paramEnum = paramEnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParamRule() {
        return paramRule;
    }

    public void setParamRule(String paramRule) {
        this.paramRule = paramRule;
    }
}