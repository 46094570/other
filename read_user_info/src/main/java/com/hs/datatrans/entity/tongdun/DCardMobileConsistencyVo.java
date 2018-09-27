package com.hs.datatrans.entity.tongdun;

public class DCardMobileConsistencyVo {
    /*银行卡号*/
    private String card;
    /*是否扣费*/
    private boolean flow_charge;
    /*手机号码*/
    private String mobile;
    /*调查结果*/
    private String result;
    /*调用结果编码*/
    private String result_code;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public boolean isFlow_charge() {
        return flow_charge;
    }

    public void setFlow_charge(boolean flow_charge) {
        this.flow_charge = flow_charge;
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
        return "DCardMobileConsistencyVo{" +
                "card='" + card + '\'' +
                ", flow_charge=" + flow_charge +
                ", mobile='" + mobile + '\'' +
                ", result='" + result + '\'' +
                ", result_code='" + result_code + '\'' +
                '}';
    }
}
