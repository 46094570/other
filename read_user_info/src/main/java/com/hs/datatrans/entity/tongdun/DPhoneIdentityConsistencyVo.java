package com.hs.datatrans.entity.tongdun;

public class DPhoneIdentityConsistencyVo {

    /*是否扣费*/
    private boolean flow_charge;
    /*身份证号码*/
    private String id_number;
    /*手机号码*/
    private String mobile;
    /*调查结果*/
    private String result;
    /*调用结果编码*/
    private String result_code;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    @Override
    public String toString() {
        return "DPhoneIdentityConsistencyVo{" +
                "flow_charge=" + flow_charge +
                ", id_number='" + id_number + '\'' +
                ", mobile='" + mobile + '\'' +
                ", result='" + result + '\'' +
                ", result_code='" + result_code + '\'' +
                '}';
    }
}
