<%--
  Created by IntelliJ IDEA.
  User: zhxhww
  Date: 2018/7/24
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>发货-后台管理系统-Admin 1.0</title>
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
        .title{
            font-size: 30px;
            font-family: 新宋体;
        }
    </style>
</head>
<body>
    <div class="layui-layout layui-layout-admin">
        <div class="title">发货操作</div>
        <form class="layui-form" action="${pageContext.request.contextPath}/order/order1/delivery?orderId=${tbOrderShipping.orderId}" method="post">
            <table class="layui-table layui-table-content" lay-skin="nob">
                <tbody>
                <tr class="box">
                    <th class="pad" width="150">订单编号</th>
                    <td>${tbOrderShipping.orderId}</td>
                </tr>
                <tr class="box">
                    <th class="pad">收货人</th>
                    <td>${tbOrderShipping.receiverName}</td>
                </tr>
                <tr class="box">
                    <th class="pad">手机号码</th>
                    <td>${tbOrderShipping.receiverMobile}</td>
                </tr>
                <tr class="box">
                    <th class="pad">收货地址</th>
                    <td>${tbOrderShipping.receiverAddress}</td>
                </tr>
                <tr>
                    <th><span class="red">*</span>快递单号</th>
                    <td>
                        <input type="text" name="shippingCode" id="shippingCode" placeholder="请输入快递单号" autocomplete="off" class="layui-input">
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>快递公司</th>
                    <td>
                        <input type="text" name="shippingName" id="shippingName" placeholder="请输入快递公司" autocomplete="off" class="layui-input">
                    </td>
                </tr>
                </tbody>
            </table>

            <input type="submit" value="发 货" class="layui-btn" style=" min-width: 100px;margin-left: 20px;">

        </form>
    </div>
</body>
</html>
