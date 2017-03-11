package com.tk.cn.utils.lanyuan;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tk.cn.annotation.TableSeg;

//@TableSeg(tableName = "t_server_info")//自定义注解使用方式

@Entity
@Table(name = "t_server_info", schema = "", catalog = "test")
public class ServerInfoFormMap extends FormMap<String, Object> {
	private static final long serialVersionUID = 1L;
}