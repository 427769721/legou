package com.legou.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * User: xwh
 * Date: 2018/7/27 Time: 16:52
 * Version:V1.0
 */
@Controller
@MultipartConfig
public class FileUploadAction {
    @RequestMapping(value = "/test/upload/img")
    @ResponseBody
    public Map<String, Object>  upload(HttpServletRequest servletRequest,
                                       @RequestParam("file") MultipartFile file
    ) throws IOException {

        String path = servletRequest.getServletContext().getRealPath("/static/upload");
        //System.out.println("文件名称"+file.getOriginalFilename());
        //上传文件名
        String filename = UUID.randomUUID()+file.getOriginalFilename();
        File filepath = new File(path, filename);

        //判断路径是否存在，没有就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        //将上传文件保存到一个目标文档中
        File file1 = new File(path + File.separator + filename);
        file.transferTo(file1);
        Map<String, Object> res = new HashMap<>();
        //返回的是一个url对象
        res.put("url", "picture/"+filename);
        return res;
    }
}
