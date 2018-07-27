package com.legou.manager.service.impl;

import com.legou.manager.dao.OrderMapper;
import com.legou.manager.dao.TbOrderItemMapper;
import com.legou.manager.dao.TbOrderMapper;
import com.legou.manager.dao.TbOrderShippingMapper;
import com.legou.manager.pojo.dto.OrderQuery;
import com.legou.manager.pojo.dto.OrderResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbOrder;
import com.legou.manager.pojo.po.TbOrderExample;
import com.legou.manager.pojo.po.TbOrderItem;
import com.legou.manager.pojo.po.TbOrderShipping;
import com.legou.manager.pojo.vo.TbOrderCustom;
import com.legou.manager.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZH
 * @Date: 2018/7/22 22:51
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Override
    public OrderResult<TbOrderCustom> showAllOrder(PageParam pageParam,OrderQuery orderQuery) {
        OrderResult<TbOrderCustom> result = new OrderResult<>();
        result.setCode(0);
        result.setMsg("select success");
        try {
            /*TbOrderExample tbOrderExample = new TbOrderExample();
            TbOrderExample.Criteria criteria = tbOrderExample.createCriteria();
            criteria.andCreateTimeBetween(new Date(orderQuery.getStart()),new Date(orderQuery.getEnd()));
            criteria.andStatusEqualTo(orderQuery.getStatus());
            criteria.andOrderIdLike(orderQuery.getOrderId());*/

            long count = orderMapper.countOrder(orderQuery);
            result.setCount(count);
            List<TbOrderCustom> data = orderMapper.listOrdersBypage(pageParam,orderQuery);
            result.setData(data);
        }catch (Exception e){
            result.setCode(1);
            result.setMsg("select failed");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateOrdersByIds(List<String> ids) {
        int i = 0;
        try {
            TbOrder tbOrder = new TbOrder();
            tbOrder.setPaymentType(0);
            TbOrderExample example = new TbOrderExample();
            TbOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdIn(ids);
            i= tbOrderMapper.updateByExampleSelective(tbOrder, example);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateOrder(String orderId) {
        int i = 0;
        try {
            i = orderMapper.updateOrder(orderId);
            System.out.println("service" + i);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public TbOrderShipping deliveryBypage(String orderId) {
        TbOrderShipping result = null;
        try {
            result = tbOrderShippingMapper.selectByPrimaryKey(orderId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delivery(String orderId, String shippingCode, String shippingName) {
        int i = 0;
        try {
           i = orderMapper.updateDelivery(orderId,shippingCode,shippingName);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public TbOrder detailsOrder(String orderId) {
        TbOrder tbOrder = new TbOrder();
        try {
            tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbOrder;
    }

    @Override
    public TbOrderShipping detailsShipping(String orderId) {
        TbOrderShipping tbOrderShipping = new TbOrderShipping();
        try {
            tbOrderShipping = tbOrderShippingMapper.selectByPrimaryKey(orderId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbOrderShipping;
    }

    @Override
    public List<TbOrderItem> detailsOrderItems(String orderId) {
        List<TbOrderItem> tbOrderItems = new ArrayList<>();
        try {
            tbOrderItems = tbOrderItemMapper.listOrderItemsBypage(orderId);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return tbOrderItems;
    }
}
