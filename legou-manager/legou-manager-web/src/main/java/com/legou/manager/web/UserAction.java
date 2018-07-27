package com.legou.manager.web;

import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserQuery;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbUser;
import com.legou.manager.service.UserService;
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
public class UserAction {

    @Autowired
    private UserService userService;

   
    /*
        查询  展示所有
     */
    @RequestMapping("/test/user/show")
    @ResponseBody
    public UserResult<TbUser> searchUsers(PageParam pageParam, UserQuery userQuery){
        System.out.println("uname:"+userQuery.getUname());
        
        UserResult<TbUser> result = null;
        try {
            result = userService.searchUsers(pageParam, userQuery);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(result);
        return  result;
    }


    /*
            用户  编辑的提前加载信息
     */
    @RequestMapping("/test/user/editUser")
    public String showUser(String uid, Model model){
        TbUser tbUser =null;
        try {
            tbUser = userService.showUser(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("tbUser",tbUser);
        System.out.println(tbUser);
        return "page/user/edit";
    }


    /*
            用户  编辑的提交
     */
    @RequestMapping("/test/user/edit")
    public String updateUser(TbUser tbUser){
        int i =0;
        try {
            i = userService.updateUser(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(tbUser);
        return "page/user/list";
    }
    


      /*
            用户  单点删除
     */
    @RequestMapping("/test/user/delete")
    public String removeUser(String uid){
        int i =0;
        try {
            i = userService.removeUser(uid);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(i);
        return "page/user/list";
    }

    /*
        多选删除
     */
    @RequestMapping("/test/users/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("uids[]") List<String> uids){
        Map<String,Object> map = new HashMap<>();
        System.out.println(uids);

        int i =0;
        try {
            i =userService.updateUsersByIds(uids);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(i>0){

            map.put("data",1);
        }else{

            map.put("data",0);
        }

        return map;

    }

    /*
            用户  新增的提交
     */
    @RequestMapping("/test/user/add")
    public String add(TbUser tbUser){
        System.out.println(tbUser);
        int i =0;
        try {
            i = userService.addUser(tbUser);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "page/user/list";
    }



}
