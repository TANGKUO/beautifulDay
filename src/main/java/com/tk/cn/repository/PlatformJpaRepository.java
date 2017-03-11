package com.tk.cn.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformJpaRepository<T> extends JpaRepository<T, Serializable> {

}
