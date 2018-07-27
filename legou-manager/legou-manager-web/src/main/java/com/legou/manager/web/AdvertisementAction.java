package com.legou.manager.web;

import com.legou.manager.pojo.dto.AdvertisementQuery;
import com.legou.manager.pojo.dto.AdvertisementResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbContent;
import com.legou.manager.pojo.po.TbContentCategory;
import com.legou.manager.service.AdvertisementService;
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
 * Date: 2018/7/26 Time: 20:00
 * Version:V1.0
 */

@Controller
public class AdvertisementAction {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/test/adver/show")
    @ResponseBody
    public  AdvertisementResult<TbContentCategory> show(PageParam pageParam, AdvertisementQuery advertisementQuery){

        AdvertisementResult<TbContentCategory> result = null;
        try {
            result = advertisementService.show(pageParam,advertisementQuery);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(result);
        return result;
    }


    @RequestMapping("/test/adver/delete")
    public String delete(String id){
        int i =0;
        try {
            i = advertisementService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(i);
        return "page/member/list";
    }

    /*
        多选删除
     */
    @RequestMapping("/adver/batch")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("ids[]") List<Long> ids){
        Map<String,Object> map = new HashMap<>();

        int i =0;
        try {
            i =advertisementService.deleteCheck(ids);
        }catch (Exception e){

        }

        if(i>0){

            map.put("data",1);
        }else{

            map.put("data",0);
        }
        return map;

    }



    @RequestMapping("/test/adver/adverDetails")
    public String adverDetails(String id, Model model){
        System.out.println(id);
        List<TbContent> result = null;
        try {
            result = advertisementService.showDeatails(id);


        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbContent",result);
        System.out.println(result);
        return "page/advertisement/details";
    }


    @RequestMapping("/test/adver/deleteimage")
    public String deleteimage(String id,String categoryId,Model model){
        int i =0;
        try {
            i = advertisementService.deleteimage(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<TbContent> result = null;
        try {
            result = advertisementService.showDeatails(categoryId);


        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbContent",result);
        System.out.println(result);
        return "page/advertisement/details";

    }

    @RequestMapping("/test/adver/editImagePro")
    public String editImagePro(String id, Model model){
        System.out.println(id);
        TbContent  result = null;
        try {
            result = advertisementService.editImagePro(id);


        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbContent",result);
        System.out.println(result);
        return "page/advertisement/edit";
    }


    @RequestMapping("/test/adver/edit")
    public String edit(TbContent tbContent,Model model){

        int i =0;
        System.out.println(tbContent);
        try {
            i = advertisementService.edit(tbContent);


        }catch (Exception e){
            e.printStackTrace();
        }


        List<TbContent> result = null;
        try {
            result = advertisementService.showDeatails(tbContent.getCategoryId().toString());


        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbContent",result);
        System.out.println(result);
        return "page/advertisement/details";
    }


}
