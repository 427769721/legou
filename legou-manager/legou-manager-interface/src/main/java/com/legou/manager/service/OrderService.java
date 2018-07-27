package com.legou.manager.service;

import com.legou.manager.pojo.dto.OrderQuery;
import com.legou.manager.pojo.dto.OrderResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbOrder;
import com.legou.manager.pojo.po.TbOrderItem;
import com.legou.manager.pojo.po.TbOrderShipping;
import com.legou.manager.pojo.vo.TbOrderCustom;

import java.util.List;

/**
 * @Author: ZH
 * @Date: 2018/7/22 22:49
 * @Description:
 */
public interface OrderService {
    OrderResult<TbOrderCustom> showAllOrder(PageParam pageParam, OrderQuery orderQuery);


    int updateOrdersByIds(List<String> ids);

    int updateOrder(String orderId);

    TbOrderShipping deliveryBypage(String orderId);

    int delivery(String orderId, String shippingCode, String shippingName);

    TbOrder detailsOrder(String orderId);

    TbOrderShipping detailsShipping(String orderId);

    List<TbOrderItem> detailsOrderItems(String orderId);
}
