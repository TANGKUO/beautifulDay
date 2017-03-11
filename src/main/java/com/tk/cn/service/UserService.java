package com.tk.cn.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tk.cn.entity.UserEntity;
import com.tk.cn.repository.UserRepository;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.tk.com</p>   
 * @author   tangkuo
 * @date    2017年3月11日 下午3:53:21
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存对象
     *
     * @param entity
     * @return
     */
    public void save(UserEntity entity) {
        userRepository.save(entity);
        logger.info("UserService save is sucess");
    }

    /**
     * 保存更新
     *
     * @param entity
     * @return
     */
    public void saveAndFlush(UserEntity entity) {
        userRepository.saveAndFlush(entity);
    }

    /**
     * 根据ID删除数据
     *
     * @param id
     */
    public void delete(String id) {
        userRepository.delete(id);
    }

    public List<Map<String, Object>> queryList(Map<?, ?> parammap) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from t_user");
        return userRepository.nativeQuery4Map(sql.toString());
    }


    public UserEntity findUserByNamePass(String userName, String password) {
        return userRepository.findOne(" loginName=?1 and password=?2", userName, password);
    }

    public void del(UserEntity entity) {
        userRepository.delete(entity);
    }

    public UserEntity getOneById(long id) {
        return userRepository.findOne(" id=?1 ", id);
    }

    public List<Map<String, Object>> getRightList(UserEntity entity, int page, int pageSize) {
        StringBuffer sql = new StringBuffer();
        int row = (page - 1) * pageSize;
        sql.append("select * from t_user where 1=1");
        String userName = entity.getUserName();
        if (userName != null && !"".equals(userName)) {
            sql.append(" and user_name ='" + userName + "'");
        }
        String loginName = entity.getLoginName();
        if (loginName != null && !"".equals(loginName)) {
            sql.append(" and login_name ='" + loginName + "'");
        }
        sql.append(" limit " + row + "," + pageSize + "");
        return userRepository.nativeQuery4Map(sql.toString());

    }

    public int findCount(UserEntity entity) {
        int total = 0;
        String sql = "select count(1) count from t_user where 1=1";
        if (entity.getUserName() != null && !"".equals(entity.getUserName())) {
            sql += " and user_name ='" + entity.getUserName() + "'";
        }
        if (entity.getLoginName() != null && !"".equals(entity.getLoginName())) {
            sql += " and login_name ='" + entity.getLoginName() + "'";
        }
        List<Map<String, Object>> list = userRepository.nativeQuery4Map(sql);
        if (list != null && list.size() == 1) {
            Map<?, ?> map = list.get(0);
            Object count = map.get("count");
            if (count != null) {
                total = Integer.parseInt(count.toString());
            }
        }
        return total;
    }
}
