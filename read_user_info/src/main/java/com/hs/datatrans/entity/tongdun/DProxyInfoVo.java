package com.hs.datatrans.entity.tongdun;

public class DProxyInfoVo {
    /*代理端口*/
    private String port;
    /*代理协议*/
    private String proxyProtocol;
    /*代理类型*/
    private String proxyType;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProxyProtocol() {
        return proxyProtocol;
    }

    public void setProxyProtocol(String proxyProtocol) {
        this.proxyProtocol = proxyProtocol;
    }

    public String getProxyType() {
        return proxyType;
    }

    public void setProxyType(String proxyType) {
        this.proxyType = proxyType;
    }

    @Override
    public String toString() {
        return "DProxyInfoVo{" +
                "port='" + port + '\'' +
                ", proxyProtocol='" + proxyProtocol + '\'' +
                ", proxyType='" + proxyType + '\'' +
                '}';
    }
}
