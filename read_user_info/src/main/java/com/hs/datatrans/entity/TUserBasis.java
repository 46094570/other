package com.hs.datatrans.entity;

public class TUserBasis {
    /**
     * 用户ID
     */
    private String userId;

    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 出生日期
     */
    private String dateBirth;

    /**
     * 性别；0：男，1：女
     */
    private String gender;

    /**
     * 年龄
     */
    private int age;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 身份证正面照图片URL
     */
    private String idCardFrontUrl;

    /**
     * 身份证反面照URL
     */
    private String idCardBackUrl;

    /**
     * 人脸照片
     */
    private String faceImgUrl;

    /**
     * '实名认证是否通过，0：是、1：否',
     */
    private String isIdCardCheckThrough;

    /**
     * 是否人脸识别（0未识别、1：识别成功、2：识别失败）
     */
    private String faceRecogniteResult;

    /**
     * '是否添加联系人，0：是、1：否',
     */
    private String isContactInfo;

    /**
     * '是否添加基本信息，0：是、1：否',
     */
    private String isBasicInfo;

    /**
     * '是否征信信息认证，0：是、1：否'
     */
    private String isPersonalInfo;

    /**
     * 是否添加银行卡绑定，0：是、1：否
     */
    private String isBankCardBind;

    /**
     * 是否提交附件上传
     */
    private String isUserAttach;

    /**
     * 所有认证是否通过，0：是、1：否
     */
    private String isAllCheckThrough;

    /**
     * 额度类别(0:尚无额度【现金贷】、1：学生贷、2：业主贷)
     */
    private String type;

    /**
     * 是否认证过一次【如认证过一次、则不显示  尚无额度】0：是、1：否
     */
    private String isFirstAuth;

    /**
     * 是否芝麻信用授权
     */
    private String isSesameAuth;

    /**
     * 用户头像
     * @return
     */
    private String imageUrl;

    private String activeTime;

    /**
     * 是否认证工作证明，0：是、1：否
     */
    private String isWorkProof;

    /**
     * 是否认证手机联系人，0：是、1：否
     */
    private String isMobile;

    /**
     * 是否黑名单：0 不是黑名单 1 是黑名单
     */
    private String isBlacklist;

    public String getIsPersonalInfo() {
        return isPersonalInfo;
    }

    public void setIsPersonalInfo(String isPersonalInfo) {
        this.isPersonalInfo = isPersonalInfo;
    }

    public String getIsMobile()
    {
        return isMobile;
    }

    public void setIsMobile(String isMobile)
    {
        this.isMobile = isMobile;
    }

    public String getIsWorkProof()
    {
        return isWorkProof;
    }

    public void setIsWorkProof(String isWorkProof)
    {
        this.isWorkProof = isWorkProof;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    public String getDateBirth()
    {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth)
    {
        this.dateBirth = dateBirth;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getIdCardFrontUrl()
    {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl)
    {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    public String getIdCardBackUrl()
    {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl)
    {
        this.idCardBackUrl = idCardBackUrl;
    }

    public String getFaceImgUrl()
    {
        return faceImgUrl;
    }

    public void setFaceImgUrl(String faceImgUrl)
    {
        this.faceImgUrl = faceImgUrl;
    }

    public String getIsIdCardCheckThrough()
    {
        return isIdCardCheckThrough;
    }

    public void setIsIdCardCheckThrough(String isIdCardCheckThrough)
    {
        this.isIdCardCheckThrough = isIdCardCheckThrough;
    }

    public String getFaceRecogniteResult()
    {
        return faceRecogniteResult;
    }

    public void setFaceRecogniteResult(String faceRecogniteResult)
    {
        this.faceRecogniteResult = faceRecogniteResult;
    }

    public String getIsContactInfo()
    {
        return isContactInfo;
    }

    public void setIsContactInfo(String isContactInfo)
    {
        this.isContactInfo = isContactInfo;
    }

    public String getIsBasicInfo()
    {
        return isBasicInfo;
    }

    public void setIsBasicInfo(String isBasicInfo)
    {
        this.isBasicInfo = isBasicInfo;
    }

    public String getIsBankCardBind()
    {
        return isBankCardBind;
    }

    public void setIsBankCardBind(String isBankCardBind)
    {
        this.isBankCardBind = isBankCardBind;
    }

    public String getIsAllCheckThrough()
    {
        return isAllCheckThrough;
    }

    public void setIsAllCheckThrough(String isAllCheckThrough)
    {
        this.isAllCheckThrough = isAllCheckThrough;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getIsFirstAuth()
    {
        return isFirstAuth;
    }

    public void setIsFirstAuth(String isFirstAuth)
    {
        this.isFirstAuth = isFirstAuth;
    }

    public String getIsUserAttach()
    {
        return isUserAttach;
    }

    public void setIsUserAttach(String isUserAttach)
    {
        this.isUserAttach = isUserAttach;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getIsSesameAuth()
    {
        return isSesameAuth;
    }

    public void setIsSesameAuth(String isSesameAuth)
    {
        this.isSesameAuth = isSesameAuth;
    }

    public String getActiveTime()
    {
        return activeTime;
    }

    public void setActiveTime(String activeTime)
    {
        this.activeTime = activeTime;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getIsBlacklist()
    {
        return isBlacklist;
    }

    public void setIsBlacklist(String isBlacklist)
    {
        this.isBlacklist = isBlacklist;
    }

    public void clean(){
        this.setActiveTime(null);
        this.setAge(0);
        this.setCreateTime(null);
        this.setDateBirth(null);
        this.setFaceImgUrl(null);
        this.setFaceRecogniteResult(null);
        this.setGender(null);
        this.setIdCard(null);
        this.setIdCardBackUrl(null);
        this.setIdCardFrontUrl(null);
        this.setImageUrl(null);
        this.setIsAllCheckThrough(null);
        this.setIsBankCardBind(null);
        this.setIsBasicInfo(null);
        this.setIsBlacklist(null);
        this.setIsContactInfo(null);
        this.setIsFirstAuth(null);
        this.setIsIdCardCheckThrough(null);
        this.setIsMobile(null);
        this.setIsPersonalInfo(null);
        this.setIsSesameAuth(null);
        this.setIsUserAttach(null);
        this.setIsWorkProof(null);
        this.setRealName(null);
        this.setType(null);
        this.setUpdateTime(null);
        this.setUserId(null);
        this.setUserName(null);
    }

    @Override
    public String toString() {
        return "TUserBasis{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", dateBirth='" + dateBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", idCard='" + idCard + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", idCardFrontUrl='" + idCardFrontUrl + '\'' +
                ", idCardBackUrl='" + idCardBackUrl + '\'' +
                ", faceImgUrl='" + faceImgUrl + '\'' +
                ", isIdCardCheckThrough='" + isIdCardCheckThrough + '\'' +
                ", faceRecogniteResult='" + faceRecogniteResult + '\'' +
                ", isContactInfo='" + isContactInfo + '\'' +
                ", isBasicInfo='" + isBasicInfo + '\'' +
                ", isPersonalInfo='" + isPersonalInfo + '\'' +
                ", isBankCardBind='" + isBankCardBind + '\'' +
                ", isUserAttach='" + isUserAttach + '\'' +
                ", isAllCheckThrough='" + isAllCheckThrough + '\'' +
                ", type='" + type + '\'' +
                ", isFirstAuth='" + isFirstAuth + '\'' +
                ", isSesameAuth='" + isSesameAuth + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", activeTime='" + activeTime + '\'' +
                ", isWorkProof='" + isWorkProof + '\'' +
                ", isMobile='" + isMobile + '\'' +
                ", isBlacklist='" + isBlacklist + '\'' +
                '}';
    }
}
