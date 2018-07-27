package com.legou.manager.dao;

import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbUser;
import com.legou.manager.pojo.po.TbUserExample;
import java.util.Map;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {

    int countByExample(TbUserExample example);

    int deleteByExample(TbUserExample example);

    int deleteByPrimaryKey(String uid);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    List<TbUser> selectByExample(TbUserExample example);

    TbUser selectByPrimaryKey(String uid);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    /*
    查询 所有 会员
     */ 

    Long countItem(Map<String, Object> map);

    List<TbUser> listitemBypage(Map<String, Object> map);
    /*
        对 会员的状态进行改变 正常 |  失效
     */
    int updateMemberStatus(@Param("memberstatus") Integer memberstatus, @Param("uid") String uid);

    Long showbackupCount();

    List<TbUser> showbackup(Map<String, Object> map);

    int backupMember(String uid);
}