package com.tk.cn.entity;



import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_role", schema = "", catalog = "test")
public class RoleEntity {
    private long id;
    private String roleName;
    private Date createTime;
    private String remark;


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
    @Column(name = "ROLE_NAME", nullable = true, insertable = true, updatable = true, length = 64)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
    @Column(name = "REMARK", nullable = true, insertable = true, updatable = true, length = 64)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}