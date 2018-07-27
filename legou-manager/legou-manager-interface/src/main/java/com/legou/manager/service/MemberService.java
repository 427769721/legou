package com.legou.manager.service;

import com.legou.manager.pojo.dto.MemberQuery;
import com.legou.manager.pojo.dto.MemberResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: xwh
 * Date: 2018/7/21 Time: 14:26
 * Version:V1.0
 */
public interface MemberService {


    MemberResult<TbUser> memberList(PageParam pageParam, MemberQuery memberQuery);

    TbUser editPro(String uid);

    int  edit(TbUser tbUser);

    int delete(String uid);

    int deleteCheck(List<String> uids);

    int updateMemberStatus(Integer memberstatus, String uid);

    int add(TbUser tbUser);

    MemberResult<TbUser> showbackup(@Param("pageParam") PageParam pageParam);

    int backupMember(String uid);

    int deleteBye(String uid);

    int backupuids(List<String> uids);
}
