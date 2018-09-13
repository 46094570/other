package com.hs.datatrans.entity.qianpen.vo;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "Resp"
)
public class BaseResp implements Serializable {
    private static final long serialVersionUID = -2785244997177694228L;
    private String code = "000001";
    private String description = "ERROR_UNKNOWN";

    public BaseResp() {
    }

    public void setCode(String code) {
        this.code = code;
        if ("000000".equals(code)) {
            this.setDescription("SUCCESS");
        }

    }

    @XmlElement
    public String getCode() {
        return this.code;
    }

    @XmlElement
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
