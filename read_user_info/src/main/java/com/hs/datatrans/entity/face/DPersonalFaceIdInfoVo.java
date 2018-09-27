package com.hs.datatrans.entity.face;

public class DPersonalFaceIdInfoVo {
    /*身份证背面返回信息	DOcrIdCardResultVo*/
    private DOcrIdCardResultVo backResult;
    /*身份证正面返回信息	DOcrIdCardResultVo*/
    private DOcrIdCardResultVo frontResult;
    /*base64后的手持身份证图片	string*/
    private String handIdentityImg;
    /*base64后的最佳图片	string*/
    private String livingBodyImgBest;
    /*base64后的全景图片	string*/
    private String livingBodyImgEnv;
    /*base64后的活体识别动作1	string*/
    private String livingBodyImgFirst;
    /*base64后的活体识别动作2	string*/
    private String livingBodyImgSecond;
    /*base64后的活体识别动作3	string*/
    private String livingBodyImgThird;
    /*base64后的身份证背面照	string*/
    private String ocrBackImg;
    /*base64后的身份证正面照片	string*/
    private String ocrFrontImg;
    /*用户Id	integer (int64)*/
    private long userId;
    /*活体对比返回结果	图片对比返回结果*/
    private DPicComparedResultVo verifyVo;

    public DOcrIdCardResultVo getBackResult() {
        return backResult;
    }

    public void setBackResult(DOcrIdCardResultVo backResult) {
        this.backResult = backResult;
    }

    public DOcrIdCardResultVo getFrontResult() {
        return frontResult;
    }

    public void setFrontResult(DOcrIdCardResultVo frontResult) {
        this.frontResult = frontResult;
    }

    public String getHandIdentityImg() {
        return handIdentityImg;
    }

    public void setHandIdentityImg(String handIdentityImg) {
        this.handIdentityImg = handIdentityImg;
    }

    public String getLivingBodyImgBest() {
        return livingBodyImgBest;
    }

    public void setLivingBodyImgBest(String livingBodyImgBest) {
        this.livingBodyImgBest = livingBodyImgBest;
    }

    public String getLivingBodyImgEnv() {
        return livingBodyImgEnv;
    }

    public void setLivingBodyImgEnv(String livingBodyImgEnv) {
        this.livingBodyImgEnv = livingBodyImgEnv;
    }

    public String getLivingBodyImgFirst() {
        return livingBodyImgFirst;
    }

    public void setLivingBodyImgFirst(String livingBodyImgFirst) {
        this.livingBodyImgFirst = livingBodyImgFirst;
    }

    public String getLivingBodyImgSecond() {
        return livingBodyImgSecond;
    }

    public void setLivingBodyImgSecond(String livingBodyImgSecond) {
        this.livingBodyImgSecond = livingBodyImgSecond;
    }

    public String getLivingBodyImgThird() {
        return livingBodyImgThird;
    }

    public void setLivingBodyImgThird(String livingBodyImgThird) {
        this.livingBodyImgThird = livingBodyImgThird;
    }

    public String getOcrBackImg() {
        return ocrBackImg;
    }

    public void setOcrBackImg(String ocrBackImg) {
        this.ocrBackImg = ocrBackImg;
    }

    public String getOcrFrontImg() {
        return ocrFrontImg;
    }

    public void setOcrFrontImg(String ocrFrontImg) {
        this.ocrFrontImg = ocrFrontImg;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public DPicComparedResultVo getVerifyVo() {
        return verifyVo;
    }

    public void setVerifyVo(DPicComparedResultVo verifyVo) {
        this.verifyVo = verifyVo;
    }

    @Override
    public String toString() {
        return "DPersonalFaceIdInfoVo{" +
                "\nbackResult=" + backResult +
                ", \nfrontResult=" + frontResult +
                ", \nhandIdentityImg='" + handIdentityImg + '\'' +
                ", \nlivingBodyImgBest='" + livingBodyImgBest + '\'' +
                ", \nlivingBodyImgEnv='" + livingBodyImgEnv + '\'' +
                ", \nlivingBodyImgFirst='" + livingBodyImgFirst + '\'' +
                ", \nlivingBodyImgSecond='" + livingBodyImgSecond + '\'' +
                ", \nlivingBodyImgThird='" + livingBodyImgThird + '\'' +
                ", \nocrBackImg='" + ocrBackImg + '\'' +
                ", \nocrFrontImg='" + ocrFrontImg + '\'' +
                ", \nuserId=" + userId +
                ", \nverifyVo=" + verifyVo +
                '}';
    }
}
