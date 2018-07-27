package com.legou.manager.service;

import com.legou.manager.pojo.dto.AdvertisementQuery;
import com.legou.manager.pojo.dto.AdvertisementResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbContent;
import com.legou.manager.pojo.po.TbContentCategory;

import java.util.List;

/**
 * User: xwh
 * Date: 2018/7/26 Time: 20:02
 * Version:V1.0
 */
public interface AdvertisementService {
    AdvertisementResult<TbContentCategory> show(PageParam pageParam, AdvertisementQuery advertisementQuery);

    int delete(String id);

    int deleteCheck(List<Long> ids);


    /*
        获取栏目的 详细资源
     */
    List<TbContent> showDeatails(String id);

    int deleteimage(String id);

    TbContent editImagePro(String id);

    int edit(TbContent tbContent);
}
