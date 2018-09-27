package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DTDReportInfoVo {

    /*归属地解析;*/
    private DAddressDetectedVo address_detect;

    /*申请编号;*/
    private String application_id;

    /*扫描时间;*/
    private Long apply_time;

    /*设备环境信息;*/
    private DFacilityInfoVo device_info;

    /*设备类型;*/
    private String device_type;

    /*风险结果;*/
    private String final_decision;

    /*风险分数;*/
    private int final_score;

    /*地理位置信息;*/
    private DGeographicalPositionInfoVo geo_ip;

    /*真实地理位置信息;*/
    private DGeographicalPositionInfoVo geo_trueip;

    /*第三方数据源调用详情;*/
    private DThirdPartyDataSourceDetailVo kunta_call_result;

    /*代理信息 < 代理信息 > array*/
    private List<DProxyInfoVo> proxy_info;
    /*错误代码;*/
    private String reason_code;

    /*错误描述;*/
    private String reason_desc;

    /*报告编号;*/
    private String report_id;

    /*报告时间;*/
    private Long report_time;

    /*扫描出来的风险项;< DRiskItemVo > array*/
    private List<DRiskItemVo> risk_items;

    /*接口调用是否成功;*/
    private boolean success;

    public DAddressDetectedVo getAddress_detect() {
        return address_detect;
    }

    public void setAddress_detect(DAddressDetectedVo address_detect) {
        this.address_detect = address_detect;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public Long getApply_time() {
        return apply_time;
    }

    public void setApply_time(Long apply_time) {
        this.apply_time = apply_time;
    }

    public DFacilityInfoVo getDevice_info() {
        return device_info;
    }

    public void setDevice_info(DFacilityInfoVo device_info) {
        this.device_info = device_info;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getFinal_decision() {
        return final_decision;
    }

    public void setFinal_decision(String final_decision) {
        this.final_decision = final_decision;
    }

    public int getFinal_score() {
        return final_score;
    }

    public void setFinal_score(int final_score) {
        this.final_score = final_score;
    }

    public DGeographicalPositionInfoVo getGeo_ip() {
        return geo_ip;
    }

    public void setGeo_ip(DGeographicalPositionInfoVo geo_ip) {
        this.geo_ip = geo_ip;
    }

    public DGeographicalPositionInfoVo getGeo_trueip() {
        return geo_trueip;
    }

    public void setGeo_trueip(DGeographicalPositionInfoVo geo_trueip) {
        this.geo_trueip = geo_trueip;
    }

    public DThirdPartyDataSourceDetailVo getKunta_call_result() {
        return kunta_call_result;
    }

    public void setKunta_call_result(DThirdPartyDataSourceDetailVo kunta_call_result) {
        this.kunta_call_result = kunta_call_result;
    }

    public List<DProxyInfoVo> getProxy_info() {
        return proxy_info;
    }

    public void setProxy_info(List<DProxyInfoVo> proxy_info) {
        this.proxy_info = proxy_info;
    }

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public String getReason_desc() {
        return reason_desc;
    }

    public void setReason_desc(String reason_desc) {
        this.reason_desc = reason_desc;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public Long getReport_time() {
        return report_time;
    }

    public void setReport_time(Long report_time) {
        this.report_time = report_time;
    }

    public List<DRiskItemVo> getRisk_items() {
        return risk_items;
    }

    public void setRisk_items(List<DRiskItemVo> risk_items) {
        this.risk_items = risk_items;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "DTDReportInfoVo{" +
                "address_detect=" + address_detect +
                ", application_id='" + application_id + '\'' +
                ", apply_time=" + apply_time +
                ", device_info=" + device_info +
                ", device_type='" + device_type + '\'' +
                ", final_decision='" + final_decision + '\'' +
                ", final_score=" + final_score +
                ", geo_ip=" + geo_ip +
                ", geo_trueip=" + geo_trueip +
                ", kunta_call_result=" + kunta_call_result +
                ", proxy_info=" + proxy_info +
                ", reason_code='" + reason_code + '\'' +
                ", reason_desc='" + reason_desc + '\'' +
                ", report_id='" + report_id + '\'' +
                ", report_time=" + report_time +
                ", risk_items=" + risk_items +
                ", success=" + success +
                '}';
    }
}
