package com.hs.datatrans.entity.face;

public class DHumanFacePositionVo {
    private double height;
    private double left;
    private double top;
    private double width;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "DHumanFacePositionVo{" +
                "height=" + height +
                ", left=" + left +
                ", top=" + top +
                ", width=" + width +
                '}';
    }
}
