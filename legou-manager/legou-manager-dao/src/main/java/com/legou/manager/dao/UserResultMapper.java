package com.legou.manager.dao;

import com.legou.manager.pojo.po.TbUser;

import java.util.List;
import java.util.Map;

public interface UserResultMapper {
    
    long countUsers(Map<String, Object> map);

    List<TbUser> listUsers(Map<String, Object> map);

}
