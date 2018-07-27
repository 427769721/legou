<%--
  Created by IntelliJ IDEA.
  User: zhxhww
  Date: 2018/7/20
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>消息列表--layui后台管理模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font_eolqem241z66flxr.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/message.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote news_search">
        <div class="layui-inline selectMsg">
            <select name="msgColl" lay-filter="selectMsg">
                <option value="0">全部</option>
                <option value="1">已收藏</option>
            </select>
        </div>
        <div class="layui-inline">
            <div class="layui-form-mid layui-word-aux">本页所有数据均为静态，刷新后所有操作无效</div>
        </div>
    </blockquote>
    <table class="layui-table msg_box" lay-skin="line">
        <colgroup>
            <col width="45%">
            <col width="25%">
            <col width="15%">
            <col>
        </colgroup>
        <tbody class="msgHtml"></tbody>
    </table>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/webjs/message.js"></script>
</body>
</html>