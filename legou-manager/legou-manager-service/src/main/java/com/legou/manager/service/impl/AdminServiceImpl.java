package com.legou.manager.service.impl;

import com.legou.manager.dao.AdminResultMapper;
import com.legou.manager.dao.TbAdminMapper;
import com.legou.manager.pojo.dto.AdminQuery;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbAdmin;
import com.legou.manager.pojo.po.TbAdminExample;
import com.legou.manager.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbAdminMapper tbAdminMapper;
    @Autowired
    private AdminResultMapper adminResultMapper;
    
    @Override
    public UserResult<TbAdmin> showAdmins(PageParam pageParam, AdminQuery adminQuery) {
        UserResult<TbAdmin> userResult = new UserResult<>();
        userResult.setMsg("select success");
        userResult.setCode(0);

        Map<String, Object> map = new HashMap<>();
        map.put("pageParam", pageParam);
        map.put("adminQuery", adminQuery);

        try {
            long count = adminResultMapper.countAdmins(map);
            userResult.setCount(count);

            List<TbAdmin> data = adminResultMapper.listAdmins(map);
            userResult.setData(data);

        } catch (Exception e){
            userResult.setMsg("select failed");
            userResult.setCode(1);
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return userResult;
    }

    @Override
    public TbAdmin showAdmin(String aid) {
        TbAdmin tbAdmin = new TbAdmin();

        try {
            tbAdmin  = tbAdminMapper.selectByPrimaryKey(aid);

        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return tbAdmin;
    }

    @Override
    public int updateAdmin(TbAdmin tbAdmin) {
        int i = 0;

        try {
            i  = tbAdminMapper.updateByPrimaryKeySelective(tbAdmin);

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
    public int removeAdmin(String aid) {
        int i = 0;

        try {
            //删除，实际上是更新status为1
            TbAdmin tbAdmin = new TbAdmin();
            tbAdmin.setStatus(1);
            //创建模板
            TbAdminExample example = new TbAdminExample();
            TbAdminExample.Criteria criteria = example.createCriteria();
            criteria.andAidEqualTo(aid);

            i = tbAdminMapper.updateByExampleSelective(tbAdmin, example);

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
    public int updateAdminsByIds(List<String> aids) {
        int i = 0;

        try {
            TbAdmin tbAdmin = new TbAdmin();
            tbAdmin.setStatus(1);
            //创建模板
            TbAdminExample example = new TbAdminExample();
            TbAdminExample.Criteria criteria = example.createCriteria();
            criteria.andAidIn(aids);

            i = tbAdminMapper.updateByExampleSelective(tbAdmin, example);


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
    public int addAdmin(TbAdmin tbAdmin) {
        tbAdmin.setAid(UUID.randomUUID().toString().substring(0,5));

        int i = 0;

        try {
            i = tbAdminMapper.insertSelective(tbAdmin);

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
    public TbAdmin selectAdmin(String aname, String apwd) {
        TbAdmin tbAdmin = new TbAdmin();

        try {
            
            //创建模板
            TbAdminExample example = new TbAdminExample();
            TbAdminExample.Criteria criteria = example.createCriteria();
            criteria.andAnameEqualTo(aname);
            criteria.andApwdEqualTo(apwd);
            List<TbAdmin> list = tbAdminMapper.selectByExample(example);
            tbAdmin = list.get(0);
            
        } catch (RuntimeException e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        
        return tbAdmin;
    }

    @Override
    public int updateAdminByApwd(String aid, String apwd) {
        int i = 0;

        try {

            TbAdmin tbAdmin = new TbAdmin();
            tbAdmin.setAid(aid);
            tbAdmin.setApwd(apwd);
            
            i = tbAdminMapper.updateByPrimaryKeySelective(tbAdmin);

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
