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
            <div class="table-list">

                <table class="layui-table layui-list-table" lay-skin="line">
                    <thead>
                    <tr>
                        <th>图片ID</th>
                        <th>图片内容标题</th>
                        <th>标题描述</th>
                        <th>图片预览</th>
                        <th>图片更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach  items="${tbContent }" var="entry">
                            <tr>
                                <td><span class="layui-table-bd">${entry.id}</span></td>
                                <td><span class="layui-table-bd price">${entry.title}</span></td>
                                <td><span class="layui-table-bd price">${entry.titleDesc}</span></td>
                                <td class="layui-table-first"><img class="img-cp fl mr10" src="${entry.pic}" alt=""></td>
                                <td>${entry.updated}</td>
                                <td>
                                <a title="编辑" href="${pageContext.request.contextPath}/test/adver/editImagePro?id=${entry.id}">
                                    <i class="layui-icon">&#xe642;</i>
                                </a>


                                <a href="javascript:void(0)"  title="删除" onclick="deleteadver('${entry.id}','${entry.categoryId}')" >
                                    <i class="layui-icon">&#xe640;</i>
                                </a>
                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <table class="layui-table" id="orderItemList" ></table>
            </div>


        </div>
    </div>
</div>
</body>

<script type="text/javascript">

    function deleteadver(id,categoryId) {
        layui.use(['jquery', 'table'], function () {
            var layer =layui.layer,
                jquery =layui.jquery;
            layer.confirm("删除该图片？",function () {

                location.href="../../test/adver/deleteimage?id="+id+"&categoryId="+categoryId;
            });
        })
    }
</script>

</html>
