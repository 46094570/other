package com.hs.datatrans.entity.face;

public class DFaceVo {
    private int orientation;
    private double quality;
    private double quality_threshold;
    private DHumanFacePositionVo rect;
    private String token;

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

    public double getQuality_threshold() {
        return quality_threshold;
    }

    public void setQuality_threshold(double quality_threshold) {
        this.quality_threshold = quality_threshold;
    }

    public DHumanFacePositionVo getRect() {
        return rect;
    }

    public void setRect(DHumanFacePositionVo rect) {
        this.rect = rect;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "DFaceVo{" +
                "orientation=" + orientation +
                ", quality=" + quality +
                ", quality_threshold=" + quality_threshold +
                ", rect=" + rect +
                ", token='" + token + '\'' +
                '}';
    }
}
