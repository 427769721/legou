package com.legou.manager.dao;

import com.legou.manager.pojo.dto.OrderQuery;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.vo.TbOrderCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ZH
 * @Date: 2018/7/22 22:56
 * @Description:
 */
public interface OrderMapper {

    long countOrder(@Param("orderQuery") OrderQuery orderQuery);

    List<TbOrderCustom> listOrdersBypage(@Param("pageParam") PageParam pageParam, @Param("orderQuery") OrderQuery orderQuery);

    int updateOrder(String orderId);

    int updateDelivery(@Param("orderId") String orderId, @Param("shippingCode") String shippingCode, @Param("shippingName") String shippingName);
}
