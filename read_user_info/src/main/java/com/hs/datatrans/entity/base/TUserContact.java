package com.hs.datatrans.entity.base;


public class TUserContact
{
    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 联系人姓名
     */
    private String contactPeopleName;

    /**
     * 联系人手机号
     */
    private String contactPeoplePhone;

    /**
     * 联系人关系，0：亲人，1：朋友，2：，3：同事
     */
    private String contactRelationship;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getContactPeopleName()
    {
        return contactPeopleName;
    }

    public void setContactPeopleName(String contactPeopleName)
    {
        this.contactPeopleName = contactPeopleName;
    }

    public String getContactPeoplePhone()
    {
        return contactPeoplePhone;
    }

    public void setContactPeoplePhone(String contactPeoplePhone)
    {
        this.contactPeoplePhone = contactPeoplePhone;
    }

    public String getContactRelationship()
    {
        return contactRelationship;
    }

    public void setContactRelationship(String contactRelationship)
    {
        this.contactRelationship = contactRelationship;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

}