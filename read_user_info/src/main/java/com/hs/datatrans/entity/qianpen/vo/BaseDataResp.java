package com.hs.datatrans.entity.qianpen.vo;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        name = "Resp"
)
public class BaseDataResp extends BaseResp implements Serializable {
    private static final long serialVersionUID = -4774869782747473448L;
    private Object data;

    public BaseDataResp() {
    }

    @XmlElement(
            name = "data"
    )
    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
