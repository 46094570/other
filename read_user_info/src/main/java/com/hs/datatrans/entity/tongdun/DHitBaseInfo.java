package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DHitBaseInfo {

    /*值为自定义规则中子规则描述或指标名称*/
    private String description;
    /*风险类型*/
    private String fraud_type;

    private String fuzzy_id_number;

    private List<String> fuzzy_name;
    /*匹配类型*/
    private String hit_type_displayname;
    /*规则标识*/
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFraud_type() {
        return fraud_type;
    }

    public void setFraud_type(String fraud_type) {
        this.fraud_type = fraud_type;
    }

    public String getFuzzy_id_number() {
        return fuzzy_id_number;
    }

    public void setFuzzy_id_number(String fuzzy_id_number) {
        this.fuzzy_id_number = fuzzy_id_number;
    }

    public List<String> getFuzzy_name() {
        return fuzzy_name;
    }

    public void setFuzzy_name(List<String> fuzzy_name) {
        this.fuzzy_name = fuzzy_name;
    }

    public String getHit_type_displayname() {
        return hit_type_displayname;
    }

    public void setHit_type_displayname(String hit_type_displayname) {
        this.hit_type_displayname = hit_type_displayname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DHitBaseInfo{" +
                "description='" + description + '\'' +
                ", fraud_type='" + fraud_type + '\'' +
                ", fuzzy_id_number='" + fuzzy_id_number + '\'' +
                ", fuzzy_name=" + fuzzy_name +
                ", hit_type_displayname='" + hit_type_displayname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
