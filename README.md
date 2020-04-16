# dbWriter

自动代码生成器v0.11.

#####编写目的：

公司项目中用到很多CURD操作，且有代码的格式、注释信息有各种要求，使用的公司自己的框架。网上其他的代码生成器格式死板，不太好用，为了省去重复的操作，
把时间花到有意义的地方，编写了这个小工具，用于生成代码和脚本。

有以下功能：

1.可以输出如下代码：
- JavaBean
- Service接口及实现类
- Dao接口即实现类
- Ibatis的XML文件
- 可重复执行的建表脚本、新增字段脚本、修改字段类型脚本
- 可重复执行的数据插入脚本
- 根据自定义模版实现其他的大量重复代码（例如Controller、Mysql脚本、js、等等）

2.输出的代码可以基于以下几种模式
- 基于Excel全新生成代码和脚本：新建表时在Excel中编辑，工具读取Excel中的建表信息生成所有代码
- 基于数据库连接全新生成代码和脚本，工具读取数据库信息生成所有代码
- 表修改生成脚本：修改数据库中的表，在Excel中编辑，生成新增/修改字段的脚本
- 基于数据库和JavaBean：自动生成映射关系，然后生成所有代码和脚本
- 基于数据库生成数据插入脚本：可重复执行
- 自定义模版，根据模版生成所需要的各种代码（工具包含了Oracle + iBatis的脚本）

3.生成的代码目录
cdWriter                                                                                      <br />
│  0_202004161100.csv 用JavaBean和数据库生成的映射关系                                        <br />
│  0_202004161138.csv 用JavaBean和数据库生成的映射关系                                        <br />
│  1.xlsx 工具基于的文档                                                                      <br />
│                                                                                             <br />
├─in                                                                                          <br />
│      AccountApply.class 导入的JavaBean编译后的文件                                          <br />
│      AccountApply.java 自行导入的JavaBean                                                   <br />
│                                                                                             <br />
├─out 命名规则可以自定义                                                                      <br />
│  │  AccountApply.java 生成的JavaBean                                                        <br />
│  │  AccountApplyDao.java 生成的Dao                                                          <br />
│  │  AccountApplyDaoImpl.java 生成的DaoImpl                                                  <br />
│  │  AccountApplyScope.java 生成的查询条件Scope                                              <br />
│  │  AccountApplyService.java 生成的Service                                                  <br />
│  │  AccountApplyServiceImpl.java 生成的ServiceImpl                                          <br />
│  │  AccountApplyView.java 生成的VO                                                          <br />
│  │                                                                                          <br />
│  ├─db                                                                                       <br />
│  │  │  AIMS_ACCOUNT_APPLY.TAB 生成的建表语句                                                <br />
│  │  │  AIMS_ACCOUNT_APPLY_SEQ.PDC 建表生成的主键序列可不生成                                <br />
│  │  │                                                                                       <br />
│  │  └─patch                                                                                 <br />
│  │          AIMS_ACCOUNT_APPLY2.TAB 基于自定义模版文件生成的另一种格式的文件                <br />
│  │                                                                                          <br />
│  ├─sqls                                                                                     <br />
│  │  │  AIMS_ACCOUNT_APPLY.SQL 自动生成的表的可插入脚本，取前1000行                          <br />
│  │  │                                                                                       <br />
│  │  └─cust                                                                                  <br />
│  │          AIMS_ACCOUNT_APPLY.SQL 根据自定义SQL和主键组合生成的数据插入可重复执行脚本      <br />
│  │                                                                                          <br />
│  ├─strait 这个目录是自定义模版生成的                                                        <br />
│  │      AIMS_ACCOUNT_APPLY.TAB                                                              <br />
│  │      AIMS_ACCOUNT_APPLY_SEQ.PDC                                                          <br />
│  │                                                                                          <br />
│  └─xml                                                                                      <br />
│          AIMS_AccountApply.xml ibatis脚本                                                   <br />
│          AIMS_AccountApply2.xml 自定义的ibatis脚本，因为templet的目录和默认生成的一样       <br />
│                                                                                             <br />
└─templet                                                                                     <br />
    ├─db                                                                                      <br />
    │      $table{tableName}.TAB.templet 手动加的模版，命名规则见文件名                       <br />
    │      $table{tableName}_SEQ.PDC.templet 手动加的模版，命名规则见文件名                   <br />
    │                                                                                         <br />
    └─xml                                                                                     <br />
            $common{appNoUpper}_$table{entityName}2.xml.templet 手动加的模版，命名规则见文件名<br />
            
4.windows下可直接执行，需要jre

5.demo

```xml

<?xml version="1.0" encoding="GBK"?>
<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://ibatis.apache.org/dtd/sql-map-2.dtd">
<sqlMap namespace="AIMS.AccountApplyDao">

    <!-- 分页 start -->
    <sql id="pagingStartSql">
        <isNotNull property="paging">
            SELECT * FROM (SELECT N.*,ROWNUM AS R
            FROM (
        </isNotNull>
    </sql>
    
    <!-- 分页 end -->
    <sql id="pagingEndSql">
        <isNotNull property="paging">
            ) N WHERE ROWNUM <![CDATA[<=]]> (#paging.endRow#)) T
            WHERE T.R > #paging.startRow#
        </isNotNull>
    </sql>
    
    <!-- 查询 账户申请工单表公共SQL-->
    <sql id="queryAccountApplyScopeSQL">
        <isNotEmpty property="fmId" prepend="and">
            AAA.FMID = #fmId#
        </isNotEmpty>
       ...
        <isNotEmpty property="abolishmentFlag" prepend="and">
            AAA.ABOLISHMENT_FLAG = #abolishmentFlag#
        </isNotEmpty>
        <isNotEmpty property="isSendN9" prepend="and">
            AAA.IS_SEND_N9 = #isSendN9#
        </isNotEmpty>
    </sql>
    
    <!-- 显示 账户申请工单表公共SQL-->
    <sql id="showAccountApplyViewSQL">
        AAA.FMID AS "fmId",
        AAA.APPLYID AS "applyId",
    	...
        AAA.ABOLISHMENT_FLAG AS "abolishmentFlag",
        AAA.IS_SEND_N9 AS "isSendN9"
    </sql>

    <!-- 新增账户申请工单表 -->
    <insert id="saveAccountApply" parameterClass="com.nstc.aims.model.AccountApply">
        <selectKey resultClass="java.lang.Integer" keyProperty="fmId">
            SELECT AIMS_ACCOUNT_APPLY_SEQ.NEXTVAL AS fmId FROM DUAL
        </selectKey>
        INSERT INTO AIMS_ACCOUNT_APPLY (
            FMID,
            APPLYID,
            ASSID,
          	...
            IS_SEND_N9
        ) VALUES(
            #fmId#,
            #applyId#,
            #assId#,
            #accountId#,
            #cltNo#,
         	...
            #isSendN9#
        )
    </insert>

    <!-- 修改账户申请工单表 -->
    <update id="updateAccountApply" parameterClass="com.nstc.aims.model.AccountApply">
        UPDATE AIMS_ACCOUNT_APPLY AAA SET
        AAA.FMID = #fmId#
        <isNotNull prepend="," property="applyId">
            AAA.APPLYID = #applyId#
        </isNotNull>
       	...
        <isNotNull prepend="," property="isSendN9">
            AAA.IS_SEND_N9 = #isSendN9#
        </isNotNull>
        WHERE AAA.FMID = #fmId#
    </update>

    <!-- 查询账户申请工单表列表 -->
    <select id="getAccountApplyList" parameterClass="com.nstc.aims.model.scope.AccountApplyScope" resultClass="com.nstc.aims.model.view.AccountApplyView">
        SELECT
        <include refid="showAccountApplyViewSQL" />
        FROM AIMS_ACCOUNT_APPLY AAA WHERE 1 = 1
        <include refid="queryAccountApplyScopeSQL" />
        ORDER BY AAA.FMID ASC
    </select>

    <!-- 根据条件查询账户申请工单表View的第一条记录  -->
    <select id="getAccountApplyByScope" parameterClass="com.nstc.aims.model.scope.AccountApplyScope" resultClass="com.nstc.aims.model.view.AccountApplyView">
        SELECT
        <include refid="showAccountApplyViewSQL" />
        FROM AIMS_ACCOUNT_APPLY AAA WHERE 1 = 1
        <include refid="queryAccountApplyScopeSQL" />
        AND ROWNUM = 1
    </select>
    
    <!-- 根据Id集合删除账户申请工单表 -->
    <delete id="deleteAccountApplyByIds" parameterClass="java.util.ArrayList">
        DELETE FROM AIMS_ACCOUNT_APPLY AAA WHERE  AAA.FMID IN
        <iterate open="(" close=")" conjunction=",">  
            #ids[]#  
        </iterate>
    </delete>   

    <!-- 查询账户申请工单表记录数 -->
    <select id="getAccountApplyPageCount" parameterClass="com.nstc.aims.model.scope.AccountApplyScope" resultClass="java.lang.Integer">
        SELECT
            COUNT(0)
        FROM AIMS_ACCOUNT_APPLY AAA WHERE 1 = 1
        <include refid="queryAccountApplyScopeSQL" />
    </select>         
    
    <!-- 查询账户申请工单表分页列表 -->
    <select id="getAccountApplyPageList" parameterClass="com.nstc.aims.model.scope.AccountApplyScope" resultClass="com.nstc.aims.model.view.AccountApplyView">
        <include refid="pagingStartSql" />
        SELECT
        <include refid="showAccountApplyViewSQL" />
        FROM AIMS_ACCOUNT_APPLY AAA WHERE 1 = 1
        <include refid="queryAccountApplyScopeSQL" />
        ORDER BY AAA.FMID ASC
        <include refid="pagingEndSql" />
    </select>

    <!-- 删除账户申请工单表 -->
    <delete id="deleteAccountApply" parameterClass="com.nstc.aims.model.scope.AccountApplyScope">
        DELETE AIMS_ACCOUNT_APPLY AAA WHERE 1 = 1
        <include refid="queryAccountApplyScopeSQL" />
    </delete> 
    
    <!-- 保存或修改账户申请工单表集合 -->  
    <update id="saveOrUpdateAccountApplyList" parameterClass="java.util.ArrayList">
        MERGE INTO AIMS_ACCOUNT_APPLY AAA 
        USING
        <iterate open="(" close=") A" conjunction="UNION">  
            SELECT
            <!-- 主键为空时候返回字符串，与原类型不匹配，非字符串类型都需要做转换 -->
            DECODE(#list[].fmId#,NULL,NULL,#list[].fmId#) AS FMID,
            DECODE(#list[].applyId#,NULL,NULL,#list[].applyId#) AS APPLYID,
            ...
            DECODE(#list[].abolishmentFlag#,NULL,NULL,#list[].abolishmentFlag#) AS ABOLISHMENT_FLAG,
            DECODE(#list[].isSendN9#,NULL,NULL,#list[].isSendN9#) AS IS_SEND_N9
            FROM DUAL
        </iterate>
        ON (A.FMID = AAA.FMID)
        WHEN MATCHED THEN
            UPDATE SET
                AAA.APPLYID = A.APPLYID,
                AAA.ASSID = A.ASSID,
              	...
                AAA.ABOLISHMENT_FMID = A.ABOLISHMENT_FMID,
                AAA.ABOLISHMENT_REASON = A.ABOLISHMENT_REASON,
                AAA.ABOLISHMENT_FLAG = A.ABOLISHMENT_FLAG,
                AAA.IS_SEND_N9 = A.IS_SEND_N9
        WHEN NOT MATCHED THEN 
            INSERT (
            FMID,
            APPLYID,
         	...
            ABOLISHMENT_REASON,
            ABOLISHMENT_FLAG,
            IS_SEND_N9
            ) VALUES (
            AIMS_ACCOUNT_APPLY_SEQ.NEXTVAL,
            A.APPLYID,
            A.ASSID,
          	...
            A.IS_SEND_N9
            )    
    </update>
    
    <!-- 保存或修改账户申请工单表实体 -->  
    <update id="saveOrUpdateAccountApply" parameterClass="com.nstc.aims.model.AccountApply">
        MERGE INTO AIMS_ACCOUNT_APPLY AAA
        USING DUAL
            ON (AAA.FMID = #fmId#)
        WHEN MATCHED THEN
        UPDATE
            SET
            AAA.APPLYID = #applyId#,
            AAA.ASSID = #assId#,
            ...
            AAA.IS_SEND_N9 = #isSendN9#
        WHEN NOT MATCHED THEN
            INSERT (
                FMID,
                APPLYID,
              	...
                ABOLISHMENT_FLAG,
                IS_SEND_N9
            ) 
            VALUES (
                AIMS_ACCOUNT_APPLY_SEQ.NEXTVAL,
                #applyId#,
                #assId#,
                ...
                #isSendN9#
            )
    </update>
    
</sqlMap>

```
```sql
DECLARE
    CNT INTEGER;
BEGIN
    SELECT COUNT(0) INTO CNT FROM USER_ALL_TABLES
    WHERE TABLE_NAME = UPPER('G_REMIND_BUSS');
    IF CNT = 0 THEN
    EXECUTE IMMEDIATE 'CREATE TABLE G_REMIND_BUSS(
        "RB_ID" NUMBER ,
        "BUSS_CODE" VARCHAR2(64) NOT NULL,
        "BUSS_NAME" VARCHAR2(64) NOT NULL,
        "REMARKS" VARCHAR2(128) ,
        "ORDER_NUM" NUMBER DEFAULT ON NULL 0 NOT NULL,
        "APPNO" VARCHAR2(32) ,
        "CREATE_TIME" DATE DEFAULT ON NULL SYSDATE ,
        "UPDATE_TIME" DATE DEFAULT ON NULL SYSDATE 
        )';
    EXECUTE IMMEDIATE 'COMMENT ON TABLE G_REMIND_BUSS IS ''提醒表''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."RB_ID" IS ''主键''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."BUSS_CODE" IS ''提醒编号''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."BUSS_NAME" IS ''提醒名称''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."REMARKS" IS ''备注''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."ORDER_NUM" IS ''排序''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."APPNO" IS ''模块编号''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."CREATE_TIME" IS ''创建时间''';
    EXECUTE IMMEDIATE 'COMMENT ON COLUMN G_REMIND_BUSS."UPDATE_TIME" IS ''修改时间''';
    END IF;
END;
/

DECLARE
    CNT INTEGER;
BEGIN
    SELECT COUNT(0) INTO CNT FROM USER_CONSTRAINTS
    WHERE CONSTRAINT_NAME = UPPER('G_REMIND_BUSS_PK');
    IF CNT = 0 THEN
    EXECUTE IMMEDIATE 'ALTER TABLE G_REMIND_BUSS ADD CONSTRAINT G_REMIND_BUSS_PK PRIMARY KEY("RB_ID")';
    END IF;
END;
/

```

不一一列举了
作者QQ：436081860

