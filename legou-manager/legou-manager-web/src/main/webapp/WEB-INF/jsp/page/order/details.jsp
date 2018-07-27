<%--
  Created by IntelliJ IDEA.
  User: zhxhww
  Date: 2018/7/24
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表-后台管理系统-Admin 1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui2/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui2/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .layui-form-switch {
            width: 55px;
        }
        .layui-form-switch em {
            width: 40px;
        }
        .layui-form-onswitch i {
            left: 45px;
        }
        body{overflow-y: scroll;}
    </style>
</head>
<body>
<!-- 布局容器 -->
<div class="layui-layout layui-layout-admin">

    <!-- 头部 -->
    <div id="header">
        <header-component></header-component>
    </div>

    <!-- 遮罩层 -->
    <div class="masked"></div>

    <!-- 左侧菜单 -->
    <div id="leftSlide">
        <leftslide-component></leftslide-component>
    </div>

    <!-- 主体 -->
    <div class="lbody">
        <!-- 主要内容 -->
        <div class="">
            <fieldset class="layui-elem-field site-demo-button layui-field-res">
                <legend>订单信息</legend>
                <div class="layui-field-box">
                    <table class="layui-table" lay-skin="nob">
                        <tbody>
                        <tr>
                            <td>订单编号: <span class="layui-field-bd">${tbOrder.orderId}</span></td>
                        </tr>
                        <tr>
                            <td>总金额: <span class="layui-field-bd price">${tbOrder.payment}</span></td>
                        </tr>
                        <tr>
                            <td>下单时间: <span class="layui-field-bd red">${tbOrder.createTime}</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </fieldset>

            <fieldset class="layui-elem-field site-demo-button layui-field-res">
                <legend>收货地址</legend>
                <div class="layui-field-box">
                    <table class="layui-table" lay-skin="nob">
                        <tbody>
                        <tr>
                            <td>收货人: <span class="layui-field-bd">${tbOrderShipping.receiverName}</span></td>
                        </tr>
                        <tr>
                            <td>手机号码: <span class="layui-field-bd">${tbOrderShipping.receiverMobile}</span></td>
                        </tr>
                        <tr>
                            <td>收货地址: <span class="layui-field-bd">${tbOrderShipping.receiverAddress}</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </fieldset>

            <div class="table-list">
                <table class="layui-table layui-list-table" lay-skin="line">
                    <thead>
                    <tr>
                        <th>商品ID</th>
                        <th>商品名称</th>
                        <th>商品数量</th>
                        <th>商品价格</th>
                        <th>成交金额</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach  items="${tbOrderItems }" var="entry">
                            <tr>
                                <td><span class="layui-table-bd">${entry.itemId}</span></td>
                                <td class="layui-table-first"><span class="layui-table-bd"><img class="img-cp fl mr10" src="${entry.picPath}" alt="">${entry.title}</span></td>
                                <td><span class="layui-table-bd price">${entry.num}</span></td>
                                <td><span class="layui-table-bd price">${entry.price}</span></td>
                                <td><span class="layui-table-bd price">${entry.totalFee}</span></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <table class="layui-table" id="orderItemList" ></table>
            </div>

            <fieldset class="layui-elem-field site-demo-button layui-field-res">
                <legend>快递信息</legend>
                <div class="layui-field-box">
                    <table class="layui-table" lay-skin="nob">
                        <tbody>
                        <tr>
                            <td>快递公司: <span class="layui-field-bd">${tbOrder.shippingName}</span></td>
                        </tr>
                        <tr>
                            <td>快递单号: <span class="layui-field-bd">${tbOrder.shippingCode}</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </fieldset>

        </div>
    </div>
</div>
</body>
</html>
