<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:formatDate value="${inputTime}" pattern="yyyy年mm月dd日 hh:mm:ss"/>
<!DOCTYPE html>
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
<%--<div class="weadmin-nav">
    <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">订单管理</a>
        <a><cite>订单列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>--%>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search" onsubmit="return false;" id="formDemo">

            <div class="layui-inline">
                <input type="text" name="name" id="name" placeholder="广告模块名" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="sreach" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </form>
    </div>
    <div class="weadmin-block demoTable">
        <button class="layui-btn layui-btn-danger" data-type="getCheckData"><i class="layui-icon"></i>批量删除</button>
        <%--<button class="layui-btn" onclick="WeAdminShow('添加订单','./add')"><i class="layui-icon"></i>添加</button>--%>
    </div>
    <table class="layui-table" id="advertable" ></table>


</div>
<script>

    layui.use(['form','laydate','jquery'], function() {
        var laydate = layui.laydate,
            $ = layui.jquery;
        form.on('submit(formDemo)',function (data) {
            return true;
        });
    });

</script>

<script type="text/javascript">
    layui.extend({
        admin: '{/}../../static/js/admin'
    });
    layui.use(['admin','table','jquery','layer'],function () {
        var table = layui.table,
            $ = layui.jquery,
            layer = layui.layer,
            admin = layui.admin;
        table.render({
            elem:'#advertable',
            page:true,
            url:'../../test/adver/show',
            cols:[[
                {type:'checkbox'},
                {field:'id',title:'广告分类编号',sort:true,width:150},
                {field:'name',title:'广告模块名',width:150},
                {field:'created',title:'创建时间',width:150},
                {field:'updated',title:'更新时间',width:150},
                {field: 'id', title: '操作', templet: '#operateTpl',width:224}

            ]],

        });

        var active = {
            getCheckData:function () {
                var data = table.checkStatus("advertable").data;
                if (data.length > 0) {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    $.post(
                        '../../adver/batch',
                        {'ids[]': ids},
                        function (data) {
                            if (data.data > 0) {
                                $('.layui-laypage-btn').click();
                                layer.msg("删除成功！", {icon: 1});
                            }
                        }
                    );
                } else {
                    layer.msg("请选择至少一行后再批量删除！", {icon: 2});
                }
            },
            reload:function () {
                var name = $("#name").val();
                if($.trim(name).length >= 0 ){
                    table.reload("advertable",{
                        page:{curr:1},
                        where:{name:name}
                    });
                }
            }

        }

        //批量删除点击事件
        $(".demoTable .layui-btn-danger").click(function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('.weadmin-body .layui-btn').click(function () {
            //alert(123);
            var type = $(this).data("type");//reload
            active[type] ? active[type].call(this) : '';
        });

    });


    function deleteAdver(id) {
        layui.use(['jquery', 'table'], function () {
            alert("shanchu"+id);
            var layer =layui.layer,
                jquery =layui.jquery;
            layer.confirm("删除该栏目？",function () {

                location.href="../../test/adver/delete?id="+id;
            });
        })
    }


</script>

<script type="text/html" id="operateTpl">

    <a href="${pageContext.request.contextPath}/test/adver/adverDetails?id={{d.id}}" class="layui-btn layui-btn-sm layui-btn-warm">
        查看详情</a>

    <a   href="javascript:void(0)"  title="删除该会员" onclick="deleteAdver('{{d.id}}')" >
        <i class="layui-icon">&#xe640;</i>
    </a>
</script>

</body>

</html>