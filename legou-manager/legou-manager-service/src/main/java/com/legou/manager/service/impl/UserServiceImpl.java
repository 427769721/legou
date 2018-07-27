package com.legou.manager.service.impl;

import com.legou.manager.dao.TbUserMapper;
import com.legou.manager.dao.UserResultMapper;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserQuery;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbUser;
import com.legou.manager.pojo.po.TbUserExample;
import com.legou.manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserResultMapper userResultMapper;
    @Autowired
    private TbUserMapper tbUserMapper;
    
    
    //查询   展示所有
    @Override
    public UserResult<TbUser> searchUsers(PageParam pageParam, UserQuery userQuery) {
        UserResult<TbUser> userResult = new UserResult<>();
        userResult.setMsg("select success");
        userResult.setCode(0);

        Map<String, Object> map = new HashMap<>();
        map.put("pageParam", pageParam);
        map.put("userQuery", userQuery);
        
        try {
            long count = userResultMapper.countUsers(map);
            userResult.setCount(count);
            
            List<TbUser> data = userResultMapper.listUsers(map);
            userResult.setData(data);
            
        } catch (Exception e){
            userResult.setMsg("select failed");
            userResult.setCode(1);
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return userResult;
    }
    
    //批量删除
    @Override
    public int updateUsersByIds(List<String> ids) {
        int i = 0;
        
        try {
            TbUser tbUser = new TbUser();
            tbUser.setStatus(1);
            //创建模板
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUidIn(ids);

            i = tbUserMapper.updateByExampleSelective(tbUser, example);


        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        
        return i;
    }
    
    //根据id进行查询
    @Override
    public TbUser showUser(String uid) {
        TbUser tbUser = new TbUser();

        try {
            tbUser  = tbUserMapper.selectByPrimaryKey(uid);

        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return tbUser;
    }
    
    //更新用户
    @Override
    public int updateUser(TbUser tbUser) {
        int i = 0;

        try {
            i  = tbUserMapper.updateByPrimaryKeySelective(tbUser);

        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }
    
    
    //删除用户
    @Override
    public int removeUser(String uid) {
        int i = 0;

        try {
            //删除，实际上是更新status为1
            TbUser tbUser = new TbUser();
            tbUser.setStatus(1);
            //创建模板
            TbUserExample example = new TbUserExample();
            TbUserExample.Criteria criteria = example.createCriteria();
            criteria.andUidEqualTo(uid);

            i = tbUserMapper.updateByExampleSelective(tbUser, example);

        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public int addUser(TbUser tbUser) {
        tbUser.setUid(UUID.randomUUID().toString().substring(0,9));
        
        int i = 0;

        try {
            i = tbUserMapper.insertSelective(tbUser);

        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        
        return i;
    }


}
