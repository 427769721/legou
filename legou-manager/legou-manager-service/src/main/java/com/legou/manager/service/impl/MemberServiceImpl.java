package com.legou.manager.service.impl;

import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.PageHelper;
import com.legou.manager.dao.TbUserMapper;
import com.legou.manager.pojo.dto.MemberQuery;
import com.legou.manager.pojo.dto.MemberResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbUser;
import com.legou.manager.pojo.po.TbUserExample;
import com.legou.manager.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User: xwh
 * Date: 2018/7/21 Time: 14:27
 * Version:V1.0
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TbUserMapper tbUserMapper;


    @Override
    public MemberResult<TbUser> memberList(PageParam pageParam, MemberQuery memberQuery) {
        MemberResult<TbUser> result = new MemberResult<>();
        result.setCode(0);
        result.setMsg("恭喜！");
        int membership =0;
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("pageParam",pageParam);
            map.put("membership",membership);
            map.put("memberQuery", memberQuery);
            Long count =tbUserMapper.countItem(map);
            result.setCount(count);
            List<TbUser> data  =  tbUserMapper.listitemBypage(map);
            result.setData(data);

        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public TbUser editPro(String uid) {
        TbUser user = new TbUser();
        try {
            user =tbUserMapper.selectByPrimaryKey(uid);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int edit(TbUser tbUser) {

/*
        TbUser user = tbUserMapper.selectByPrimaryKey(tbUser.getUid());
        user.setGender(tbUser.getGender());
        user.setAddress(tbUser.getAddress());
        user.setUname(tbUser.getUname());
        user.setMailbox(tbUser.getMailbox());
        user.setPhone(tbUser.getPhone());
        user.setMembership(tbUser.getMembership());*/



        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUidEqualTo(tbUser.getUid());

        int i = 0;
        try {
            i= tbUserMapper.updateByExampleSelective(tbUser,tbUserExample);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int delete(String uid) {
        int i = 0;
        try {
            TbUser tbUser = new TbUser();
            tbUser.setStatus(1);

            TbUserExample tbUserExample = new TbUserExample();
            TbUserExample.Criteria criteria = tbUserExample.createCriteria();
            criteria.andUidEqualTo(uid);
            i=tbUserMapper.updateByExampleSelective(tbUser,tbUserExample);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteCheck(List<String> uids) {

        int i =0;
        try {
            TbUser tbUser = new TbUser();
            tbUser.setStatus(1);

            TbUserExample tbUserExample = new TbUserExample();
            TbUserExample.Criteria criteria = tbUserExample.createCriteria();
            criteria.andUidIn(uids);
            i=tbUserMapper.updateByExampleSelective(tbUser,tbUserExample);


        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i ;
    }

    @Override
    public int updateMemberStatus(Integer memberstatus, String uid) {
        int i = 0;
        try {
           i =tbUserMapper.updateMemberStatus(memberstatus,uid);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int add(TbUser tbUser) {


        String uid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        tbUser.setUid(uid);

        /*
            判断 他是不是会员 不过不是 0  就是会员 可以开启那个memberstatus = 0
         */

        if(tbUser.getMembership()!=0){
            tbUser.setMemberstatus(0);
        }else{
            tbUser.setMemberstatus(1);
        }

        tbUser.setStatus(0);

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tbUser.setLasttime(dateFormat.format(date));

        System.out.println("jinlaile "+tbUser);
        int i = 0;
        try {
            i= tbUserMapper.insert(tbUser);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public MemberResult<TbUser> showbackup(PageParam pageParam) {

        MemberResult<TbUser> result = new MemberResult<>();
        result.setCode(0);
        result.setMsg("恭喜！");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("pageParam",pageParam);
            Long count =tbUserMapper.showbackupCount();
            result.setCount(count);
            List<TbUser> data  =  tbUserMapper.showbackup(map);
            result.setData(data);

        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int backupMember(String uid) {

        int i = 0;
        try {

            i=tbUserMapper.backupMember(uid);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteBye(String uid) {

        int i = 0;
        try {

            i=tbUserMapper.deleteByPrimaryKey(uid);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int backupuids(List<String> uids) {
        int i =0;
        try {
            TbUser tbUser = new TbUser();
            tbUser.setStatus(0);

            TbUserExample tbUserExample = new TbUserExample();
            TbUserExample.Criteria criteria = tbUserExample.createCriteria();
            criteria.andUidIn(uids);
            i=tbUserMapper.updateByExampleSelective(tbUser,tbUserExample);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i ;
    }

}
