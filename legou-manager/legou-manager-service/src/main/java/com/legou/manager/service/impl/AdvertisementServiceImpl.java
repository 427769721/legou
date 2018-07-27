package com.legou.manager.service.impl;

import com.legou.manager.dao.TbContentCategoryMapper;
import com.legou.manager.dao.TbContentMapper;
import com.legou.manager.pojo.dto.AdvertisementQuery;
import com.legou.manager.pojo.dto.AdvertisementResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.*;
import com.legou.manager.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * User: xwh
 * Date: 2018/7/26 Time: 20:03
 * Version:V1.0
 */

@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService{


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public AdvertisementResult<TbContentCategory> show(PageParam pageParam, AdvertisementQuery advertisementQuery) {
        AdvertisementResult<TbContentCategory> result =new AdvertisementResult<>();
        result.setCode(0);
        result.setMsg("恭喜！");
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("pageParam",pageParam);
            map.put("advertisementQuery", advertisementQuery);

            Long count = tbContentCategoryMapper.countAdver(map);

            List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.showAdver(map);

            result.setCount(count);
            result.setData(tbContentCategories);

        }catch (Exception e){
            result.setCode(-1);
            result.setMsg("failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(String id) {
        int i = 0;
        try {
            TbContentCategory tbContentCategory = new TbContentCategory();
            tbContentCategory.setStatus(1);

            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(Long.parseLong(id));

            i= tbContentCategoryMapper.updateByExampleSelective(tbContentCategory,example);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;

    }

    @Override
    public int deleteCheck(List<Long> ids) {


        int i =0;
        try {
            TbContentCategory tbContentCategory = new TbContentCategory();
            tbContentCategory.setStatus(1);

            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            i =tbContentCategoryMapper.updateByExampleSelective(tbContentCategory,example);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i ;
    }

    @Override
    public List<TbContent> showDeatails(String id) {
        List<TbContent> tbContent = new ArrayList<>();
        try {
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(Long.parseLong(id));
            tbContent = tbContentMapper.selectByExample(example);

        }catch (Exception e){

            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }

        return tbContent;
    }

    @Override
    public int deleteimage(String id) {
        int i =0;
        try {

           TbContentExample tbContentExample = new TbContentExample();
           TbContentExample.Criteria criteria = tbContentExample.createCriteria();
           criteria.andIdEqualTo(Long.parseLong(id));
           i = tbContentMapper.deleteByExample(tbContentExample);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i ;

    }

    @Override
    public TbContent editImagePro(String id) {
        TbContent tbContent = new TbContent();

        try {
            tbContent= tbContentMapper.selectByPrimaryKey(Long.parseLong(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbContent;
    }

    @Override
    public int edit(TbContent tbContent) {

        tbContent.setUpdated(new Date());
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andIdEqualTo(tbContent.getId());

        int i = tbContentMapper.updateByExampleSelective(tbContent, tbContentExample);

        return i;
    }
}
