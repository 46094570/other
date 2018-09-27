package com.hs.datatrans.entity.tongdun;

import java.util.List;

public class DFrequencyDetailVo {
    private List<String> data;
    private String detail;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "DFrequencyDetailVo{" +
                "data=" + data +
                ", detail='" + detail + '\'' +
                '}';
    }
}
