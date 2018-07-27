package com.legou.manager.dao;

import com.legou.manager.pojo.po.TbAdmin;

import java.util.List;
import java.util.Map;

public interface AdminResultMapper {
    long countAdmins(Map<String, Object> map);

    List<TbAdmin> listAdmins(Map<String, Object> map);
}
