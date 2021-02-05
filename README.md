

### Restful接口测试数据生成器

Java实现的Restful接口测试数据生成器

### 功能介绍

1、根据接口文档模板生成测试数据：

   可以是单条数据，也可以指定书目生成批量数据，数据可以是json格式，也可以是每个字段作为excel的一列。

2、根据字段的种类，可以生成指定格式的数据：

   目前支持的种类包括：1：时间戳、2：银行卡、3：地址、4：手机号、5：中文姓名、6：邮箱、7：英文姓名、8：身份证号:。

3、支持正则表达式：

   如果设置了正则表达式，则可以根据正则生成对应的数据

4、支持字段的嵌套

   对于接口字段的嵌套，可以支持生成多级json数据,可以是json格式，也可以是json序列化后的格式。

5、示例

生成的单条测试数据如下：

```JSON
{
  "OTHER_INFO": "{\"MOBILE_ADDR\":\"山东省济宁市恿绕谓路1057号孽副小区11单元708室\",\"DATA2\":\"{\\\"CERT_ADDR\\\":\\\"河南省驻马店市检肚寇路3198号辑疥小区5单元931室\\\"}\",\"DATA1\":\"{\\\"MOBILE_NUM\\\":\\\"15605274686\\\"}\",\"MOBILE_NUM\":\"13164114082\"}",
  "APP_NO": "CFYH1008896936",
  "CHANNEL_SOURCE": "APP",
  "BUSINESS_CODE": "TEST01",
  "STEP_TYPE": "003",
  "SESSION_ID": "VFXP40500338931080604227",
  "CUST_INFO": "{\"GENDER_CD\":\"1\",\"NAME_EN\":\"Lorin\",\"MAIL\":\"ivuimoxste@sstaw.w4y\",\"CERT_TYPE\":\"5\",\"CERT_NO\":\"717561197812233818\",\"IDEN_VALID_DT\":\"99991231\",\"CUST_NAME\":\"唐橙蛀\",\"AGE\":\"5\",\"MOBILE\":\"13159666613\"}",
  "SCENARIO_INFO": "{\"ADDRESS\":\"内蒙古自治区呼伦贝尔市洲们托路2069号混牙食小区13单元2264室\",\"CARD_NUM\":\"6222862258675117\"}",
  "APPLY_TIME": "1612512389129"
}
```

或者如下：

```JSON
{
  "OTHER_INFO": {
    "MOBILE_ADDR": "甘肃省庆阳市抢兽幂路4267号暑耍小区17单元646室",
    "DATA2": {
      "CERT_ADDR": "河南省商丘市茂铁峨路231号悉练翰小区1单元166室"
    },
    "DATA1": {
      "MOBILE_NUM": "14521010521"
    },
    "MOBILE_NUM": "14740305772"
  },
  "APP_NO": "OXEF6780053205",
  "CHANNEL_SOURCE": "APP",
  "BUSINESS_CODE": "TEST01",
  "STEP_TYPE": "003",
  "SESSION_ID": "HQWE72712356367251419852",
  "CUST_INFO": {
    "GENDER_CD": "1",
    "NAME_EN": "Alan",
    "MAIL": "ruqbprnh7c@d6uhk.une",
    "CERT_TYPE": "2",
    "CERT_NO": "145464198511212044",
    "IDEN_VALID_DT": "99991231",
    "CUST_NAME": "贾林",
    "AGE": "1",
    "MOBILE": "18739114266"
  },
  "SCENARIO_INFO": {
    "ADDRESS": "四川省泸州市涎殖路2111号撑贿小区9单元611室",
    "CARD_NUM": "6225080730231147"
  },
  "APPLY_TIME": "1612515320813"
}
```

### 使用方法

1、接口模板的准备，参考resources下的data_templete.xlsx。

目前的表头有如下一些类型：
|   序号   |  字段名    |   备注   |
| ---- | ---- | ---- |
|1 | index: |序号|
|2|param：|参数|
|3|paramName：|参数中文名称|
|4|paramType：|参数类型，只能是：string，int，long，double|
|5|require：|出现要求，只能Y或N|
|6|paramEnum：|枚举，使用竖线\|分割|
|7|remark：|备注|
|8|stringLength：|字符长度，范围[0,65535]|
|9|category：|所属种类：包括1：时间戳 2：银行卡 3：地址 4：手机号 5：中文姓名 6：邮箱 7：英文姓名 8：身份证号|
|10|paramRule：|参数规则，标准正则表达式|
|11|resourceType：|来源类别、1：来源于文件、2：来源于其它sheet页的枚举、3：来源于其它sheet页的接口文档，json格式。目前只支持3|
|12|resource：|可以来源于其它SHEET页，此处为其它SHEET页的名称|


2、参考 

`src/main/java/com/springlustre/tools/generator/example/GenDataExample.java`

用IDAE打开，直接右键run即可测试




### The RESTful interface tests the data generator

A RESTful interface implemented in Java tests the data generator

### feature introduction

1. Generate test data according to interface document template:

It can be a single piece of data, or you can specify a bibliography to generate batch data, the data can be in JSON format, or each field can be a column in Excel.

2, According to the type of field, you can generate data in the specified format:

Currently supported categories include: 1: timestamp, 2: bank card, 3: address, 4: mobile phone number, 5: Chinese name, 6: email, 7: English name, 8: ID number.

Support for regular expressions:

If the regular expression is set, the corresponding data can be generated based on the regular expression

4. Support for field nesting

For the nesting of interface fields, you can support the generation of multiple levels of JSON data, either in JSON format or in JSON serialized format.

5, sample

The generated single test data is as follows:

```JSON
{
  "OTHER_INFO": "{\"MOBILE_ADDR\":\"山东省济宁市恿绕谓路1057号孽副小区11单元708室\",\"DATA2\":\"{\\\"CERT_ADDR\\\":\\\"河南省驻马店市检肚寇路3198号辑疥小区5单元931室\\\"}\",\"DATA1\":\"{\\\"MOBILE_NUM\\\":\\\"15605274686\\\"}\",\"MOBILE_NUM\":\"13164114082\"}",
  "APP_NO": "CFYH1008896936",
  "CHANNEL_SOURCE": "APP",
  "BUSINESS_CODE": "TEST01",
  "STEP_TYPE": "003",
  "SESSION_ID": "VFXP40500338931080604227",
  "CUST_INFO": "{\"GENDER_CD\":\"1\",\"NAME_EN\":\"Lorin\",\"MAIL\":\"ivuimoxste@sstaw.w4y\",\"CERT_TYPE\":\"5\",\"CERT_NO\":\"717561197812233818\",\"IDEN_VALID_DT\":\"99991231\",\"CUST_NAME\":\"唐橙蛀\",\"AGE\":\"5\",\"MOBILE\":\"13159666613\"}",
  "SCENARIO_INFO": "{\"ADDRESS\":\"内蒙古自治区呼伦贝尔市洲们托路2069号混牙食小区13单元2264室\",\"CARD_NUM\":\"6222862258675117\"}",
  "APPLY_TIME": "1612512389129"
}
```

OR：

```JSON
{
  "OTHER_INFO": {
    "MOBILE_ADDR": "甘肃省庆阳市抢兽幂路4267号暑耍小区17单元646室",
    "DATA2": {
      "CERT_ADDR": "河南省商丘市茂铁峨路231号悉练翰小区1单元166室"
    },
    "DATA1": {
      "MOBILE_NUM": "14521010521"
    },
    "MOBILE_NUM": "14740305772"
  },
  "APP_NO": "OXEF6780053205",
  "CHANNEL_SOURCE": "APP",
  "BUSINESS_CODE": "TEST01",
  "STEP_TYPE": "003",
  "SESSION_ID": "HQWE72712356367251419852",
  "CUST_INFO": {
    "GENDER_CD": "1",
    "NAME_EN": "Alan",
    "MAIL": "ruqbprnh7c@d6uhk.une",
    "CERT_TYPE": "2",
    "CERT_NO": "145464198511212044",
    "IDEN_VALID_DT": "99991231",
    "CUST_NAME": "贾林",
    "AGE": "1",
    "MOBILE": "18739114266"
  },
  "SCENARIO_INFO": {
    "ADDRESS": "四川省泸州市涎殖路2111号撑贿小区9单元611室",
    "CARD_NUM": "6225080730231147"
  },
  "APPLY_TIME": "1612515320813"
}
```

### use method

1. To prepare the interface template, refer to data_templete.xlsx under Resources.

2.reference

`src/main/java/com/springlustre/tools/generator/example/GenDataExample.java`

Open with IDAE, directly right - click run can be tested