package com.tk.cn.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tk.cn.entity.UserEntity;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午3:45:31
 */
@Repository
public interface UserRepository extends PlatformJpaRepository<UserEntity> {

    @Query("select t from UserEntity t where t.loginName=?1 and t.password=?2")
    public UserEntity getUserByNamePass(String userName,String password);

	public List<Map<String, Object>> nativeQuery4Map(String string);

	public UserEntity findOne(String string, String userName, String password);

	public UserEntity findOne(String string, long id);
}