<%--
  User: Administrator
  Date: 2018/7/23
  Time: 11:08
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员列表-后台管理系统-Admin 1.0</title>
    <meta name="Description" content="基于layUI数据表格操作"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui2/css/layui.css">
    <%--<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui2/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/webjs/backuplist.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js" charset="utf-8"></script>

    <!--<script type="text/javascript" src="../../static/js/admin.js"></script>-->
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
<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">会员管理</a>
        <a>
          <cite>备份会员列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">




    </div>
    <div class="weadmin-block demoTable">

        <button class="layui-btn layui-btn-normal" data-type="getCheckData"><i class="layui-icon">&#xe640;</i>批量恢复</button>
    </div>

    <table class="layui-hide" id="articleList" ></table>



    <script type="text/html" id="myTpl">
        <div class="layui-form">
            <button class="layui-btn layui-btn-danger"   >被删除</button>
        </div>
    </script>


    <script type="text/html" id="operateTpl">

        <a   href="javascript:void(0)"  title="恢复" onclick="backup('{{d.uid}}')" >
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a   href="javascript:void(0)"  title="彻底删除" onclick="deletebackup('{{d.uid}}')" >
            <i class="layui-icon">&#xe640;</i>
        </a>
    </script>
    <%--<script type="text/html" id="shelfTpl">--%>
    <%--<form class="layui-form">--%>
    <%--<input type="checkbox" name="itemstatus" lay-filter="itemstatus" lay-skin="switch" lay-text="正常|下架" {{1==d.status?'checked':''}}/>--%>
    <%--</form>--%>
    <%--</script>--%>


    <script type="text/javascript">


        layui.use(['form', 'jquery','layer'], function() {
            var form = layui.form,
                $ = layui.jquery,
                admin = layui.admin,
                layer = layui.layer;

        });
    </script>


</div>



</body>

</html>