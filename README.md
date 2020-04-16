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
        <isNotEmpty property="applyId" prepend="and">
            AAA.APPLYID = #applyId#
        </isNotEmpty>
        <isNotEmpty property="assId" prepend="and">
            AAA.ASSID = #assId#
        </isNotEmpty>
        <isNotEmpty property="accountId" prepend="and">
            AAA.ACCOUNTID = #accountId#
        </isNotEmpty>
        <isNotEmpty property="cltNo" prepend="and">
            AAA.CLTNO = #cltNo#
        </isNotEmpty>
        <isNotEmpty property="natureId" prepend="and">
            AAA.NATUREID = #natureId#
        </isNotEmpty>
        <isNotEmpty property="usageId" prepend="and">
            AAA.USAGEID = #usageId#
        </isNotEmpty>
        <isNotEmpty property="currencyNo" prepend="and">
            AAA.CURRENCYNO = #currencyNo#
        </isNotEmpty>
        <isNotEmpty property="isAbroad" prepend="and">
            AAA.ISABROAD = #isAbroad#
        </isNotEmpty>
        <isNotEmpty property="openReason" prepend="and">
            AAA.OPENREASON = #openReason#
        </isNotEmpty>
        <isNotEmpty property="applyDate" prepend="and">
            AAA.APPLYDATE = #applyDate#
        </isNotEmpty>
        <isNotEmpty property="cancelRemark" prepend="and">
            AAA.CANCELREMARK = #cancelRemark#
        </isNotEmpty>
        <isNotEmpty property="cancelReason" prepend="and">
            AAA.CANCELREASON = #cancelReason#
        </isNotEmpty>
        <isNotEmpty property="fileRemark" prepend="and">
            AAA.FILEREMARK = #fileRemark#
        </isNotEmpty>
        <isNotEmpty property="reason" prepend="and">
            AAA.REASON = #reason#
        </isNotEmpty>
        <isNotEmpty property="create_time" prepend="and">
            AAA.CREATE_TIME = #create_time#
        </isNotEmpty>
        <isNotEmpty property="update_time" prepend="and">
            AAA.UPDATE_TIME = #update_time#
        </isNotEmpty>
        <isNotEmpty property="isRegister" prepend="and">
            AAA.ISREGISTER = #isRegister#
        </isNotEmpty>
        <isNotEmpty property="cltName" prepend="and">
            AAA.CLT_NAME = #cltName#
        </isNotEmpty>
        <isNotEmpty property="contactPerson" prepend="and">
            AAA.CONTACT_PERSON = #contactPerson#
        </isNotEmpty>
        <isNotEmpty property="contactTel" prepend="and">
            AAA.CONTACT_TEL = #contactTel#
        </isNotEmpty>
        <isNotEmpty property="basicAccountNo" prepend="and">
            AAA.BASIC_ACCOUNT_NO = #basicAccountNo#
        </isNotEmpty>
        <isNotEmpty property="basicAccountBankname" prepend="and">
            AAA.BASIC_ACCOUNT_BANKNAME = #basicAccountBankname#
        </isNotEmpty>
        <isNotEmpty property="authorizedPersonName" prepend="and">
            AAA.AUTHORIZED_PERSON_NAME = #authorizedPersonName#
        </isNotEmpty>
        <isNotEmpty property="authorizedPersonId" prepend="and">
            AAA.AUTHORIZED_PERSON_ID = #authorizedPersonId#
        </isNotEmpty>
        <isNotEmpty property="legalPerson" prepend="and">
            AAA.LEGAL_PERSON = #legalPerson#
        </isNotEmpty>
        <isNotEmpty property="legalIdCard" prepend="and">
            AAA.LEGAL_ID_CARD = #legalIdCard#
        </isNotEmpty>
        <isNotEmpty property="namesSeal" prepend="and">
            AAA.NAMES_SEAL = #namesSeal#
        </isNotEmpty>
        <isNotEmpty property="financialSeal" prepend="and">
            AAA.FINANCIAL_SEAL = #financialSeal#
        </isNotEmpty>
        <isNotEmpty property="officialSeal" prepend="and">
            AAA.OFFICIAL_SEAL = #officialSeal#
        </isNotEmpty>
        <isNotEmpty property="adminArea" prepend="and">
            AAA.ADMIN_AREA = #adminArea#
        </isNotEmpty>
        <isNotEmpty property="isSealLegalPerson" prepend="and">
            AAA.IS_SEAL_LEGAL_PERSON = #isSealLegalPerson#
        </isNotEmpty>
        <isNotEmpty property="isInternalAccount" prepend="and">
            AAA.IS_INTERNAL_ACCOUNT = #isInternalAccount#
        </isNotEmpty>
        <isNotEmpty property="needOpenEbank" prepend="and">
            AAA.NEED_OPEN_EBANK = #needOpenEbank#
        </isNotEmpty>
        <isNotEmpty property="needOnline" prepend="and">
            AAA.NEED_ONLINE = #needOnline#
        </isNotEmpty>
        <isNotEmpty property="belongFta" prepend="and">
            AAA.BELONG_FTA = #belongFta#
        </isNotEmpty>
        <isNotEmpty property="cltAddr" prepend="and">
            AAA.CLT_ADDR = #cltAddr#
        </isNotEmpty>
        <isNotEmpty property="accountName" prepend="and">
            AAA.ACCOUNT_NAME = #accountName#
        </isNotEmpty>
        <isNotEmpty property="actualOfficeAddress" prepend="and">
            AAA.ACTUAL_OFFICE_ADDRESS = #actualOfficeAddress#
        </isNotEmpty>
        <isNotEmpty property="ctId" prepend="and">
            AAA.CTID = #ctId#
        </isNotEmpty>
        <isNotEmpty property="erpId" prepend="and">
            AAA.ERP_ID = #erpId#
        </isNotEmpty>
        <isNotEmpty property="erpSysNo" prepend="and">
            AAA.ERP_SYS_NO = #erpSysNo#
        </isNotEmpty>
        <isNotEmpty property="abolishmentFmid" prepend="and">
            AAA.ABOLISHMENT_FMID = #abolishmentFmid#
        </isNotEmpty>
        <isNotEmpty property="abolishmentReason" prepend="and">
            AAA.ABOLISHMENT_REASON = #abolishmentReason#
        </isNotEmpty>
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
        AAA.ASSID AS "assId",
        AAA.ACCOUNTID AS "accountId",
        AAA.CLTNO AS "cltNo",
        AAA.NATUREID AS "natureId",
        AAA.USAGEID AS "usageId",
        AAA.CURRENCYNO AS "currencyNo",
        AAA.ISABROAD AS "isAbroad",
        AAA.OPENREASON AS "openReason",
        AAA.APPLYDATE AS "applyDate",
        AAA.CANCELREMARK AS "cancelRemark",
        AAA.CANCELREASON AS "cancelReason",
        AAA.FILEREMARK AS "fileRemark",
        AAA.REASON AS "reason",
        AAA.CREATE_TIME AS "create_time",
        AAA.UPDATE_TIME AS "update_time",
        AAA.ISREGISTER AS "isRegister",
        AAA.CLT_NAME AS "cltName",
        AAA.CONTACT_PERSON AS "contactPerson",
        AAA.CONTACT_TEL AS "contactTel",
        AAA.BASIC_ACCOUNT_NO AS "basicAccountNo",
        AAA.BASIC_ACCOUNT_BANKNAME AS "basicAccountBankname",
        AAA.AUTHORIZED_PERSON_NAME AS "authorizedPersonName",
        AAA.AUTHORIZED_PERSON_ID AS "authorizedPersonId",
        AAA.LEGAL_PERSON AS "legalPerson",
        AAA.LEGAL_ID_CARD AS "legalIdCard",
        AAA.NAMES_SEAL AS "namesSeal",
        AAA.FINANCIAL_SEAL AS "financialSeal",
        AAA.OFFICIAL_SEAL AS "officialSeal",
        AAA.ADMIN_AREA AS "adminArea",
        AAA.IS_SEAL_LEGAL_PERSON AS "isSealLegalPerson",
        AAA.IS_INTERNAL_ACCOUNT AS "isInternalAccount",
        AAA.NEED_OPEN_EBANK AS "needOpenEbank",
        AAA.NEED_ONLINE AS "needOnline",
        AAA.BELONG_FTA AS "belongFta",
        AAA.CLT_ADDR AS "cltAddr",
        AAA.ACCOUNT_NAME AS "accountName",
        AAA.ACTUAL_OFFICE_ADDRESS AS "actualOfficeAddress",
        AAA.CTID AS "ctId",
        AAA.ERP_ID AS "erpId",
        AAA.ERP_SYS_NO AS "erpSysNo",
        AAA.ABOLISHMENT_FMID AS "abolishmentFmid",
        AAA.ABOLISHMENT_REASON AS "abolishmentReason",
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
            ACCOUNTID,
            CLTNO,
            NATUREID,
            USAGEID,
            CURRENCYNO,
            ISABROAD,
            OPENREASON,
            APPLYDATE,
            CANCELREMARK,
            CANCELREASON,
            FILEREMARK,
            REASON,
            CREATE_TIME,
            UPDATE_TIME,
            ISREGISTER,
            CLT_NAME,
            CONTACT_PERSON,
            CONTACT_TEL,
            BASIC_ACCOUNT_NO,
            BASIC_ACCOUNT_BANKNAME,
            AUTHORIZED_PERSON_NAME,
            AUTHORIZED_PERSON_ID,
            LEGAL_PERSON,
            LEGAL_ID_CARD,
            NAMES_SEAL,
            FINANCIAL_SEAL,
            OFFICIAL_SEAL,
            ADMIN_AREA,
            IS_SEAL_LEGAL_PERSON,
            IS_INTERNAL_ACCOUNT,
            NEED_OPEN_EBANK,
            NEED_ONLINE,
            BELONG_FTA,
            CLT_ADDR,
            ACCOUNT_NAME,
            ACTUAL_OFFICE_ADDRESS,
            CTID,
            ERP_ID,
            ERP_SYS_NO,
            ABOLISHMENT_FMID,
            ABOLISHMENT_REASON,
            ABOLISHMENT_FLAG,
            IS_SEND_N9
        ) VALUES(
            #fmId#,
            #applyId#,
            #assId#,
            #accountId#,
            #cltNo#,
            #natureId#,
            #usageId#,
            #currencyNo#,
            #isAbroad#,
            #openReason#,
            #applyDate#,
            #cancelRemark#,
            #cancelReason#,
            #fileRemark#,
            #reason#,
            #create_time#,
            #update_time#,
            #isRegister#,
            #cltName#,
            #contactPerson#,
            #contactTel#,
            #basicAccountNo#,
            #basicAccountBankname#,
            #authorizedPersonName#,
            #authorizedPersonId#,
            #legalPerson#,
            #legalIdCard#,
            #namesSeal#,
            #financialSeal#,
            #officialSeal#,
            #adminArea#,
            #isSealLegalPerson#,
            #isInternalAccount#,
            #needOpenEbank#,
            #needOnline#,
            #belongFta#,
            #cltAddr#,
            #accountName#,
            #actualOfficeAddress#,
            #ctId#,
            #erpId#,
            #erpSysNo#,
            #abolishmentFmid#,
            #abolishmentReason#,
            #abolishmentFlag#,
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
        <isNotNull prepend="," property="assId">
            AAA.ASSID = #assId#
        </isNotNull>
        <isNotNull prepend="," property="accountId">
            AAA.ACCOUNTID = #accountId#
        </isNotNull>
        <isNotNull prepend="," property="cltNo">
            AAA.CLTNO = #cltNo#
        </isNotNull>
        <isNotNull prepend="," property="natureId">
            AAA.NATUREID = #natureId#
        </isNotNull>
        <isNotNull prepend="," property="usageId">
            AAA.USAGEID = #usageId#
        </isNotNull>
        <isNotNull prepend="," property="currencyNo">
            AAA.CURRENCYNO = #currencyNo#
        </isNotNull>
        <isNotNull prepend="," property="isAbroad">
            AAA.ISABROAD = #isAbroad#
        </isNotNull>
        <isNotNull prepend="," property="openReason">
            AAA.OPENREASON = #openReason#
        </isNotNull>
        <isNotNull prepend="," property="applyDate">
            AAA.APPLYDATE = #applyDate#
        </isNotNull>
        <isNotNull prepend="," property="cancelRemark">
            AAA.CANCELREMARK = #cancelRemark#
        </isNotNull>
        <isNotNull prepend="," property="cancelReason">
            AAA.CANCELREASON = #cancelReason#
        </isNotNull>
        <isNotNull prepend="," property="fileRemark">
            AAA.FILEREMARK = #fileRemark#
        </isNotNull>
        <isNotNull prepend="," property="reason">
            AAA.REASON = #reason#
        </isNotNull>
        <isNotNull prepend="," property="create_time">
            AAA.CREATE_TIME = #create_time#
        </isNotNull>
        <isNotNull prepend="," property="update_time">
            AAA.UPDATE_TIME = #update_time#
        </isNotNull>
        <isNotNull prepend="," property="isRegister">
            AAA.ISREGISTER = #isRegister#
        </isNotNull>
        <isNotNull prepend="," property="cltName">
            AAA.CLT_NAME = #cltName#
        </isNotNull>
        <isNotNull prepend="," property="contactPerson">
            AAA.CONTACT_PERSON = #contactPerson#
        </isNotNull>
        <isNotNull prepend="," property="contactTel">
            AAA.CONTACT_TEL = #contactTel#
        </isNotNull>
        <isNotNull prepend="," property="basicAccountNo">
            AAA.BASIC_ACCOUNT_NO = #basicAccountNo#
        </isNotNull>
        <isNotNull prepend="," property="basicAccountBankname">
            AAA.BASIC_ACCOUNT_BANKNAME = #basicAccountBankname#
        </isNotNull>
        <isNotNull prepend="," property="authorizedPersonName">
            AAA.AUTHORIZED_PERSON_NAME = #authorizedPersonName#
        </isNotNull>
        <isNotNull prepend="," property="authorizedPersonId">
            AAA.AUTHORIZED_PERSON_ID = #authorizedPersonId#
        </isNotNull>
        <isNotNull prepend="," property="legalPerson">
            AAA.LEGAL_PERSON = #legalPerson#
        </isNotNull>
        <isNotNull prepend="," property="legalIdCard">
            AAA.LEGAL_ID_CARD = #legalIdCard#
        </isNotNull>
        <isNotNull prepend="," property="namesSeal">
            AAA.NAMES_SEAL = #namesSeal#
        </isNotNull>
        <isNotNull prepend="," property="financialSeal">
            AAA.FINANCIAL_SEAL = #financialSeal#
        </isNotNull>
        <isNotNull prepend="," property="officialSeal">
            AAA.OFFICIAL_SEAL = #officialSeal#
        </isNotNull>
        <isNotNull prepend="," property="adminArea">
            AAA.ADMIN_AREA = #adminArea#
        </isNotNull>
        <isNotNull prepend="," property="isSealLegalPerson">
            AAA.IS_SEAL_LEGAL_PERSON = #isSealLegalPerson#
        </isNotNull>
        <isNotNull prepend="," property="isInternalAccount">
            AAA.IS_INTERNAL_ACCOUNT = #isInternalAccount#
        </isNotNull>
        <isNotNull prepend="," property="needOpenEbank">
            AAA.NEED_OPEN_EBANK = #needOpenEbank#
        </isNotNull>
        <isNotNull prepend="," property="needOnline">
            AAA.NEED_ONLINE = #needOnline#
        </isNotNull>
        <isNotNull prepend="," property="belongFta">
            AAA.BELONG_FTA = #belongFta#
        </isNotNull>
        <isNotNull prepend="," property="cltAddr">
            AAA.CLT_ADDR = #cltAddr#
        </isNotNull>
        <isNotNull prepend="," property="accountName">
            AAA.ACCOUNT_NAME = #accountName#
        </isNotNull>
        <isNotNull prepend="," property="actualOfficeAddress">
            AAA.ACTUAL_OFFICE_ADDRESS = #actualOfficeAddress#
        </isNotNull>
        <isNotNull prepend="," property="ctId">
            AAA.CTID = #ctId#
        </isNotNull>
        <isNotNull prepend="," property="erpId">
            AAA.ERP_ID = #erpId#
        </isNotNull>
        <isNotNull prepend="," property="erpSysNo">
            AAA.ERP_SYS_NO = #erpSysNo#
        </isNotNull>
        <isNotNull prepend="," property="abolishmentFmid">
            AAA.ABOLISHMENT_FMID = #abolishmentFmid#
        </isNotNull>
        <isNotNull prepend="," property="abolishmentReason">
            AAA.ABOLISHMENT_REASON = #abolishmentReason#
        </isNotNull>
        <isNotNull prepend="," property="abolishmentFlag">
            AAA.ABOLISHMENT_FLAG = #abolishmentFlag#
        </isNotNull>
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
            DECODE(#list[].assId#,NULL,NULL,#list[].assId#) AS ASSID,
            DECODE(#list[].accountId#,NULL,NULL,#list[].accountId#) AS ACCOUNTID,
            #list[].cltNo# AS CLTNO,
            DECODE(#list[].natureId#,NULL,NULL,#list[].natureId#) AS NATUREID,
            DECODE(#list[].usageId#,NULL,NULL,#list[].usageId#) AS USAGEID,
            #list[].currencyNo# AS CURRENCYNO,
            #list[].isAbroad# AS ISABROAD,
            #list[].openReason# AS OPENREASON,
            #list[].applyDate# AS APPLYDATE,
            #list[].cancelRemark# AS CANCELREMARK,
            #list[].cancelReason# AS CANCELREASON,
            #list[].fileRemark# AS FILEREMARK,
            #list[].reason# AS REASON,
            #list[].create_time# AS CREATE_TIME,
            #list[].update_time# AS UPDATE_TIME,
            #list[].isRegister# AS ISREGISTER,
            #list[].cltName# AS CLT_NAME,
            #list[].contactPerson# AS CONTACT_PERSON,
            #list[].contactTel# AS CONTACT_TEL,
            #list[].basicAccountNo# AS BASIC_ACCOUNT_NO,
            #list[].basicAccountBankname# AS BASIC_ACCOUNT_BANKNAME,
            #list[].authorizedPersonName# AS AUTHORIZED_PERSON_NAME,
            #list[].authorizedPersonId# AS AUTHORIZED_PERSON_ID,
            #list[].legalPerson# AS LEGAL_PERSON,
            #list[].legalIdCard# AS LEGAL_ID_CARD,
            #list[].namesSeal# AS NAMES_SEAL,
            #list[].financialSeal# AS FINANCIAL_SEAL,
            #list[].officialSeal# AS OFFICIAL_SEAL,
            #list[].adminArea# AS ADMIN_AREA,
            DECODE(#list[].isSealLegalPerson#,NULL,NULL,#list[].isSealLegalPerson#) AS IS_SEAL_LEGAL_PERSON,
            DECODE(#list[].isInternalAccount#,NULL,NULL,#list[].isInternalAccount#) AS IS_INTERNAL_ACCOUNT,
            DECODE(#list[].needOpenEbank#,NULL,NULL,#list[].needOpenEbank#) AS NEED_OPEN_EBANK,
            DECODE(#list[].needOnline#,NULL,NULL,#list[].needOnline#) AS NEED_ONLINE,
            DECODE(#list[].belongFta#,NULL,NULL,#list[].belongFta#) AS BELONG_FTA,
            #list[].cltAddr# AS CLT_ADDR,
            #list[].accountName# AS ACCOUNT_NAME,
            #list[].actualOfficeAddress# AS ACTUAL_OFFICE_ADDRESS,
            DECODE(#list[].ctId#,NULL,NULL,#list[].ctId#) AS CTID,
            #list[].erpId# AS ERP_ID,
            #list[].erpSysNo# AS ERP_SYS_NO,
            DECODE(#list[].abolishmentFmid#,NULL,NULL,#list[].abolishmentFmid#) AS ABOLISHMENT_FMID,
            #list[].abolishmentReason# AS ABOLISHMENT_REASON,
            DECODE(#list[].abolishmentFlag#,NULL,NULL,#list[].abolishmentFlag#) AS ABOLISHMENT_FLAG,
            DECODE(#list[].isSendN9#,NULL,NULL,#list[].isSendN9#) AS IS_SEND_N9
            FROM DUAL
        </iterate>
        ON (A.FMID = AAA.FMID)
        WHEN MATCHED THEN
            UPDATE SET
                AAA.APPLYID = A.APPLYID,
                AAA.ASSID = A.ASSID,
                AAA.ACCOUNTID = A.ACCOUNTID,
                AAA.CLTNO = A.CLTNO,
                AAA.NATUREID = A.NATUREID,
                AAA.USAGEID = A.USAGEID,
                AAA.CURRENCYNO = A.CURRENCYNO,
                AAA.ISABROAD = A.ISABROAD,
                AAA.OPENREASON = A.OPENREASON,
                AAA.APPLYDATE = A.APPLYDATE,
                AAA.CANCELREMARK = A.CANCELREMARK,
                AAA.CANCELREASON = A.CANCELREASON,
                AAA.FILEREMARK = A.FILEREMARK,
                AAA.REASON = A.REASON,
                AAA.CREATE_TIME = A.CREATE_TIME,
                AAA.UPDATE_TIME = A.UPDATE_TIME,
                AAA.ISREGISTER = A.ISREGISTER,
                AAA.CLT_NAME = A.CLT_NAME,
                AAA.CONTACT_PERSON = A.CONTACT_PERSON,
                AAA.CONTACT_TEL = A.CONTACT_TEL,
                AAA.BASIC_ACCOUNT_NO = A.BASIC_ACCOUNT_NO,
                AAA.BASIC_ACCOUNT_BANKNAME = A.BASIC_ACCOUNT_BANKNAME,
                AAA.AUTHORIZED_PERSON_NAME = A.AUTHORIZED_PERSON_NAME,
                AAA.AUTHORIZED_PERSON_ID = A.AUTHORIZED_PERSON_ID,
                AAA.LEGAL_PERSON = A.LEGAL_PERSON,
                AAA.LEGAL_ID_CARD = A.LEGAL_ID_CARD,
                AAA.NAMES_SEAL = A.NAMES_SEAL,
                AAA.FINANCIAL_SEAL = A.FINANCIAL_SEAL,
                AAA.OFFICIAL_SEAL = A.OFFICIAL_SEAL,
                AAA.ADMIN_AREA = A.ADMIN_AREA,
                AAA.IS_SEAL_LEGAL_PERSON = A.IS_SEAL_LEGAL_PERSON,
                AAA.IS_INTERNAL_ACCOUNT = A.IS_INTERNAL_ACCOUNT,
                AAA.NEED_OPEN_EBANK = A.NEED_OPEN_EBANK,
                AAA.NEED_ONLINE = A.NEED_ONLINE,
                AAA.BELONG_FTA = A.BELONG_FTA,
                AAA.CLT_ADDR = A.CLT_ADDR,
                AAA.ACCOUNT_NAME = A.ACCOUNT_NAME,
                AAA.ACTUAL_OFFICE_ADDRESS = A.ACTUAL_OFFICE_ADDRESS,
                AAA.CTID = A.CTID,
                AAA.ERP_ID = A.ERP_ID,
                AAA.ERP_SYS_NO = A.ERP_SYS_NO,
                AAA.ABOLISHMENT_FMID = A.ABOLISHMENT_FMID,
                AAA.ABOLISHMENT_REASON = A.ABOLISHMENT_REASON,
                AAA.ABOLISHMENT_FLAG = A.ABOLISHMENT_FLAG,
                AAA.IS_SEND_N9 = A.IS_SEND_N9
        WHEN NOT MATCHED THEN 
            INSERT (
            FMID,
            APPLYID,
            ASSID,
            ACCOUNTID,
            CLTNO,
            NATUREID,
            USAGEID,
            CURRENCYNO,
            ISABROAD,
            OPENREASON,
            APPLYDATE,
            CANCELREMARK,
            CANCELREASON,
            FILEREMARK,
            REASON,
            CREATE_TIME,
            UPDATE_TIME,
            ISREGISTER,
            CLT_NAME,
            CONTACT_PERSON,
            CONTACT_TEL,
            BASIC_ACCOUNT_NO,
            BASIC_ACCOUNT_BANKNAME,
            AUTHORIZED_PERSON_NAME,
            AUTHORIZED_PERSON_ID,
            LEGAL_PERSON,
            LEGAL_ID_CARD,
            NAMES_SEAL,
            FINANCIAL_SEAL,
            OFFICIAL_SEAL,
            ADMIN_AREA,
            IS_SEAL_LEGAL_PERSON,
            IS_INTERNAL_ACCOUNT,
            NEED_OPEN_EBANK,
            NEED_ONLINE,
            BELONG_FTA,
            CLT_ADDR,
            ACCOUNT_NAME,
            ACTUAL_OFFICE_ADDRESS,
            CTID,
            ERP_ID,
            ERP_SYS_NO,
            ABOLISHMENT_FMID,
            ABOLISHMENT_REASON,
            ABOLISHMENT_FLAG,
            IS_SEND_N9
            ) VALUES (
            AIMS_ACCOUNT_APPLY_SEQ.NEXTVAL,
            A.APPLYID,
            A.ASSID,
            A.ACCOUNTID,
            A.CLTNO,
            A.NATUREID,
            A.USAGEID,
            A.CURRENCYNO,
            A.ISABROAD,
            A.OPENREASON,
            A.APPLYDATE,
            A.CANCELREMARK,
            A.CANCELREASON,
            A.FILEREMARK,
            A.REASON,
            A.CREATE_TIME,
            A.UPDATE_TIME,
            A.ISREGISTER,
            A.CLT_NAME,
            A.CONTACT_PERSON,
            A.CONTACT_TEL,
            A.BASIC_ACCOUNT_NO,
            A.BASIC_ACCOUNT_BANKNAME,
            A.AUTHORIZED_PERSON_NAME,
            A.AUTHORIZED_PERSON_ID,
            A.LEGAL_PERSON,
            A.LEGAL_ID_CARD,
            A.NAMES_SEAL,
            A.FINANCIAL_SEAL,
            A.OFFICIAL_SEAL,
            A.ADMIN_AREA,
            A.IS_SEAL_LEGAL_PERSON,
            A.IS_INTERNAL_ACCOUNT,
            A.NEED_OPEN_EBANK,
            A.NEED_ONLINE,
            A.BELONG_FTA,
            A.CLT_ADDR,
            A.ACCOUNT_NAME,
            A.ACTUAL_OFFICE_ADDRESS,
            A.CTID,
            A.ERP_ID,
            A.ERP_SYS_NO,
            A.ABOLISHMENT_FMID,
            A.ABOLISHMENT_REASON,
            A.ABOLISHMENT_FLAG,
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
            AAA.ACCOUNTID = #accountId#,
            AAA.CLTNO = #cltNo#,
            AAA.NATUREID = #natureId#,
            AAA.USAGEID = #usageId#,
            AAA.CURRENCYNO = #currencyNo#,
            AAA.ISABROAD = #isAbroad#,
            AAA.OPENREASON = #openReason#,
            AAA.APPLYDATE = #applyDate#,
            AAA.CANCELREMARK = #cancelRemark#,
            AAA.CANCELREASON = #cancelReason#,
            AAA.FILEREMARK = #fileRemark#,
            AAA.REASON = #reason#,
            AAA.CREATE_TIME = #create_time#,
            AAA.UPDATE_TIME = #update_time#,
            AAA.ISREGISTER = #isRegister#,
            AAA.CLT_NAME = #cltName#,
            AAA.CONTACT_PERSON = #contactPerson#,
            AAA.CONTACT_TEL = #contactTel#,
            AAA.BASIC_ACCOUNT_NO = #basicAccountNo#,
            AAA.BASIC_ACCOUNT_BANKNAME = #basicAccountBankname#,
            AAA.AUTHORIZED_PERSON_NAME = #authorizedPersonName#,
            AAA.AUTHORIZED_PERSON_ID = #authorizedPersonId#,
            AAA.LEGAL_PERSON = #legalPerson#,
            AAA.LEGAL_ID_CARD = #legalIdCard#,
            AAA.NAMES_SEAL = #namesSeal#,
            AAA.FINANCIAL_SEAL = #financialSeal#,
            AAA.OFFICIAL_SEAL = #officialSeal#,
            AAA.ADMIN_AREA = #adminArea#,
            AAA.IS_SEAL_LEGAL_PERSON = #isSealLegalPerson#,
            AAA.IS_INTERNAL_ACCOUNT = #isInternalAccount#,
            AAA.NEED_OPEN_EBANK = #needOpenEbank#,
            AAA.NEED_ONLINE = #needOnline#,
            AAA.BELONG_FTA = #belongFta#,
            AAA.CLT_ADDR = #cltAddr#,
            AAA.ACCOUNT_NAME = #accountName#,
            AAA.ACTUAL_OFFICE_ADDRESS = #actualOfficeAddress#,
            AAA.CTID = #ctId#,
            AAA.ERP_ID = #erpId#,
            AAA.ERP_SYS_NO = #erpSysNo#,
            AAA.ABOLISHMENT_FMID = #abolishmentFmid#,
            AAA.ABOLISHMENT_REASON = #abolishmentReason#,
            AAA.ABOLISHMENT_FLAG = #abolishmentFlag#,
            AAA.IS_SEND_N9 = #isSendN9#
        WHEN NOT MATCHED THEN
            INSERT (
                FMID,
                APPLYID,
                ASSID,
                ACCOUNTID,
                CLTNO,
                NATUREID,
                USAGEID,
                CURRENCYNO,
                ISABROAD,
                OPENREASON,
                APPLYDATE,
                CANCELREMARK,
                CANCELREASON,
                FILEREMARK,
                REASON,
                CREATE_TIME,
                UPDATE_TIME,
                ISREGISTER,
                CLT_NAME,
                CONTACT_PERSON,
                CONTACT_TEL,
                BASIC_ACCOUNT_NO,
                BASIC_ACCOUNT_BANKNAME,
                AUTHORIZED_PERSON_NAME,
                AUTHORIZED_PERSON_ID,
                LEGAL_PERSON,
                LEGAL_ID_CARD,
                NAMES_SEAL,
                FINANCIAL_SEAL,
                OFFICIAL_SEAL,
                ADMIN_AREA,
                IS_SEAL_LEGAL_PERSON,
                IS_INTERNAL_ACCOUNT,
                NEED_OPEN_EBANK,
                NEED_ONLINE,
                BELONG_FTA,
                CLT_ADDR,
                ACCOUNT_NAME,
                ACTUAL_OFFICE_ADDRESS,
                CTID,
                ERP_ID,
                ERP_SYS_NO,
                ABOLISHMENT_FMID,
                ABOLISHMENT_REASON,
                ABOLISHMENT_FLAG,
                IS_SEND_N9
            ) 
            VALUES (
                AIMS_ACCOUNT_APPLY_SEQ.NEXTVAL,
                #applyId#,
                #assId#,
                #accountId#,
                #cltNo#,
                #natureId#,
                #usageId#,
                #currencyNo#,
                #isAbroad#,
                #openReason#,
                #applyDate#,
                #cancelRemark#,
                #cancelReason#,
                #fileRemark#,
                #reason#,
                #create_time#,
                #update_time#,
                #isRegister#,
                #cltName#,
                #contactPerson#,
                #contactTel#,
                #basicAccountNo#,
                #basicAccountBankname#,
                #authorizedPersonName#,
                #authorizedPersonId#,
                #legalPerson#,
                #legalIdCard#,
                #namesSeal#,
                #financialSeal#,
                #officialSeal#,
                #adminArea#,
                #isSealLegalPerson#,
                #isInternalAccount#,
                #needOpenEbank#,
                #needOnline#,
                #belongFta#,
                #cltAddr#,
                #accountName#,
                #actualOfficeAddress#,
                #ctId#,
                #erpId#,
                #erpSysNo#,
                #abolishmentFmid#,
                #abolishmentReason#,
                #abolishmentFlag#,
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


