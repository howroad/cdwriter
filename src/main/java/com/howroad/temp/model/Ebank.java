/** 1234569879 */




package com.howroad.temp.model;

import java.util.Date;

/**
 * <p>Title: Ebank.java</p>
 *
 * <p>Description: ������Ϣ��</p>
 *
 * <p>Company: �����ź��ǿƼ��ɷ����޹�˾</p>
 *
 * @author luhao
 * 
 * @since 2019-11-25 15:49:22
 *
 */
public class Ebank {
    
    /** �������� */
    private Integer ebankId;
    
    /** ���һ�β����Ĺ���Id */
    private Integer fmId;
    
    /** ����Id */
    private Integer ebankApplyId;
    
    /** �˻�Id */
    private Integer accountRegistId;
    
    /** �˻����� */
    private String accountAccountCode;
    
    /** �ʺ� */
    private String accountAccountNo;
    
    /** ��������:1����������2�������ڻ��� */
    private Integer ebankType;
    
    /** �Ƿ�ɲ�ѯ */
    private Integer queryFlag;
    
    /** �Ƿ��ת�� */
    private Integer transferFlag;
    
    /** �Ƿ��˽֧�� */
    private Integer isPrivate;
    
    /** ������; */
    private String applyPurpose;
    
    /** ��ͨ���� */
    private Date openingDate;
    
    /** �Ƿ��������� */
    private Integer is3rdApprove;
    
    /** ��������޶� */
    private Double singleMaximum;
    
    /** ÿ������޶� */
    private Double dailyMaximum;
    
    /** U�ܵ����� */
    private Date uEndDate;
    
    /** U���������Ƶ�� */
    private String uPwdTimes;
    
    /** һ��U�ܱ��� */
    private String uCode1;
    
    /** һ��U�ܱ����� */
    private String uPerson1;
    
    /** ����U�ܱ��� */
    private String uCode2;
    
    /** ����U�ܱ����� */
    private String uPerson2;
    
    /** ����U�ܱ��� */
    private String uCode3;
    
    /** ����U�ܱ����� */
    private String uPerson3;
    
    /** �ļ�U�ܱ��� */
    private String uCode4;
    
    /** �ļ�U�ܱ����� */
    private String uPerson4;
    
    /** �弶U�ܱ��� */
    private String uCode5;
    
    /** �弶U�ܱ����� */
    private String uPerson5;
    
    /** ����U�ܱ��� */
    private String uCode6;
    
    /** ����U�ܱ����� */
    private String uPerson6;
    
    /** �߼�U�ܱ��� */
    private String uCode7;
    
    /** �߼�U�ܱ����� */
    private String uPerson7;
    
    /** �˼�U�ܱ��� */
    private String uCode8;
    
    /** �˼�U�ܱ����� */
    private String uPerson8;
    
    /** �ż�U�ܱ��� */
    private String uCode9;
    
    /** �ż�U�ܱ����� */
    private String uPerson9;
    
    /** ������ע */
    private String ebankRemark;
    
    /** ���ԭ�� */
    private String changeReason;
    
    /** ����ԭ�� */
    private String reviseReason;
    
    /** ����ԭ�� */
    private String rejectReson;
    
    /** ����λ��� */
    private String initCltNo;
    
    /** ����λ���� */
    private String initCltName;
    
    /** �����Ƿ����� */
    private Integer ebankCancel;
    
    /** �ʺ��Ƿ����� */
    private Integer isAccountCancel;
    
    /** ����ǰ��Ӧ���ʺű�� */
    private String oldAccountCode;
    
    /** �����˱�� */
    private String ebankCreatePerson;
    
    /** ����ʱ�� */
    private Date ebankCreateTime;
    
    /** �޸��˱�� */
    private String ebankUpdatePerson;
    
    /** �޸�ʱ�� */
    private Date ebankUpdateTime;
    
    /** �������� */
    private Date ebankCancelDate;
    
    /** ����ԭ�� */
    private String ebankCancelReason;
    
    /** ERP������ˮ�� */
    private String erpId;
    
    /** ERPϵͳ��� */
    private String erpSysNo;
    
    /** ������������ */
    private Date ebankApplyDate;
    

    public void setEbankId(Integer ebankId){
        this.ebankId = ebankId;
    }
    public Integer getEbankId(){
        return this.ebankId;
    }
    public void setFmId(Integer fmId){
        this.fmId = fmId;
    }
    public Integer getFmId(){
        return this.fmId;
    }
    public void setEbankApplyId(Integer ebankApplyId){
        this.ebankApplyId = ebankApplyId;
    }
    public Integer getEbankApplyId(){
        return this.ebankApplyId;
    }
    public void setAccountRegistId(Integer accountRegistId){
        this.accountRegistId = accountRegistId;
    }
    public Integer getAccountRegistId(){
        return this.accountRegistId;
    }
    public void setAccountAccountCode(String accountAccountCode){
        this.accountAccountCode = accountAccountCode;
    }
    public String getAccountAccountCode(){
        return this.accountAccountCode;
    }
    public void setAccountAccountNo(String accountAccountNo){
        this.accountAccountNo = accountAccountNo;
    }
    public String getAccountAccountNo(){
        return this.accountAccountNo;
    }
    public void setEbankType(Integer ebankType){
        this.ebankType = ebankType;
    }
    public Integer getEbankType(){
        return this.ebankType;
    }
    public void setQueryFlag(Integer queryFlag){
        this.queryFlag = queryFlag;
    }
    public Integer getQueryFlag(){
        return this.queryFlag;
    }
    public void setTransferFlag(Integer transferFlag){
        this.transferFlag = transferFlag;
    }
    public Integer getTransferFlag(){
        return this.transferFlag;
    }
    public void setIsPrivate(Integer isPrivate){
        this.isPrivate = isPrivate;
    }
    public Integer getIsPrivate(){
        return this.isPrivate;
    }
    public void setApplyPurpose(String applyPurpose){
        this.applyPurpose = applyPurpose;
    }
    public String getApplyPurpose(){
        return this.applyPurpose;
    }
    public void setOpeningDate(Date openingDate){
        this.openingDate = openingDate;
    }
    public Date getOpeningDate(){
        return this.openingDate;
    }
    public void setIs3rdApprove(Integer is3rdApprove){
        this.is3rdApprove = is3rdApprove;
    }
    public Integer getIs3rdApprove(){
        return this.is3rdApprove;
    }
    public void setSingleMaximum(Double singleMaximum){
        this.singleMaximum = singleMaximum;
    }
    public Double getSingleMaximum(){
        return this.singleMaximum;
    }
    public void setDailyMaximum(Double dailyMaximum){
        this.dailyMaximum = dailyMaximum;
    }
    public Double getDailyMaximum(){
        return this.dailyMaximum;
    }
    public void setUEndDate(Date uEndDate){
        this.uEndDate = uEndDate;
    }
    public Date getUEndDate(){
        return this.uEndDate;
    }
    public void setUPwdTimes(String uPwdTimes){
        this.uPwdTimes = uPwdTimes;
    }
    public String getUPwdTimes(){
        return this.uPwdTimes;
    }
    public void setUCode1(String uCode1){
        this.uCode1 = uCode1;
    }
    public String getUCode1(){
        return this.uCode1;
    }
    public void setUPerson1(String uPerson1){
        this.uPerson1 = uPerson1;
    }
    public String getUPerson1(){
        return this.uPerson1;
    }
    public void setUCode2(String uCode2){
        this.uCode2 = uCode2;
    }
    public String getUCode2(){
        return this.uCode2;
    }
    public void setUPerson2(String uPerson2){
        this.uPerson2 = uPerson2;
    }
    public String getUPerson2(){
        return this.uPerson2;
    }
    public void setUCode3(String uCode3){
        this.uCode3 = uCode3;
    }
    public String getUCode3(){
        return this.uCode3;
    }
    public void setUPerson3(String uPerson3){
        this.uPerson3 = uPerson3;
    }
    public String getUPerson3(){
        return this.uPerson3;
    }
    public void setUCode4(String uCode4){
        this.uCode4 = uCode4;
    }
    public String getUCode4(){
        return this.uCode4;
    }
    public void setUPerson4(String uPerson4){
        this.uPerson4 = uPerson4;
    }
    public String getUPerson4(){
        return this.uPerson4;
    }
    public void setUCode5(String uCode5){
        this.uCode5 = uCode5;
    }
    public String getUCode5(){
        return this.uCode5;
    }
    public void setUPerson5(String uPerson5){
        this.uPerson5 = uPerson5;
    }
    public String getUPerson5(){
        return this.uPerson5;
    }
    public void setUCode6(String uCode6){
        this.uCode6 = uCode6;
    }
    public String getUCode6(){
        return this.uCode6;
    }
    public void setUPerson6(String uPerson6){
        this.uPerson6 = uPerson6;
    }
    public String getUPerson6(){
        return this.uPerson6;
    }
    public void setUCode7(String uCode7){
        this.uCode7 = uCode7;
    }
    public String getUCode7(){
        return this.uCode7;
    }
    public void setUPerson7(String uPerson7){
        this.uPerson7 = uPerson7;
    }
    public String getUPerson7(){
        return this.uPerson7;
    }
    public void setUCode8(String uCode8){
        this.uCode8 = uCode8;
    }
    public String getUCode8(){
        return this.uCode8;
    }
    public void setUPerson8(String uPerson8){
        this.uPerson8 = uPerson8;
    }
    public String getUPerson8(){
        return this.uPerson8;
    }
    public void setUCode9(String uCode9){
        this.uCode9 = uCode9;
    }
    public String getUCode9(){
        return this.uCode9;
    }
    public void setUPerson9(String uPerson9){
        this.uPerson9 = uPerson9;
    }
    public String getUPerson9(){
        return this.uPerson9;
    }
    public void setEbankRemark(String ebankRemark){
        this.ebankRemark = ebankRemark;
    }
    public String getEbankRemark(){
        return this.ebankRemark;
    }
    public void setChangeReason(String changeReason){
        this.changeReason = changeReason;
    }
    public String getChangeReason(){
        return this.changeReason;
    }
    public void setReviseReason(String reviseReason){
        this.reviseReason = reviseReason;
    }
    public String getReviseReason(){
        return this.reviseReason;
    }
    public void setRejectReson(String rejectReson){
        this.rejectReson = rejectReson;
    }
    public String getRejectReson(){
        return this.rejectReson;
    }
    public void setInitCltNo(String initCltNo){
        this.initCltNo = initCltNo;
    }
    public String getInitCltNo(){
        return this.initCltNo;
    }
    public void setInitCltName(String initCltName){
        this.initCltName = initCltName;
    }
    public String getInitCltName(){
        return this.initCltName;
    }
    public void setEbankCancel(Integer ebankCancel){
        this.ebankCancel = ebankCancel;
    }
    public Integer getEbankCancel(){
        return this.ebankCancel;
    }
    public void setIsAccountCancel(Integer isAccountCancel){
        this.isAccountCancel = isAccountCancel;
    }
    public Integer getIsAccountCancel(){
        return this.isAccountCancel;
    }
    public void setOldAccountCode(String oldAccountCode){
        this.oldAccountCode = oldAccountCode;
    }
    public String getOldAccountCode(){
        return this.oldAccountCode;
    }
    public void setEbankCreatePerson(String ebankCreatePerson){
        this.ebankCreatePerson = ebankCreatePerson;
    }
    public String getEbankCreatePerson(){
        return this.ebankCreatePerson;
    }
    public void setEbankCreateTime(Date ebankCreateTime){
        this.ebankCreateTime = ebankCreateTime;
    }
    public Date getEbankCreateTime(){
        return this.ebankCreateTime;
    }
    public void setEbankUpdatePerson(String ebankUpdatePerson){
        this.ebankUpdatePerson = ebankUpdatePerson;
    }
    public String getEbankUpdatePerson(){
        return this.ebankUpdatePerson;
    }
    public void setEbankUpdateTime(Date ebankUpdateTime){
        this.ebankUpdateTime = ebankUpdateTime;
    }
    public Date getEbankUpdateTime(){
        return this.ebankUpdateTime;
    }
    public void setEbankCancelDate(Date ebankCancelDate){
        this.ebankCancelDate = ebankCancelDate;
    }
    public Date getEbankCancelDate(){
        return this.ebankCancelDate;
    }
    public void setEbankCancelReason(String ebankCancelReason){
        this.ebankCancelReason = ebankCancelReason;
    }
    public String getEbankCancelReason(){
        return this.ebankCancelReason;
    }
    public void setErpId(String erpId){
        this.erpId = erpId;
    }
    public String getErpId(){
        return this.erpId;
    }
    public void setErpSysNo(String erpSysNo){
        this.erpSysNo = erpSysNo;
    }
    public String getErpSysNo(){
        return this.erpSysNo;
    }
    public void setEbankApplyDate(Date ebankApplyDate){
        this.ebankApplyDate = ebankApplyDate;
    }
    public Date getEbankApplyDate(){
        return this.ebankApplyDate;
    }
    
    public String toString() {
        return "Ebank ["
                + "ebankId = " + ebankId + ", "
                + "fmId = " + fmId + ", "
                + "ebankApplyId = " + ebankApplyId + ", "
                + "accountRegistId = " + accountRegistId + ", "
                + "accountAccountCode = " + accountAccountCode + ", "
                + "accountAccountNo = " + accountAccountNo + ", "
                + "ebankType = " + ebankType + ", "
                + "queryFlag = " + queryFlag + ", "
                + "transferFlag = " + transferFlag + ", "
                + "isPrivate = " + isPrivate + ", "
                + "applyPurpose = " + applyPurpose + ", "
                + "openingDate = " + openingDate + ", "
                + "is3rdApprove = " + is3rdApprove + ", "
                + "singleMaximum = " + singleMaximum + ", "
                + "dailyMaximum = " + dailyMaximum + ", "
                + "uEndDate = " + uEndDate + ", "
                + "uPwdTimes = " + uPwdTimes + ", "
                + "uCode1 = " + uCode1 + ", "
                + "uPerson1 = " + uPerson1 + ", "
                + "uCode2 = " + uCode2 + ", "
                + "uPerson2 = " + uPerson2 + ", "
                + "uCode3 = " + uCode3 + ", "
                + "uPerson3 = " + uPerson3 + ", "
                + "uCode4 = " + uCode4 + ", "
                + "uPerson4 = " + uPerson4 + ", "
                + "uCode5 = " + uCode5 + ", "
                + "uPerson5 = " + uPerson5 + ", "
                + "uCode6 = " + uCode6 + ", "
                + "uPerson6 = " + uPerson6 + ", "
                + "uCode7 = " + uCode7 + ", "
                + "uPerson7 = " + uPerson7 + ", "
                + "uCode8 = " + uCode8 + ", "
                + "uPerson8 = " + uPerson8 + ", "
                + "uCode9 = " + uCode9 + ", "
                + "uPerson9 = " + uPerson9 + ", "
                + "ebankRemark = " + ebankRemark + ", "
                + "changeReason = " + changeReason + ", "
                + "reviseReason = " + reviseReason + ", "
                + "rejectReson = " + rejectReson + ", "
                + "initCltNo = " + initCltNo + ", "
                + "initCltName = " + initCltName + ", "
                + "ebankCancel = " + ebankCancel + ", "
                + "isAccountCancel = " + isAccountCancel + ", "
                + "oldAccountCode = " + oldAccountCode + ", "
                + "ebankCreatePerson = " + ebankCreatePerson + ", "
                + "ebankCreateTime = " + ebankCreateTime + ", "
                + "ebankUpdatePerson = " + ebankUpdatePerson + ", "
                + "ebankUpdateTime = " + ebankUpdateTime + ", "
                + "ebankCancelDate = " + ebankCancelDate + ", "
                + "ebankCancelReason = " + ebankCancelReason + ", "
                + "erpId = " + erpId + ", "
                + "erpSysNo = " + erpSysNo + ", "
                + "ebankApplyDate = " + ebankApplyDate + " "
                + "]";
    }
}
