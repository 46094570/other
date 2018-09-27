package com.hs.datatrans.entity.face;

public class DIdentityHeadPositionVo {
    private DCoordinateVo lb;
    private DCoordinateVo lt;
    private DCoordinateVo rb;
    private DCoordinateVo rt;

    public DCoordinateVo getLb() {
        return lb;
    }

    public void setLb(DCoordinateVo lb) {
        this.lb = lb;
    }

    public DCoordinateVo getLt() {
        return lt;
    }

    public void setLt(DCoordinateVo lt) {
        this.lt = lt;
    }

    public DCoordinateVo getRb() {
        return rb;
    }

    public void setRb(DCoordinateVo rb) {
        this.rb = rb;
    }

    public DCoordinateVo getRt() {
        return rt;
    }

    public void setRt(DCoordinateVo rt) {
        this.rt = rt;
    }

    @Override
    public String toString() {
        return "DIdentityHeadPositionVo{" +
                "lb=" + lb +
                ", lt=" + lt +
                ", rb=" + rb +
                ", rt=" + rt +
                '}';
    }
}
