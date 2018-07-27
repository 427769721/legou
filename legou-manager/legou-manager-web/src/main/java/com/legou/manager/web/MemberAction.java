package com.legou.manager.web;

import com.legou.manager.pojo.dto.MemberQuery;
import com.legou.manager.pojo.dto.MemberResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbUser;
import com.legou.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: xwh
 * Date: 2018/7/21 Time: 14:19
 * Version:V1.0
 */
@Controller
public class MemberAction {

    @Autowired
    private MemberService memberService;

    /*
        查询 非0 的会员
     */
    @RequestMapping("/test/member/show")
    @ResponseBody
    public MemberResult<TbUser> memberList(PageParam pageParam, MemberQuery memberQuery){

        MemberResult<TbUser> result = null;
        try {
            result = memberService.memberList(pageParam, memberQuery);
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println(result);
        return  result;
    }


    /*
            会员  编辑的提前加载信息
     */

    @RequestMapping("/test/member/editPro")
    public String editPro(String uid, Model model){
        TbUser tbUser =null;
        try {
            tbUser = memberService.editPro(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("tbUser",tbUser);
        System.out.println(tbUser);
        return "page/member/edit";
    }


    /*
            会员  编辑的提交
     */

    @RequestMapping("/test/member/edit")
    public String edit(TbUser tbUser){
        int i =0;
        try {
            i = memberService.edit(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(tbUser);
        return "page/member/list";
    }


    /*
            会员  新增的提交
     */

    @RequestMapping("/test/member/add")
    public String add(TbUser tbUser){
        System.out.println(tbUser);
        int i =0;
        try {
            i = memberService.add(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "page/member/list";
    }


      /*
            会员  单点删除
     */

    @RequestMapping("/test/member/delete")
    public String delete(String uid){
        int i =0;
        try {
            i = memberService.delete(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(i);
        return "page/member/list";
    }

    /*
        多选删除
     */
    @RequestMapping("/test/member/deletechance")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("uids[]") List<String> uids){
        Map<String,Object> map = new HashMap<>();
        System.out.println(uids);

        int i =0;
        try {
            i =memberService.deleteCheck(uids);
        }catch (Exception e){

        }

        if(i>0){

            map.put("data",1);
        }else{

            map.put("data",0);
        }

        return map;


    }



      /*
            切换 会员状态
     */

    @RequestMapping("/test/member/updateMemberStatus")
    @ResponseBody
    public Map<String,Object> updateMemberStatus (Integer memberstatus,String uid){
        Map<String,Object> map = new HashMap<>();

        int i = memberService.updateMemberStatus(memberstatus,uid);

        map.put("data","1");

        return map;
    }


    /*
    查询  已经被删除的 的会员
 */
    @RequestMapping("/test/member/showbackup")
    @ResponseBody
    public MemberResult<TbUser> memberList(PageParam pageParam ){

        MemberResult<TbUser> result = null;
        try {
            result = memberService.showbackup(pageParam);
        }catch (Exception e){
            e.printStackTrace();
        }


        System.out.println(result);
        return  result;
    }


    /*
            会员  点击  恢复 即是 把 会员的status 恢复，与
     */

    @RequestMapping("/test/member/backup")
    public String backup (String uid){
        int i =0;
        try {
           i = memberService.backupMember(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/member/backuplist";
    }
/*
            会员  彻底删除
     */

    @RequestMapping("/test/member/deleteBye")
    public String deleteBye (String uid){
        int i =0;
        try {
            i = memberService.deleteBye(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "page/member/backuplist";
    }




    @RequestMapping("/test/member/backupuids")
    @ResponseBody
    public Map<String,Object> backupuids(@RequestParam("uids[]") List<String> uids){
        Map<String,Object> map = new HashMap<>();
        int i =0;
        try {
            i =memberService.backupuids(uids);
        }catch (Exception e){

        }

        if(i>0){

            map.put("data",1);
        }else{

            map.put("data",0);
        }

        return map;


    }






}
