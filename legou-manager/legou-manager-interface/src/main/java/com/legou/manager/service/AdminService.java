package com.legou.manager.service;

import com.legou.manager.pojo.dto.AdminQuery;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbAdmin;

import java.util.List;

public interface AdminService {
    UserResult<TbAdmin> showAdmins(PageParam pageParam, AdminQuery adminQuery);

    TbAdmin showAdmin(String aid);

    int updateAdmin(TbAdmin tbAdmin);

    int removeAdmin(String aid);

    int updateAdminsByIds(List<String> aids);

    int addAdmin(TbAdmin tbAdmin);

    TbAdmin selectAdmin(String aname, String apwd);

    int updateAdminByApwd(String aid, String apwd);
}
