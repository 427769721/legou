package com.legou.manager.service;

import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserQuery;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbUser;

import java.util.List;

public interface UserService {

    UserResult<TbUser> searchUsers(PageParam pageParam, UserQuery userQuery);

    int updateUsersByIds(List<String> ids);

    TbUser showUser(String uid);

    int updateUser(TbUser tbUser);

    int removeUser(String uid);

    int addUser(TbUser tbUser);
}
