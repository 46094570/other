package com.hs.datatrans.entity.tongdun;

public class DRiskItemVo {
    /*检查项分组*/
    private String group;
    /*检查详情*/
    private DItemDetailVo item_detail;
    /*检查项编号*/
    private String item_id;
    /*检查项名称*/
    private String item_name;
    /*风险等级*/
    private String risk_level;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public DItemDetailVo getItem_detail() {
        return item_detail;
    }

    public void setItem_detail(DItemDetailVo item_detail) {
        this.item_detail = item_detail;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    @Override
    public String toString() {
        return "DRiskItemVo{" +
                "group='" + group + '\'' +
                ", item_detail=" + item_detail +
                ", item_id='" + item_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", risk_level='" + risk_level + '\'' +
                '}';
    }
}
