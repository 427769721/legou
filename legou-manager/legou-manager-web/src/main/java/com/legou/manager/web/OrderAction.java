package com.legou.manager.web;

import com.legou.manager.pojo.dto.OrderQuery;
import com.legou.manager.pojo.dto.OrderResult;
import com.legou.manager.pojo.dto.PageParam;
import com.legou.manager.pojo.po.TbOrder;
import com.legou.manager.pojo.po.TbOrderItem;
import com.legou.manager.pojo.po.TbOrderShipping;
import com.legou.manager.pojo.vo.TbOrderCustom;
import com.legou.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZH
 * @Date: 2018/7/22 22:41
 * @Description:
 */
@Controller
public class OrderAction {
    @Autowired
    private OrderService orderService;

    /**
     * 显示所有订单
     * @param pageParam
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showAllOrder")
    public OrderResult<TbOrderCustom> showAllOrder(PageParam pageParam,OrderQuery orderQuery){
        OrderResult<TbOrderCustom> result = null;
        try {
            result = orderService.showAllOrder(pageParam,orderQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/order/batch")
    @ResponseBody
    public int removeOrders(@RequestParam(value = "ids[]")List<String> ids){
        int i = 0;
        try {
            i = orderService.updateOrdersByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    /**
     * /order/deleteOrder
     * 删除
     */
    @RequestMapping(value = "/order/order1/deleteOrder")
    public String removeOrder(String orderId){
        int i = 0;
        try {
            System.out.println("orderId:"+orderId);
            i = orderService.updateOrder(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/order/list";
    }

    /**
     *
     * 发货显示
     */
    @RequestMapping(value = "/order/order1/deliveryBypage")
    public String deliveryBypage(String orderId, Model model){
        TbOrderShipping result = null;
        try {
            result = orderService.deliveryBypage(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbOrderShipping",result);
        return "page/order/delivery";
    }

    /**
     *
     * 发货
     */
    @RequestMapping(value = "/order/order1/delivery")
    public String delivery(String orderId, String shippingCode, String shippingName){
        System.out.println(orderId);
        System.out.println(shippingName);
        System.out.println(shippingCode);
        int i = 0;
        try {
            i = orderService.delivery(orderId,shippingCode,shippingName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "page/order/list";
    }

    /**
     *
     * 订单详情
     */
    @RequestMapping(value = "/order/order1/details")
    public String details(String orderId, Model model){
        TbOrder tbOrder = new TbOrder();
        TbOrderShipping tbOrderShipping = new TbOrderShipping();
        List<TbOrderItem> tbOrderItems = new ArrayList<>();
        try {
            tbOrder = orderService.detailsOrder(orderId);
            tbOrderShipping = orderService.detailsShipping(orderId);
            tbOrderItems = orderService.detailsOrderItems(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("tbOrder",tbOrder);
        model.addAttribute("tbOrderShipping",tbOrderShipping);
        model.addAttribute("tbOrderItems",tbOrderItems);
        return "page/order/details";
    }

    /**
     *
     * 商品订单详情
     */
    @RequestMapping(value = "/order/detailsOrderItems")
    public List<TbOrderItem> detailsOrderItems(String orderId, Model model){
        List<TbOrderItem> tbOrderItems = new ArrayList<>();
        try {
            tbOrderItems = orderService.detailsOrderItems(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return tbOrderItems;
    }
}
