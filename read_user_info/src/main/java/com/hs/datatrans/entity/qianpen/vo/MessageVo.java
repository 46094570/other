package com.hs.datatrans.entity.qianpen.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hs.datatrans.utils.qianpen.utils.AesUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 消息
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageVo<T>
{
    
    private boolean success;
    
    /**
     * 加密秘钥串（一般是用token加密）
     */
    @JsonIgnore
    private String securityKey;
    
    private T data;
    
    /**
     * 加密保存的字段
     */
    private String securityData;
    
    private String errMsg;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int errCode;
    
    /**
     * 用token加密返回
     * 
     * @param token
     */
    public MessageVo(String token)
    {
        this.securityKey = token;
    }
    
    /**
     * 默认是不加密的
     */
    public MessageVo()
    {
        
    }
    
    public boolean isSuccess()
    {
        return success;
    }
    
    public void setSuccess(boolean success)
    {
        this.success = success;
    }
    
    public T getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.success = true;
        if (StringUtils.isBlank(this.securityKey))
        {
            this.data = data;
        }
        else
        {
            if (data instanceof String)
            {
                this.securityData = AesUtil.aesEncode(String.valueOf(data), this.securityKey);
            }
            else
            {
                this.securityData = AesUtil.aesEncode(JSONObject.toJSONString(data), this.securityKey);
            }
        }
    }
    
    public String getSecurityKey()
    {
        return securityKey;
    }
    
    public void setSecurityKey(String securityKey)
    {
        this.securityKey = securityKey;
    }
    
    public String getSecurityData()
    {
        return securityData;
    }
    
    public void setSecurityData(String securityData)
    {
        this.securityData = securityData;
    }
    
    public String getErrMsg()
    {
        return errMsg;
    }
    
    public void setError(int errCode, String errMsg)
    {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    
    public void setErrMsg(String errMsg)
    {
        this.success = false;
        this.errMsg = errMsg;
    }
    
    public int getErrCode()
    {
        return errCode;
    }
    
    public void setErrCode(int errCode)
    {
        this.success = false;
        this.errCode = errCode;
    }
}
