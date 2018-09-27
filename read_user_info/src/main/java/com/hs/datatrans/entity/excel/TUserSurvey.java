package com.hs.datatrans.entity.excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TUserSurvey implements Serializable {
    private static final long serialVersionUID = 3079204263021578605L;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 累计申请金额
     */
    private BigDecimal applyAmount;

    /**
     * 待还金额
     */
    private BigDecimal stayAmount;

    /**
     * 已还金额
     */
    private BigDecimal repaidAmount;

    /**
     * 坏账金额
     */
    private BigDecimal debtAmount;

    /**
     * 累计借款项目
     */
    private Integer projectCount;

    /**
     * 累计逾期项目
     */
    private Integer overdueProject;

    /**
     * 累计已结清项目
     */
    private Integer settleProject;

    /**
     * 累计坏账项目
     */
    private Integer dabtProject;

    /**
     * 更新时间
     */
    private Date updateTime;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public BigDecimal getApplyAmount()
    {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount)
    {
        this.applyAmount = applyAmount;
    }

    public BigDecimal getStayAmount()
    {
        return stayAmount;
    }

    public void setStayAmount(BigDecimal stayAmount)
    {
        this.stayAmount = stayAmount;
    }

    public BigDecimal getRepaidAmount()
    {
        return repaidAmount;
    }

    public void setRepaidAmount(BigDecimal repaidAmount)
    {
        this.repaidAmount = repaidAmount;
    }

    public BigDecimal getDebtAmount()
    {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount)
    {
        this.debtAmount = debtAmount;
    }

    public Integer getProjectCount()
    {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount)
    {
        this.projectCount = projectCount;
    }

    public Integer getOverdueProject()
    {
        return overdueProject;
    }

    public void setOverdueProject(Integer overdueProject)
    {
        this.overdueProject = overdueProject;
    }

    public Integer getSettleProject()
    {
        return settleProject;
    }

    public void setSettleProject(Integer settleProject)
    {
        this.settleProject = settleProject;
    }

    public Integer getDabtProject()
    {
        return dabtProject;
    }

    public void setDabtProject(Integer dabtProject)
    {
        this.dabtProject = dabtProject;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public void clean(){
        this.setApplyAmount(null);
        this.setDabtProject(null);
        this.setDebtAmount(null);
        this.setOverdueProject(null);
        this.setProjectCount(null);
        this.setRepaidAmount(null);
        this.setSettleProject(null);
        this.setStayAmount(null);
        this.setUpdateTime(null);
        this.setUserId(null);
    }

    @Override
    public String toString() {
        return "TUserSurvey{" +
                "userId='" + userId + '\'' +
                ", applyAmount=" + applyAmount +
                ", stayAmount=" + stayAmount +
                ", repaidAmount=" + repaidAmount +
                ", debtAmount=" + debtAmount +
                ", projectCount=" + projectCount +
                ", overdueProject=" + overdueProject +
                ", settleProject=" + settleProject +
                ", dabtProject=" + dabtProject +
                ", updateTime=" + updateTime +
                '}';
    }
}
