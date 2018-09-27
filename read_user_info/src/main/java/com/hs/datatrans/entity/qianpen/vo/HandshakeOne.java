package com.hs.datatrans.entity.qianpen.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HandshakeOne implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String servicePublicKey;
    
    private String appId;
    
    public String getServicePublicKey()
    {
        return servicePublicKey;
    }
    
    public void setServicePublicKey(String servicePublicKey)
    {
        this.servicePublicKey = servicePublicKey;
    }
    
    public String getAppId()
    {
        return appId;
    }
    
    public void setAppId(String appId)
    {
        this.appId = appId;
    }
    
}
