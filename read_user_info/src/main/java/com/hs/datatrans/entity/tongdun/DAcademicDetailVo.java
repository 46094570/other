package com.hs.datatrans.entity.tongdun;

public class DAcademicDetailVo {

    /*是否扣费;*/
    private boolean flow_charge;

    /*身份证号码;*/
    private String id_number;

    /*姓名;*/
    private String name;

    /*调查结果;*/
    private String result;

    /*调用结果编码;*/
    private String result_code;

    /*学历;*/
    private String third_part_degree;

    /*入学年份;*/
    private String third_part_enrol_date;

    /*毕业年份;*/
    private String third_part_graduate_date;

    /*毕业学校;*/
    private String third_part_graduate_school;

    /*专业;*/
    private String third_part_speciality;

    /*毕业类型;*/
    private String third_part_study_result;

    /*学历类型;*/
    private String third_part_study_style;

    public boolean isFlow_charge() {
        return flow_charge;
    }

    public void setFlow_charge(boolean flow_charge) {
        this.flow_charge = flow_charge;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getThird_part_degree() {
        return third_part_degree;
    }

    public void setThird_part_degree(String third_part_degree) {
        this.third_part_degree = third_part_degree;
    }

    public String getThird_part_enrol_date() {
        return third_part_enrol_date;
    }

    public void setThird_part_enrol_date(String third_part_enrol_date) {
        this.third_part_enrol_date = third_part_enrol_date;
    }

    public String getThird_part_graduate_date() {
        return third_part_graduate_date;
    }

    public void setThird_part_graduate_date(String third_part_graduate_date) {
        this.third_part_graduate_date = third_part_graduate_date;
    }

    public String getThird_part_graduate_school() {
        return third_part_graduate_school;
    }

    public void setThird_part_graduate_school(String third_part_graduate_school) {
        this.third_part_graduate_school = third_part_graduate_school;
    }

    public String getThird_part_speciality() {
        return third_part_speciality;
    }

    public void setThird_part_speciality(String third_part_speciality) {
        this.third_part_speciality = third_part_speciality;
    }

    public String getThird_part_study_result() {
        return third_part_study_result;
    }

    public void setThird_part_study_result(String third_part_study_result) {
        this.third_part_study_result = third_part_study_result;
    }

    public String getThird_part_study_style() {
        return third_part_study_style;
    }

    public void setThird_part_study_style(String third_part_study_style) {
        this.third_part_study_style = third_part_study_style;
    }

    @Override
    public String toString() {
        return "DAcademicDetailVo{" +
                "flow_charge=" + flow_charge +
                ", id_number='" + id_number + '\'' +
                ", name='" + name + '\'' +
                ", result='" + result + '\'' +
                ", result_code='" + result_code + '\'' +
                ", third_part_degree='" + third_part_degree + '\'' +
                ", third_part_enrol_date='" + third_part_enrol_date + '\'' +
                ", third_part_graduate_date='" + third_part_graduate_date + '\'' +
                ", third_part_graduate_school='" + third_part_graduate_school + '\'' +
                ", third_part_speciality='" + third_part_speciality + '\'' +
                ", third_part_study_result='" + third_part_study_result + '\'' +
                ", third_part_study_style='" + third_part_study_style + '\'' +
                '}';
    }
}
