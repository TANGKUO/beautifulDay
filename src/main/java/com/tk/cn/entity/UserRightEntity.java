package com.tk.cn.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午3:38:31
 */
@Entity
@Table(name = "t_right", schema = "", catalog = "test")
public class UserRightEntity {

    private long id;

    private long parentId;

    private String rightName;

    private String rightUrl;

    private int level;

    private int type;

    private Date createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TR_ID", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PARENT_TR_ID", nullable = true, insertable = true, updatable = true)
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "RIGHT_NAME", nullable = true, insertable = true, updatable = true, length = 64)
    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    @Basic
    @Column(name = "CREATE_TIME", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "RIGHT_URL", nullable = true, insertable = true, updatable = true)
    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }
    @Basic
    @Column(name = "LEVEL", nullable = true, insertable = true, updatable = true)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
