package com.hs.datatrans.entity.tongdun;

public class DGeographicalPositionInfoVo {

    /*IP地址所处城市*/
    private String city;
    /*ip地址所处国家*/
    private String country;
    /*ip地址所在县*/
    private String county;
    /*ip地址*/
    private String ip;
    /*互联网服务提供商，例如：电信*/
    private String isp;
    /*纬度*/
    private double latitude;
    /*long 型ip*/
    private long lip;
    /*经度;*/
    private double longitude;
    /*所在省份，例如：江苏省*/
    private String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getLip() {
        return lip;
    }

    public void setLip(long lip) {
        this.lip = lip;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "DGeographicalPositionInfoVo{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", ip='" + ip + '\'' +
                ", isp='" + isp + '\'' +
                ", latitude=" + latitude +
                ", lip=" + lip +
                ", longitude=" + longitude +
                ", province='" + province + '\'' +
                '}';
    }
}
