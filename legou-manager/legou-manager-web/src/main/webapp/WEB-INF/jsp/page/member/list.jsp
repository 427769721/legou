<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/webjs/memberlist.js" charset="utf-8"></script>
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
          <cite>会员列表</cite>
        </a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">

        <form id="formDemo" class="layui-form layui-col-md12 we-search" onsubmit="return false;">
            会员快速搜索：
            <div class="layui-inline">
                <input type="text" id="uname" name="uname" placeholder="会员关键字" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search" data-type="reload"><i class="layui-icon">&#xe615;</i></button>
        </form>



    </div>
    <div class="weadmin-block demoTable">
        <button class="layui-btn layui-btn-normal" data-type="getCheckData"><i class="layui-icon">&#xe640;</i>批量删除</button>
       <%-- <button class="layui-btn" onclick="WeAdminShow('添加会员','./add',600,500)"><i class="layui-icon">&#xe61f;</i>添加</button>--%>
    </div>

    <table class="layui-hide" id="articleList" ></table>


    <script type="text/html" id="operateTpl">

        <a title="编辑" href="${pageContext.request.contextPath}/test/member/editPro?uid={{d.uid}}">
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a   href="javascript:void(0)"  title="删除该会员" onclick="deletemember('{{d.uid}}')" >
        <%--<a title="删除该会员" href="${pageContext.request.contextPath}/test/member/delete?uid={{d.uid}}">--%>
            <i class="layui-icon">&#xe640;</i>
        </a>
    </script>
    <%--<script type="text/html" id="shelfTpl">--%>
        <%--<form class="layui-form">--%>
            <%--<input type="checkbox" name="itemstatus" lay-filter="itemstatus" lay-skin="switch" lay-text="正常|下架" {{1==d.status?'checked':''}}/>--%>
        <%--</form>--%>
    <%--</script>--%>
    <script type="text/html" id="myTpl">
        <div class="layui-form">
            <%--改变会员的状态 需要传 uid  和 1或者 0--%>
            <input type="checkbox" name="memberstatus" value="{{d.uid}}" id="memberstatus" lay-filter="ifKeyDemo"  lay-skin="switch" layer-data="{{d.memberstatus}}" lay-text="正常使用|已失效" {{0==d.memberstatus?'checked':''}}/>
        </div>
    </script>


    <script type="text/javascript">


        layui.use(['form', 'jquery','layer'], function() {
            var form = layui.form,
                $ = layui.jquery,
                admin = layui.admin,
                layer = layui.layer;


            form.on('submit(formDemo)', function(data){
               /* layer.msg(JSON.stringify(data.field));*/
                /*
                {"uname":"阳台123","sex":"男","phone":"15755082312","mailbox":"42769721@qq.com","address":"安徽省滁州市","uid":"1"}
                 */
                return true;
            });

        });
    </script>


    <script>
        layui.use(['form', 'jquery','layer'], function() {
            var form = layui.form,
                $ = layui.jquery,
                admin = layui.admin,
                layer = layui.layer;


            //监听是否 为关，如果关了，就给1  开就0
            form.on('switch(ifKeyDemo)', function(data){
                var index_sms;
                var alert_value =this.checked ? '0' : '1';
                var uid = $(this).val();
//                alert(uid);
            //状态更新

                $.ajax({
                    type: 'POST',
                    url: '${pageContext.request.contextPath}/test/member/updateMemberStatus',
                    data: {"memberstatus":alert_value,"uid":uid},
                    beforeSend:function(){
                        layer.msg('正在切换中，请稍候',{icon: 16,time:1000,shade:0.8});
                    },
                    error: function(){
                        layer.msg('数据异常，操作失败！');
                    },
                    success: function(data){
                        if(data.data=="1"){
                            layer.msg("操作成功！", {icon: 1});
                        }else{
                            layer.msg("数据异常，操作失败！",{'icon':2});
                        }
                    },

                    dataType:'json'
                });


            });

        });
    </script>







</div>



</body>

</html>