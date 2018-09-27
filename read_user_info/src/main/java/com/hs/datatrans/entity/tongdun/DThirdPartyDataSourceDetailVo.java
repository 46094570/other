package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DThirdPartyDataSourceDetailVo {

    /*银行卡号-手机号一致性验证详情*/
    private List<DCardMobileConsistencyVo> card_mobile_check;
    /*银行卡号-姓名一致性验证详情 */
    private List<DCardNameConsistencyVo> card_name_check;
    /*学历查询详情*/
    private List<DAcademicDetailVo> degree_query;
    /* 手机号-身份证一致性验证详情*/
    private List<DPhoneIdentityConsistencyVo> mobile_id_number_check;
    /*手机号-身份证-姓名一致性验证详情*/
    private List<DPhoneIdentityNameConsistencyVo> mobile_id_number_name_check;
    /*手机号-姓名一致性验证详情*/
    private List<DPhoneNameConsistencyVo> mobile_name_check;
    /*身份证-姓名一致性验证详情*/
    private List<DIdentityNameConsistencyVo> real_name_check;

    public List<DCardMobileConsistencyVo> getCard_mobile_check() {
        return card_mobile_check;
    }

    public void setCard_mobile_check(List<DCardMobileConsistencyVo> card_mobile_check) {
        this.card_mobile_check = card_mobile_check;
    }

    public List<DCardNameConsistencyVo> getCard_name_check() {
        return card_name_check;
    }

    public void setCard_name_check(List<DCardNameConsistencyVo> card_name_check) {
        this.card_name_check = card_name_check;
    }

    public List<DAcademicDetailVo> getDegree_query() {
        return degree_query;
    }

    public void setDegree_query(List<DAcademicDetailVo> degree_query) {
        this.degree_query = degree_query;
    }

    public List<DPhoneIdentityConsistencyVo> getMobile_id_number_check() {
        return mobile_id_number_check;
    }

    public void setMobile_id_number_check(List<DPhoneIdentityConsistencyVo> mobile_id_number_check) {
        this.mobile_id_number_check = mobile_id_number_check;
    }

    public List<DPhoneIdentityNameConsistencyVo> getMobile_id_number_name_check() {
        return mobile_id_number_name_check;
    }

    public void setMobile_id_number_name_check(List<DPhoneIdentityNameConsistencyVo> mobile_id_number_name_check) {
        this.mobile_id_number_name_check = mobile_id_number_name_check;
    }

    public List<DPhoneNameConsistencyVo> getMobile_name_check() {
        return mobile_name_check;
    }

    public void setMobile_name_check(List<DPhoneNameConsistencyVo> mobile_name_check) {
        this.mobile_name_check = mobile_name_check;
    }

    public List<DIdentityNameConsistencyVo> getReal_name_check() {
        return real_name_check;
    }

    public void setReal_name_check(List<DIdentityNameConsistencyVo> real_name_check) {
        this.real_name_check = real_name_check;
    }

    @Override
    public String toString() {
        return "DThirdPartyDataSourceDetailVo{" +
                "card_mobile_check=" + card_mobile_check +
                ", card_name_check=" + card_name_check +
                ", degree_query=" + degree_query +
                ", mobile_id_number_check=" + mobile_id_number_check +
                ", mobile_id_number_name_check=" + mobile_id_number_name_check +
                ", mobile_name_check=" + mobile_name_check +
                ", real_name_check=" + real_name_check +
                '}';
    }
}
