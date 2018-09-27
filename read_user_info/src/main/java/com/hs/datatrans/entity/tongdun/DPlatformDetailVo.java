package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DPlatformDetailVo {

    /*维度命中多头个数*/
    private int count;
    /*维度命中多头详情*/
    private List<String> detail;
    /*维度展示名*/
   private String dimension;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "DPlatformDetailVo{" +
                "count=" + count +
                ", detail=" + detail +
                ", dimension='" + dimension + '\'' +
                '}';
    }

}
