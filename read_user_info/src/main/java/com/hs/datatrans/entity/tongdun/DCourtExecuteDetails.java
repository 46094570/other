package com.hs.datatrans.entity.tongdun;

public class DCourtExecuteDetails {

    /*年龄*/
    private String age;
    /*案号*/
    private String case_number;
    /*执行法院*/
    private String court_name;
    /*失信被执行人行为具体情形*/
    private String discredit_detail;
    /*生效法律文书确定的义务*/
    private String duty;
    /*执行依据文号*/
    private String execution_base;
    /*做出执行依据单位*/
    private String execution_department;
    /*执行标的*/
    private String execution_number;
    /*执行状态*/
    private String execution_status;
    /*立案时间*/
    private String filing_time;
    /*风险类型*/
    private String fraud_type;
    /*性别*/
    private String gender;
    /*被执行人姓名*/
    private String name;
    /*省份*/
    private String province;
    /*被执行人的履行情况*/
    private String situation;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCase_number() {
        return case_number;
    }

    public void setCase_number(String case_number) {
        this.case_number = case_number;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getDiscredit_detail() {
        return discredit_detail;
    }

    public void setDiscredit_detail(String discredit_detail) {
        this.discredit_detail = discredit_detail;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getExecution_base() {
        return execution_base;
    }

    public void setExecution_base(String execution_base) {
        this.execution_base = execution_base;
    }

    public String getExecution_department() {
        return execution_department;
    }

    public void setExecution_department(String execution_department) {
        this.execution_department = execution_department;
    }

    public String getExecution_number() {
        return execution_number;
    }

    public void setExecution_number(String execution_number) {
        this.execution_number = execution_number;
    }

    public String getExecution_status() {
        return execution_status;
    }

    public void setExecution_status(String execution_status) {
        this.execution_status = execution_status;
    }

    public String getFiling_time() {
        return filing_time;
    }

    public void setFiling_time(String filing_time) {
        this.filing_time = filing_time;
    }

    public String getFraud_type() {
        return fraud_type;
    }

    public void setFraud_type(String fraud_type) {
        this.fraud_type = fraud_type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "DCourtExecuteDetails{" +
                "age='" + age + '\'' +
                ", case_number='" + case_number + '\'' +
                ", court_name='" + court_name + '\'' +
                ", discredit_detail='" + discredit_detail + '\'' +
                ", duty='" + duty + '\'' +
                ", execution_base='" + execution_base + '\'' +
                ", execution_department='" + execution_department + '\'' +
                ", execution_number='" + execution_number + '\'' +
                ", execution_status='" + execution_status + '\'' +
                ", filing_time='" + filing_time + '\'' +
                ", fraud_type='" + fraud_type + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", situation='" + situation + '\'' +
                '}';
    }
}
