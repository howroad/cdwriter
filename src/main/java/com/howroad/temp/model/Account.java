package com.howroad.temp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description: 账户实体类
 * </p>
 * 
 * <p>
 * Company: 北京九恒星科技股份有限公司
 * </p>
 * 
 * @author LYY
 * 
 * @since：2016-7-21 下午04:27:29
 * 
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	private Integer accountId;
	/**
	 * 账户申请流水号
	 */
	private Integer applyId;
	/**
	 * 账户代码
	 */
	private String accountCode;
	/**
	 * 单位编号
	 */
	private String cltNo;
	/**
	 * 合作金融网点流水号
	 */
	private Integer assId;
	/**
	 * 所属金融机构
	 */
	private String bankNo;
	/**
	 * 境内外
	 */
	private String isAbroad;
	/**
	 * 地区编码（开户地址）
	 */
	private String regNo;
	/**
	 * 区域编码
	 */
	private Integer areaId;
	/**
	 * 开户日期
	 */
	private Date openAccountDate;
	/**
	 * 账号
	 */
	private String accountNo;
	/**
	 * 户名
	 */
	private String accountName;
	/**
	 * 助记名
	 */
	private String memoryName;
	/**
	 * 币种
	 */
	private String currencyNo;
	/**
	 * 账户用途
	 */
	private Integer usageId;
	/**
	 * 账户性质
	 */
	private Integer ctId;
	/**
	 * 有效日期开始
	 */
	private Date vaildStartDate;
	/**
	 * 有效日期结束
	 */
	private Date vaildEndDate;
	/**
	 * 账户类别
	 */
	private Integer natureId;
    /**
     * 账户类别编号
     */
    private String natureCode;
	/**
	 * 外汇业务类型
	 */
	private Integer foreignType;
	/**
	 * 联网方式
	 */
	private String associateFlag;
    /**
     * 便更后联网方式
     */
	private String afterAssociateFlag;
	/**
	 * 单位/项目地址
	 */
	private String arrdess;
	/**
	 * 备注
	 */
	private String cnreMark;
	/**
	 * 统计编码1
	 */
	private String tempCode1;
	/**
	 * 统计编码2
	 */
	private String tempCode2;
	/**
	 * 统计编码3
	 */
	private String tempCode3;
	/**
	 * 统计编码4
	 */
	private String tempCode4;
	/**
	 * 统计编码5
	 */
	private String tempCode5;
	/**
	 * 统计编码6
	 */
	private String tempCode6;
	/**
	 * 统计编码7
	 */
	private String tempCode7;
	/**
	 * 统计编码8
	 */
	private String tempCode8;
	/**
	 * 统计编码9
	 */
	private String tempCode9;
	/**
	 * 统计编码10
	 */
	private String tempCode10;
	/**
	 * 账户状态
	 */
	private Integer acntState;
    /**
     * 变更后账户状态
     */
    private Integer afterAcntState;
	/**
	 * 销户日期
	 */
	private Date cancelDate;
	/**
	 * 销户备注
	 */
	private String cancelRemark;
	/**
	 * 销户原因
	 */
	private String cancelReason;
	
	/**
	 * 取消销户日期
	 */
	private Date removeCancelDate;
	/**
	 * 取消销户原因
	 */
	private String removeCancelReason;
	/**
	 * 附件内容描述
	 */
	private String fileRemark;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 经办人
	 */
	private String inputor;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 最后修改时间
	 */
	private Date update_time;
	/**
	 * 虚拟户对应的实体账户
	 */
	private String eAccount;
	/**
	 * 单位级别码
	 */
	private String treeNo;

	/**
	 * 对公电话
	 */
	private String publicTelephone;

	/**
	 * 客户经理
	 */
	private String customerManager;

	/**
	 * 客户经理电话
	 */
	private String cmTelephone;

	/**
	 * 客户经理电子邮箱
	 */
	private String cmMail;

	/**
	 * 是否监管账户
	 */
	private Integer isEscrowAccount;
	
	/**
	 * 启用数据权限查询出的单位集合
	 */
	private String[] cltNos;
	
	/**
	 * 是否启动数据权限
	 */
	private Integer isUserPer;
	
	/**
	 * 扣将账户标记:1是,0否
	 */
	private Integer isDeduct;
	/**
	 * 销户之前的账户状态
	 */
	private Integer beforeCalcelState;
    /**
     * 账户变更类型（0-基本信息 1-账户状态 2-联网方式）
     */
    private String modifyType;
	/**
	 * 直连联网方式:0 全部 1 现金 2 票据
	 */
	private Integer connectType;

    /**
     * 联行号/网点编号
     */
	private String associateBankCode;

	/**
	 * 申请是否被登记
	 */
	private Integer isRegister;
	
	/*********************国电投新增字段**********************/
	/**
	 * 单位名称
	 */
	private String cltName;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系人电话
	 */
	private String contactTel;
	/**
	 * 基本户账户
	 */
	private String basicAccountNo;
	/**
	 * 基本户开户行
	 */
	private String basicAccountBankname;
	/**
	 * 被授权人姓名
	 */
	private String authorizedPersonName;
	/**
	 * 被授权人身份证号
	 */
	private String authorizedPersonId;
	/**
	 * 法人姓名
	 */
	private String legalPerson;
	/**
	 * 法人身份证号
	 */
	private String legalIdCard;
	/**
	 * 印鉴人名章名称
	 */
	private String namesSeal;
	/**
	 * 印鉴财务章名称
	 */
	private String financialSeal;
	/**
	 * 公章名称
	 */
	private String officialSeal;
	/**
	 * 行政区划
	 */
	private String adminArea;
	/**
	 * 实际办公地址
	 */
	private String actualOfficeAddress;
	/**
	 * 预留印鉴是否为法人
	 */
	private Integer isSealLegalPerson;
	/**
	 * 是否为财务公司内部户
	 */
	private Integer isInternalAccount;
	/**
	 * 是否需要开通网银
	 */
	private Integer needOpenEbank;
	/**
	 * 是否需要上线
	 */
	private Integer needOnline;
	/**
	 * 是否属于自贸区
	 */
	private Integer belongFta;
	/**
	 * 是否开通提醒
	 */
	private Integer isOpenRemind;
	/**
	 * 是否短信提醒
	 */
	private Integer smsRemind;
	/**
	 * 是否电话提醒
	 */
	private Integer telRemind;
	/**
	 * 是否大额提醒
	 */
	private Integer largeAmountRemind;
	/**
	 * 账户到期日
	 */
	private Date dueDate;
    /**
     * 账户变更申请Id
     */
	private Integer changeApplyId;
	/**
	 * 变更日期
	 */
	private Date changeDate;
	/**
	 * 变更原因
	 */
	private String changeReason;
	/**
	 * 修正日期
	 */
	private Date correctDate;
	/**
	 * 修正原因
	 */
	private String correctReason;
	/**
	 * erp开户登记流水号
	 */
	private String erpId;
	/**
	 * ERP系统编号
	 */
	private String erpSysNo;
	/*********************国电投新增字段**********************/
	/**
	 * 工作流编号
	 */
	private String fmCls;
	
	
	public String getFmCls() {
		return fmCls;
	}

	public void setFmCls(String fmCls) {
		this.fmCls = fmCls;
	}

	public String[] getCltNos() {
		return cltNos;
	}

	public Date getCorrectDate() {
		return correctDate;
	}

	public void setCorrectDate(Date correctDate) {
		this.correctDate = correctDate;
	}

	public String getCorrectReason() {
		return correctReason;
	}

	public void setCorrectReason(String correctReason) {
		this.correctReason = correctReason;
	}

	public Integer getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(Integer isRegister) {
		this.isRegister = isRegister;
	}

	public void setCltNos(String[] cltNos) {
		this.cltNos = cltNos;
	}

	public Integer getIsUserPer() {
		return isUserPer;
	}

	public void setIsUserPer(Integer isUserPer) {
		this.isUserPer = isUserPer;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getCltNo() {
		return cltNo;
	}

	public void setCltNo(String cltNo) {
		this.cltNo = cltNo;
	}

	public Integer getAssId() {
		return assId;
	}

	public void setAssId(Integer assId) {
		this.assId = assId;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(String isAbroad) {
		this.isAbroad = isAbroad;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Date getOpenAccountDate() {
		return openAccountDate;
	}

	public void setOpenAccountDate(Date openAccountDate) {
		this.openAccountDate = openAccountDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMemoryName() {
		return memoryName;
	}

	public void setMemoryName(String memoryName) {
		this.memoryName = memoryName;
	}

	public String getCurrencyNo() {
		return currencyNo;
	}

	public void setCurrencyNo(String currencyNo) {
		this.currencyNo = currencyNo;
	}

	public Integer getUsageId() {
		return usageId;
	}

	public void setUsageId(Integer usageId) {
		this.usageId = usageId;
	}

	public Integer getCtId() {
		return ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public Date getVaildStartDate() {
		return vaildStartDate;
	}

	public void setVaildStartDate(Date vaildStartDate) {
		this.vaildStartDate = vaildStartDate;
	}

	public Date getVaildEndDate() {
		return vaildEndDate;
	}

	public void setVaildEndDate(Date vaildEndDate) {
		this.vaildEndDate = vaildEndDate;
	}

	public Integer getNatureId() {
		return natureId;
	}

	public void setNatureId(Integer natureId) {
		this.natureId = natureId;
	}

	public Integer getForeignType() {
		return foreignType;
	}

	public void setForeignType(Integer foreignType) {
		this.foreignType = foreignType;
	}

	public String getAssociateFlag() {
		return associateFlag;
	}

	public void setAssociateFlag(String associateFlag) {
		this.associateFlag = associateFlag;
	}

	public String getArrdess() {
		return arrdess;
	}

	public void setArrdess(String arrdess) {
		this.arrdess = arrdess;
	}

	public String getCnreMark() {
		return cnreMark;
	}

	public void setCnreMark(String cnreMark) {
		this.cnreMark = cnreMark;
	}

	public Integer getAcntState() {
		return acntState;
	}

	public void setAcntState(Integer acntState) {
		this.acntState = acntState;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getFileRemark() {
		return fileRemark;
	}

	public void setFileRemark(String fileRemark) {
		this.fileRemark = fileRemark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getTempCode1() {
		return tempCode1;
	}

	public void setTempCode1(String tempCode1) {
		this.tempCode1 = tempCode1;
	}

	public String getTempCode2() {
		return tempCode2;
	}

	public void setTempCode2(String tempCode2) {
		this.tempCode2 = tempCode2;
	}

	public String getTempCode3() {
		return tempCode3;
	}

	public void setTempCode3(String tempCode3) {
		this.tempCode3 = tempCode3;
	}

	public String getTempCode4() {
		return tempCode4;
	}

	public void setTempCode4(String tempCode4) {
		this.tempCode4 = tempCode4;
	}

	public String getTempCode5() {
		return tempCode5;
	}

	public void setTempCode5(String tempCode5) {
		this.tempCode5 = tempCode5;
	}

	public String getTempCode6() {
		return tempCode6;
	}

	public void setTempCode6(String tempCode6) {
		this.tempCode6 = tempCode6;
	}

	public String getTempCode7() {
		return tempCode7;
	}

	public void setTempCode7(String tempCode7) {
		this.tempCode7 = tempCode7;
	}

	public String getTempCode8() {
		return tempCode8;
	}

	public void setTempCode8(String tempCode8) {
		this.tempCode8 = tempCode8;
	}

	public String getTempCode9() {
		return tempCode9;
	}

	public void setTempCode9(String tempCode9) {
		this.tempCode9 = tempCode9;
	}

	public String getTempCode10() {
		return tempCode10;
	}

	public void setTempCode10(String tempCode10) {
		this.tempCode10 = tempCode10;
	}

	public String getInputor() {
		return inputor;
	}

	public void setInputor(String inputor) {
		this.inputor = inputor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountCode == null) ? 0 : accountCode.hashCode());
		result = prime * result
				+ ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result
				+ ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result
				+ ((acntState == null) ? 0 : acntState.hashCode());
		result = prime * result + ((applyId == null) ? 0 : applyId.hashCode());
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((arrdess == null) ? 0 : arrdess.hashCode());
		result = prime * result + ((assId == null) ? 0 : assId.hashCode());
		result = prime * result
				+ ((associateFlag == null) ? 0 : associateFlag.hashCode());
		result = prime * result + ((bankNo == null) ? 0 : bankNo.hashCode());
		result = prime * result
				+ ((cancelDate == null) ? 0 : cancelDate.hashCode());
		result = prime * result
				+ ((cancelReason == null) ? 0 : cancelReason.hashCode());
		result = prime * result
				+ ((cancelRemark == null) ? 0 : cancelRemark.hashCode());
		result = prime * result + ((cltNo == null) ? 0 : cltNo.hashCode());
		result = prime * result
				+ ((cnreMark == null) ? 0 : cnreMark.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((create_time == null) ? 0 : create_time.hashCode());
		result = prime * result + ((ctId == null) ? 0 : ctId.hashCode());
		result = prime * result
				+ ((currencyNo == null) ? 0 : currencyNo.hashCode());
		result = prime * result
				+ ((fileRemark == null) ? 0 : fileRemark.hashCode());
		result = prime * result
				+ ((foreignType == null) ? 0 : foreignType.hashCode());
		result = prime * result + ((inputor == null) ? 0 : inputor.hashCode());
		result = prime * result
				+ ((isAbroad == null) ? 0 : isAbroad.hashCode());
		result = prime * result
				+ ((memoryName == null) ? 0 : memoryName.hashCode());
		result = prime * result
				+ ((natureId == null) ? 0 : natureId.hashCode());
		result = prime * result
				+ ((openAccountDate == null) ? 0 : openAccountDate.hashCode());
		result = prime * result + ((regNo == null) ? 0 : regNo.hashCode());
		result = prime * result
				+ ((tempCode1 == null) ? 0 : tempCode1.hashCode());
		result = prime * result
				+ ((tempCode10 == null) ? 0 : tempCode10.hashCode());
		result = prime * result
				+ ((tempCode2 == null) ? 0 : tempCode2.hashCode());
		result = prime * result
				+ ((tempCode3 == null) ? 0 : tempCode3.hashCode());
		result = prime * result
				+ ((tempCode4 == null) ? 0 : tempCode4.hashCode());
		result = prime * result
				+ ((tempCode5 == null) ? 0 : tempCode5.hashCode());
		result = prime * result
				+ ((tempCode6 == null) ? 0 : tempCode6.hashCode());
		result = prime * result
				+ ((tempCode7 == null) ? 0 : tempCode7.hashCode());
		result = prime * result
				+ ((tempCode8 == null) ? 0 : tempCode8.hashCode());
		result = prime * result
				+ ((tempCode9 == null) ? 0 : tempCode9.hashCode());
		result = prime * result
				+ ((update_time == null) ? 0 : update_time.hashCode());
		result = prime * result + ((usageId == null) ? 0 : usageId.hashCode());
		result = prime * result
				+ ((vaildEndDate == null) ? 0 : vaildEndDate.hashCode());
		result = prime * result
				+ ((vaildStartDate == null) ? 0 : vaildStartDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (tempCode1 == null) {
			if (other.tempCode1 != null)
				return false;
		} else if (!tempCode1.equals(other.tempCode1))
			return false;
		if (tempCode10 == null) {
			if (other.tempCode10 != null)
				return false;
		} else if (!tempCode10.equals(other.tempCode10))
			return false;
		if (tempCode2 == null) {
			if (other.tempCode2 != null)
				return false;
		} else if (!tempCode2.equals(other.tempCode2))
			return false;
		if (tempCode3 == null) {
			if (other.tempCode3 != null)
				return false;
		} else if (!tempCode3.equals(other.tempCode3))
			return false;
		if (tempCode4 == null) {
			if (other.tempCode4 != null)
				return false;
		} else if (!tempCode4.equals(other.tempCode4))
			return false;
		if (tempCode5 == null) {
			if (other.tempCode5 != null)
				return false;
		} else if (!tempCode5.equals(other.tempCode5))
			return false;
		if (tempCode6 == null) {
			if (other.tempCode6 != null)
				return false;
		} else if (!tempCode6.equals(other.tempCode6))
			return false;
		if (tempCode7 == null) {
			if (other.tempCode7 != null)
				return false;
		} else if (!tempCode7.equals(other.tempCode7))
			return false;
		if (tempCode8 == null) {
			if (other.tempCode8 != null)
				return false;
		} else if (!tempCode8.equals(other.tempCode8))
			return false;
		if (tempCode9 == null) {
			if (other.tempCode9 != null)
				return false;
		} else if (!tempCode9.equals(other.tempCode9))
			return false;
		return true;
	}

	public String geteAccount() {
		return eAccount;
	}

	public void seteAccount(String eAccount) {
		this.eAccount = eAccount;
	}

	public String getTreeNo() {
		return treeNo;
	}

	public void setTreeNo(String treeNo) {
		this.treeNo = treeNo;
	}

	public String getPublicTelephone() {
		return publicTelephone;
	}

	public void setPublicTelephone(String publicTelephone) {
		this.publicTelephone = publicTelephone;
	}

	public String getCustomerManager() {
		return customerManager;
	}

	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}

	public String getCmTelephone() {
		return cmTelephone;
	}

	public void setCmTelephone(String cmTelephone) {
		this.cmTelephone = cmTelephone;
	}

	public String getCmMail() {
		return cmMail;
	}

	public void setCmMail(String cmMail) {
		this.cmMail = cmMail;
	}

	public Integer getIsEscrowAccount() {
		return isEscrowAccount;
	}

	public void setIsEscrowAccount(Integer isEscrowAccount) {
		this.isEscrowAccount = isEscrowAccount;
	}

	public Date getRemoveCancelDate() {
		return removeCancelDate;
	}

	public void setRemoveCancelDate(Date removeCancelDate) {
		this.removeCancelDate = removeCancelDate;
	}

	public String getRemoveCancelReason() {
		return removeCancelReason;
	}

	public void setRemoveCancelReason(String removeCancelReason) {
		this.removeCancelReason = removeCancelReason;
	}

	public Integer getIsDeduct() {
		return isDeduct;
	}

	public void setIsDeduct(Integer isDeduct) {
		this.isDeduct = isDeduct;
	}

    public Integer getAfterAcntState() {
        return afterAcntState;
    }

    public void setAfterAcntState(Integer afterAcntState) {
        this.afterAcntState = afterAcntState;
    }

    public Integer getBeforeCalcelState() {
        return beforeCalcelState;
    }

    public void setBeforeCalcelState(Integer beforeCalcelState) {
        this.beforeCalcelState = beforeCalcelState;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public Integer getConnectType() {
        return connectType;
    }

    public void setConnectType(Integer connectType) {
        this.connectType = connectType;
    }

    public String getNatureCode() {
        return natureCode;
    }

    public void setNatureCode(String natureCode) {
        this.natureCode = natureCode;
    }

    public String getAssociateBankCode() {
        return associateBankCode;
    }

    public void setAssociateBankCode(String associateBankCode) {
        this.associateBankCode = associateBankCode;
    }

    public String getAfterAssociateFlag() {
        return afterAssociateFlag;
    }

    public void setAfterAssociateFlag(String afterAssociateFlag) {
        this.afterAssociateFlag = afterAssociateFlag;
    }

	public String getCltName() {
		return cltName;
	}

	public void setCltName(String cltName) {
		this.cltName = cltName;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getBasicAccountNo() {
		return basicAccountNo;
	}

	public void setBasicAccountNo(String basicAccountNo) {
		this.basicAccountNo = basicAccountNo;
	}

	public String getBasicAccountBankname() {
		return basicAccountBankname;
	}

	public void setBasicAccountBankname(String basicAccountBankname) {
		this.basicAccountBankname = basicAccountBankname;
	}

	public String getAuthorizedPersonName() {
		return authorizedPersonName;
	}

	public void setAuthorizedPersonName(String authorizedPersonName) {
		this.authorizedPersonName = authorizedPersonName;
	}

	public String getAuthorizedPersonId() {
		return authorizedPersonId;
	}

	public void setAuthorizedPersonId(String authorizedPersonId) {
		this.authorizedPersonId = authorizedPersonId;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalIdCard() {
		return legalIdCard;
	}

	public void setLegalIdCard(String legalIdCard) {
		this.legalIdCard = legalIdCard;
	}

	public String getNamesSeal() {
		return namesSeal;
	}

	public void setNamesSeal(String namesSeal) {
		this.namesSeal = namesSeal;
	}

	public String getFinancialSeal() {
		return financialSeal;
	}

	public void setFinancialSeal(String financialSeal) {
		this.financialSeal = financialSeal;
	}

	public String getOfficialSeal() {
		return officialSeal;
	}

	public void setOfficialSeal(String officialSeal) {
		this.officialSeal = officialSeal;
	}

	public String getAdminArea() {
		return adminArea;
	}

	public void setAdminArea(String adminArea) {
		this.adminArea = adminArea;
	}

	public String getActualOfficeAddress() {
		return actualOfficeAddress;
	}

	public void setActualOfficeAddress(String actualOfficeAddress) {
		this.actualOfficeAddress = actualOfficeAddress;
	}

	public Integer getIsSealLegalPerson() {
		return isSealLegalPerson;
	}

	public void setIsSealLegalPerson(Integer isSealLegalPerson) {
		this.isSealLegalPerson = isSealLegalPerson;
	}

	public Integer getIsInternalAccount() {
		return isInternalAccount;
	}

	public void setIsInternalAccount(Integer isInternalAccount) {
		this.isInternalAccount = isInternalAccount;
	}

	public Integer getNeedOpenEbank() {
		return needOpenEbank;
	}

	public void setNeedOpenEbank(Integer needOpenEbank) {
		this.needOpenEbank = needOpenEbank;
	}

	public Integer getNeedOnline() {
		return needOnline;
	}

	public void setNeedOnline(Integer needOnline) {
		this.needOnline = needOnline;
	}

	public Integer getBelongFta() {
		return belongFta;
	}

	public void setBelongFta(Integer belongFta) {
		this.belongFta = belongFta;
	}

	public Integer getIsOpenRemind() {
		return isOpenRemind;
	}

	public void setIsOpenRemind(Integer isOpenRemind) {
		this.isOpenRemind = isOpenRemind;
	}

	public Integer getSmsRemind() {
		return smsRemind;
	}

	public void setSmsRemind(Integer smsRemind) {
		this.smsRemind = smsRemind;
	}

	public Integer getTelRemind() {
		return telRemind;
	}

	public void setTelRemind(Integer telRemind) {
		this.telRemind = telRemind;
	}

	public Integer getLargeAmountRemind() {
		return largeAmountRemind;
	}

	public void setLargeAmountRemind(Integer largeAmountRemind) {
		this.largeAmountRemind = largeAmountRemind;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getChangeApplyId() {
		return changeApplyId;
	}

	public void setChangeApplyId(Integer changeApplyId) {
		this.changeApplyId = changeApplyId;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getErpId() {
		return erpId;
	}

	public void setErpId(String erpId) {
		this.erpId = erpId;
	}

	public String getErpSysNo() {
		return erpSysNo;
	}

	public void setErpSysNo(String erpSysNo) {
		this.erpSysNo = erpSysNo;
	}
}
