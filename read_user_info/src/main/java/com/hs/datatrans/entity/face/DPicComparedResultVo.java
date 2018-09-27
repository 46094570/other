package com.hs.datatrans.entity.face;

import java.util.List;

public class DPicComparedResultVo {
    /*本字段仅当比对为有源比对、且参数data_source_image_udpate_policy取值不为“no”时才存在	string*/
    private String data_source_image_update_status;
    /*错误消息	string*/
    private String error_message;
    /*该字段表示待比对的脸的真实性	该字段表示待比对的脸的真实性*/
    private DFaceCompareRealityVo face_genuineness;
    /*当验证照为未经detect方法检测过的照片、且return_faces参数为1时，返回本字段	< FaceVo > array*/
    private List<DFaceVo> faces;
    /*身份相关异常情况	本对象仅在有源比对时*/
    private DPicExceptionsVo id_exceptions;
    /*请求唯一字符串	string*/
    private String request_id;
    /*有源比对时，数据源人脸照片与待验证人脸照的比对结果	object*/
    private Object result_faceid;
    /*整个请求花费时间	integer (int32)*/
    private int time_used;

    public String getData_source_image_update_status() {
        return data_source_image_update_status;
    }

    public void setData_source_image_update_status(String data_source_image_update_status) {
        this.data_source_image_update_status = data_source_image_update_status;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public DFaceCompareRealityVo getFace_genuineness() {
        return face_genuineness;
    }

    public void setFace_genuineness(DFaceCompareRealityVo face_genuineness) {
        this.face_genuineness = face_genuineness;
    }

    public List<DFaceVo> getFaces() {
        return faces;
    }

    public void setFaces(List<DFaceVo> faces) {
        this.faces = faces;
    }

    public DPicExceptionsVo getId_exceptions() {
        return id_exceptions;
    }

    public void setId_exceptions(DPicExceptionsVo id_exceptions) {
        this.id_exceptions = id_exceptions;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Object getResult_faceid() {
        return result_faceid;
    }

    public void setResult_faceid(Object result_faceid) {
        this.result_faceid = result_faceid;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    @Override
    public String toString() {
        return "DPicComparedResultVo{" +
                "data_source_image_update_status='" + data_source_image_update_status + '\'' +
                ", error_message='" + error_message + '\'' +
                ", face_genuineness=" + face_genuineness +
                ", faces=" + faces +
                ", id_exceptions=" + id_exceptions +
                ", request_id='" + request_id + '\'' +
                ", result_faceid=" + result_faceid +
                ", time_used=" + time_used +
                '}';
    }
}
