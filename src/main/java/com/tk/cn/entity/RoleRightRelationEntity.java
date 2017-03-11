package com.tk.cn.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_role_right_relation", schema = "", catalog = "test")
public class RoleRightRelationEntity {
    private long id;
    private long roleId;
    private long rightId;
    private long rightType;
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
    @Column(name = "ROLE_ID", nullable = true, insertable = true, updatable = true)
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
    @Basic
    @Column(name = "RIGHT_ID", nullable = true, insertable = true, updatable = true)
    public long getRightId() {
        return rightId;
    }

    public void setRightId(long rightId) {
        this.rightId = rightId;
    }
    @Basic
    @Column(name = "RIGHT_TYPE", nullable = true, insertable = true, updatable = true)
    public long getRightType() {
        return rightType;
    }

    public void setRightType(long rightType) {
        this.rightType = rightType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleRightRelationEntity)) return false;

        RoleRightRelationEntity that = (RoleRightRelationEntity) o;

        if (id != that.id) return false;
        if (rightId != that.rightId) return false;
        if (rightType != that.rightType) return false;
        if (roleId != that.roleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (rightId ^ (rightId >>> 32));
        result = 31 * result + (int) (rightType ^ (rightType >>> 32));
        return result;
    }
}