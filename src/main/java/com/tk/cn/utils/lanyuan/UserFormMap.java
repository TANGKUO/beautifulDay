package com.tk.cn.utils.lanyuan;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * user实体表
 */

//@TableSeg(tableName = "t_user")//自定义注解使用方式

@Entity
@Table(name = "t_user", schema = "", catalog = "test")
public class UserFormMap extends FormMap<String, Object> {

	private static final long serialVersionUID = 1L;

}
