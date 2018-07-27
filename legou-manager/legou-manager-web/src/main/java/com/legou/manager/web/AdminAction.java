package com.legou.manager.web;

import com.legou.manager.pojo.dto.AdminQuery;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.dto.UserResult;
import com.legou.manager.pojo.po.TbAdmin;
import com.legou.manager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminAction {

    @Autowired
    private AdminService adminService;


    /*
        查询  展示所有
     */
    @RequestMapping("/test/admins/show")
    @ResponseBody
    public UserResult<TbAdmin> showAdmins(PageParam pageParam, AdminQuery adminQuery){
        System.out.println("aname:"+adminQuery.getAname());

        UserResult<TbAdmin> result = null;
        try {
            result = adminService.showAdmins(pageParam, adminQuery);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(result);
        return  result;
    }


    /*
            用户  编辑的提前加载信息
     */
    @RequestMapping("/test/admin/editAdmin")
    public String showAdmin(String aid, Model model){
        TbAdmin tbAdmin =null;
        try {
            tbAdmin = adminService.showAdmin(aid);
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("tbAdmin",tbAdmin);
        System.out.println(tbAdmin);
        
        return "page/admin/edit";
    }


    /*
            用户  编辑的提交
     */
    @RequestMapping("/test/admin/edit")
    public String updateAdmin(TbAdmin tbAdmin){
        int i =0;
        try {
            i = adminService.updateAdmin(tbAdmin);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(tbAdmin);
        return "page/admin/list";
    }



    /*
          用户  单点删除
   */
    @RequestMapping("/test/admin/delete")
    public String removeAdmin(String aid){
        int i =0;
        try {
            i = adminService.removeAdmin(aid);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(i);
        return "page/admin/list";
    }

    /*
        多选删除
     */
    @RequestMapping("/test/admins/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("aids[]") List<String> aids){
        Map<String,Object> map = new HashMap<>();
        System.out.println(aids);

        int i =0;
        try {
            i = adminService.updateAdminsByIds(aids);
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
    @RequestMapping("/test/admin/add")
    public String add(TbAdmin tbAdmin){
        System.out.println(tbAdmin);
        int i =0;
        try {
            i = adminService.addAdmin(tbAdmin);
        }catch (Exception e){
            e.printStackTrace();
        }
        

        return "page/admin/list";
    }

    /*
           登陆
     */
    @RequestMapping("/login")
    public String login(String aname, String apwd){
       
        TbAdmin tbAdmin = null;
        try {
            tbAdmin = adminService.selectAdmin(aname, apwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(tbAdmin!=null){
            return "index";
        }

        return "login";
    }
    
    
    //todo
    @RequestMapping("/test/admin/updateApwd")
    public String updateApwd(String aid, String apwd){

        int i = 0;
        try {
            i = adminService.updateAdminByApwd(aid, apwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        
        if(i>0){
            return "login";
        }

        return "page/admin/list";
    }
    
}
