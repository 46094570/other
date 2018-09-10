package com.hs.datatrans.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TUserAccount implements Serializable {
    private static final long serialVersionUID = 686428276092920002L;
    /**
     * id,主键
     */
    private String id;

    /**
     * 用户id,t_user.user_id
     */
    private String userId;

    /**
     * 总余额
     */
    private BigDecimal total;

    /**
     * 可用余额
     */
    private BigDecimal available;

    /**
     * 锁定金额
     */
    private BigDecimal locked;

    /**
     * 最后更新时间
     */
    private Date lastupdate;

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

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

    public BigDecimal getAvailable()
    {
        return available;
    }

    public void setAvailable(BigDecimal available)
    {
        this.available = available;
    }

    public BigDecimal getLocked()
    {
        return locked;
    }

    public void setLocked(BigDecimal locked)
    {
        this.locked = locked;
    }

    public Date getLastupdate()
    {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate)
    {
        this.lastupdate = lastupdate;
    }

    public void clean(){
        this.setAvailable(null);
        this.setId(null);
        this.setLastupdate(null);
        this.setLocked(null);
        this.setTotal(null);
        this.setUserId(null);
    }

    @Override
    public String toString() {
        return "TUserAccount{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", total=" + total +
                ", available=" + available +
                ", locked=" + locked +
                ", lastupdate=" + lastupdate +
                '}';
    }
}
