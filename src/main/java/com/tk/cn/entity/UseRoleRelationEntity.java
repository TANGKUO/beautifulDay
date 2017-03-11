package com.tk.cn.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user_role_relation", schema = "", catalog = "test")
public class UseRoleRelationEntity {
    private long id;
    private long userId;
    private long roleId;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TUR_ID", nullable = false, insertable = true, updatable = true)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "USER_ID", nullable = true, insertable = true, updatable = true)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "ROLE_ID", nullable = true, insertable = true, updatable = true)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UseRoleRelationEntity)) return false;

        UseRoleRelationEntity that = (UseRoleRelationEntity) o;

        if (id != that.id) return false;
        if (roleId != that.roleId) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        return result;
    }
}