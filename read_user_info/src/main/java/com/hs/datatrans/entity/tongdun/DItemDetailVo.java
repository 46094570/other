package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DItemDetailVo {

    /*< 法院执行详情 > array*/
    private List<DCourtExecuteDetails> court_details;

    private int discredit_times;

    private String fraud_type;

    /*< DFrequencyDetailVo > array*/
    private List<DFrequencyDetailVo> frequency_detail_list;

    private List<String> high_risk_areas;

    private List<String> hit_list_datas;

    /*< DHitBaseInfo > array*/
    private List<DHitBaseInfo> namelist_hit_details;

    /*< 逾期详情 > array*/
    private List<DOverdueDetails> overdue_details;

    private int platform_count;

    private List<String> platform_detail;

    /*< DPlatformDetailVo > array*/
    private List<DPlatformDetailVo> platform_detail_dimension;

    /*	规则的类型*/
    private String type;

    public List<DCourtExecuteDetails> getCourt_details() {
        return court_details;
    }

    public void setCourt_details(List<DCourtExecuteDetails> court_details) {
        this.court_details = court_details;
    }

    public int getDiscredit_times() {
        return discredit_times;
    }

    public void setDiscredit_times(int discredit_times) {
        this.discredit_times = discredit_times;
    }

    public String getFraud_type() {
        return fraud_type;
    }

    public void setFraud_type(String fraud_type) {
        this.fraud_type = fraud_type;
    }

    public List<DFrequencyDetailVo> getFrequency_detail_list() {
        return frequency_detail_list;
    }

    public void setFrequency_detail_list(List<DFrequencyDetailVo> frequency_detail_list) {
        this.frequency_detail_list = frequency_detail_list;
    }

    public List<String> getHigh_risk_areas() {
        return high_risk_areas;
    }

    public void setHigh_risk_areas(List<String> high_risk_areas) {
        this.high_risk_areas = high_risk_areas;
    }

    public List<String> getHit_list_datas() {
        return hit_list_datas;
    }

    public void setHit_list_datas(List<String> hit_list_datas) {
        this.hit_list_datas = hit_list_datas;
    }

    public List<DHitBaseInfo> getNamelist_hit_details() {
        return namelist_hit_details;
    }

    public void setNamelist_hit_details(List<DHitBaseInfo> namelist_hit_details) {
        this.namelist_hit_details = namelist_hit_details;
    }

    public List<DOverdueDetails> getOverdue_details() {
        return overdue_details;
    }

    public void setOverdue_details(List<DOverdueDetails> overdue_details) {
        this.overdue_details = overdue_details;
    }

    public int getPlatform_count() {
        return platform_count;
    }

    public void setPlatform_count(int platform_count) {
        this.platform_count = platform_count;
    }

    public List<String> getPlatform_detail() {
        return platform_detail;
    }

    public void setPlatform_detail(List<String> platform_detail) {
        this.platform_detail = platform_detail;
    }

    public List<DPlatformDetailVo> getPlatform_detail_dimension() {
        return platform_detail_dimension;
    }

    public void setPlatform_detail_dimension(List<DPlatformDetailVo> platform_detail_dimension) {
        this.platform_detail_dimension = platform_detail_dimension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DItemDetailVo{" +
                "court_details=" + court_details +
                ", discredit_times=" + discredit_times +
                ", fraud_type='" + fraud_type + '\'' +
                ", frequency_detail_list=" + frequency_detail_list +
                ", high_risk_areas=" + high_risk_areas +
                ", hit_list_datas=" + hit_list_datas +
                ", namelist_hit_details=" + namelist_hit_details +
                ", overdue_details=" + overdue_details +
                ", platform_count=" + platform_count +
                ", platform_detail=" + platform_detail +
                ", platform_detail_dimension=" + platform_detail_dimension +
                ", type='" + type + '\'' +
                '}';
    }
}
