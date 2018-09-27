package com.hs.datatrans.entity.face;

public class DFaceCompareRealityVo {
    /*Int类型，只取值0或1。0表示未检测出换脸攻击，1表示检测出了换脸攻击	number (float)*/
    private double mask_confidence;
    /*人脸照片为面具的置信度	number (float)*/
    private int face_replaced;
    /*人脸照片为面具的置信度	number (float)*/
    private double mask_threshold;
    /*人脸照片为面具的置信度阈值	number (float)*/
    private double screen_replay_confidence;
    /*人脸照片为屏幕翻拍的置信度阈值	number (float)*/
    private double screen_replay_threshold;
    /*脸照片为软件合成脸的置信度	number (float)*/
    private double synthetic_face_confidence;
    /*人脸照片为软件合成脸的置信度阈值	number (float)*/
    private double synthetic_face_threshold;

    public double getMask_confidence() {
        return mask_confidence;
    }

    public void setMask_confidence(double mask_confidence) {
        this.mask_confidence = mask_confidence;
    }

    public int getFace_replaced() {
        return face_replaced;
    }

    public void setFace_replaced(int face_replaced) {
        this.face_replaced = face_replaced;
    }

    public double getMask_threshold() {
        return mask_threshold;
    }

    public void setMask_threshold(double mask_threshold) {
        this.mask_threshold = mask_threshold;
    }

    public double getScreen_replay_confidence() {
        return screen_replay_confidence;
    }

    public void setScreen_replay_confidence(double screen_replay_confidence) {
        this.screen_replay_confidence = screen_replay_confidence;
    }

    public double getScreen_replay_threshold() {
        return screen_replay_threshold;
    }

    public void setScreen_replay_threshold(double screen_replay_threshold) {
        this.screen_replay_threshold = screen_replay_threshold;
    }

    public double getSynthetic_face_confidence() {
        return synthetic_face_confidence;
    }

    public void setSynthetic_face_confidence(double synthetic_face_confidence) {
        this.synthetic_face_confidence = synthetic_face_confidence;
    }

    public double getSynthetic_face_threshold() {
        return synthetic_face_threshold;
    }

    public void setSynthetic_face_threshold(double synthetic_face_threshold) {
        this.synthetic_face_threshold = synthetic_face_threshold;
    }

    @Override
    public String toString() {
        return "DFaceCompareRealityVo{" +
                "mask_confidence=" + mask_confidence +
                ", face_replaced=" + face_replaced +
                ", mask_threshold=" + mask_threshold +
                ", screen_replay_confidence=" + screen_replay_confidence +
                ", screen_replay_threshold=" + screen_replay_threshold +
                ", synthetic_face_confidence=" + synthetic_face_confidence +
                ", synthetic_face_threshold=" + synthetic_face_threshold +
                '}';
    }
}
