package com.hs.datatrans.entity.face;

public class DPicExceptionsVo {
    /*判别身份证号码是否曾被冒用来攻击FaceID活体检测，取值1表示曾被攻击、取值0表示未被攻击*/
    private int id_attacked;
    /*数据源人像照片的色彩判断，取值1表示是黑白照片、取值0表示是彩色照片*/
    private int id_photo_monochrome;

    public int getId_attacked() {
        return id_attacked;
    }

    public void setId_attacked(int id_attacked) {
        this.id_attacked = id_attacked;
    }

    public int getId_photo_monochrome() {
        return id_photo_monochrome;
    }

    public void setId_photo_monochrome(int id_photo_monochrome) {
        this.id_photo_monochrome = id_photo_monochrome;
    }

    @Override
    public String toString() {
        return "DPicExceptionsVo{" +
                "id_attacked=" + id_attacked +
                ", id_photo_monochrome=" + id_photo_monochrome +
                '}';
    }
}
