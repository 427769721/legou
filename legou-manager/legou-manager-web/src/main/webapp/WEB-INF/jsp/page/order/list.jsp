<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <input class="layui-input" placeholder="开始日" name="start" id="start" autocomplete="off">
            </div>
            <div class="layui-inline">
                <input class="layui-input" placeholder="截止日" name="end" id="end" autocomplete="off">
            </div>
            <div class="layui-input-inline">
                <select name="status" id="status">
                    <option value="0">支付状态</option>
                    <option value="1">未支付</option>
                    <option value="2">已支付</option>
                    <option value="3">未发货</option>
                    <option value="4">已发货</option>
                </select>
            </div>
            <div class="layui-inline">
                <input type="text" name="orderId" id="orderId" placeholder="请输入订单号" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="sreach" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </form>
    </div>
    <div class="weadmin-block demoTable">
        <button class="layui-btn layui-btn-danger" data-type="getCheckData"><i class="layui-icon"></i>批量删除</button>
        <%--<button class="layui-btn" onclick="WeAdminShow('添加订单','./add')"><i class="layui-icon"></i>添加</button>--%>
    </div>
    <table class="layui-table" id="orderList" ></table>
    <%--<table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>订单编号</th>
            <th>收货人</th>
            <th>总金额</th>
            <th>应付金额</th>
            <th>订单状态</th>
            <th>支付状态</th>
            <th>发货状态</th>
            <th>支付方式</th>
            <th>配送方式</th>
            <th>下单时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>2017009171822298053</td>
            <td>老王:18925139194</td>
            <td>7829.10</td>
            <td>7854.10</td>
            <td>待确认</td>
            <td>未支付</td>
            <td>未发货</td>
            <td>其他方式</td>
            <td>申通物流</td>
            <td>2017-08-17 18:22</td>
            <td class="td-manage">
                <a title="查看" onclick="WeAdminShow('编辑','order-view.html')" href="javascript:;">
                    <i class="layui-icon">&#xe63c;</i>
                </a>
                <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                    <i class="layui-icon">&#xe640;</i>
                </a>
            </td>
        </tr>
        </tbody>
    </table>--%>

</div>
<script>
   /* layui.extend({
        admin: '{/}../../static/js/admin'
    });*/
    layui.use(['form','laydate','jquery'], function() {
        var laydate = layui.laydate,
            $ = layui.jquery;
        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });
        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
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
            elem:'#orderList',
            page:true,
            url:'../../showAllOrder',
            cols:[[
                {type:'checkbox'},
                {field:'orderId',title:'订单编号',sort:true,width:150},
                {field:'uname',title:'用户名',width:150},
                {field:'payment',title:'总金额',width:150,sort: true},
                {field:'status',title:'订单状态',width:150},
                /*{field:'paymentType',title:'发货状态'},*/
                {field:'createTime',title:'下单时间',width:150},
                {field: 'orderId',field: 'status', title: '操作', templet: '#operateTpl',width:224}

            ]],
            done:function (res,curr,count) {
                console.log($("[data-field='status']").children());
                $("[data-field='status']").children().each(function () {
                    if ($(this).text()=="1"){
                        $(this).text('未付款');
                    }
                    if ($(this).text()=="2"){
                        $(this).text('已付款');
                    }
                    if ($(this).text()=="3"){
                        $(this).text('未发货');
                    }
                    if ($(this).text()=="4"){
                        $(this).text('已发货');
                    }
                });
            }
        });

        var active = {
            getCheckData:function () {
                var data = table.checkStatus("orderList").data;
                if (data.length > 0) {
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].orderId);
                    }
                    $.post(
                        '../../order/batch',
                        {'ids[]': ids},
                        function (data) {
                            if (data > 0) {
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
                var start = $("#start").val();
                var end = $("#end").val();
                var status = $("#status").val();
                var orderId = $("#orderId").val();
                if($.trim(start).length >= 0 ){
                    table.reload("orderList",{
                        page:{curr:1},
                        where:{start:start}
                    });
                }
                if($.trim(end).length >= 0 ){
                    table.reload("orderList",{
                        page:{curr:1},
                        where:{end:end}
                    });
                }
                if($.trim(status).length >= 0 ){
                    table.reload("orderList",{
                        page:{curr:1},
                        where:{status:status}
                    });
                }
                if($.trim(orderId).length >= 0 ){
                    table.reload("orderList",{
                        page:{curr:1},
                        where:{orderId:orderId}
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
    function delivery(status,orderId) {
        if(status==1){
            alert("该订单未付款");
        }
        if(status==2 || status==3){
           window.location = "${pageContext.request.contextPath}/order/order1/deliveryBypage?orderId=" + orderId;
        }
        if(status==4){
            alert("该订单已发货");
        }
    }

</script>

<script type="text/html" id="operateTpl">
    <a class="layui-btn layui-btn-sm layui-btn-normal" id="delivery" href="javascript:void(0)" onclick="delivery('{{d.status}}','{{d.orderId}}')">
        发货</a>
    <a href="${pageContext.request.contextPath}/order/order1/details?orderId={{d.orderId}}" class="layui-btn layui-btn-sm layui-btn-warm">
        查看详情</a>
    <a href="${pageContext.request.contextPath}/order/order1/deleteOrder?orderId={{d.orderId}}" class="layui-btn layui-btn-sm layui-btn-danger">
        删除</a>
</script>

</body>

</html>