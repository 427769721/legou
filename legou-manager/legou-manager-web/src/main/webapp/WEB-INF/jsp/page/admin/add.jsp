<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>


<head>
    <meta charset="UTF-8">
    <title>管理员添加-后台管理系统-Admin 1.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/layui2/css/layui.css">    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui2/layui.js" charset="utf-8"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/admin.js" charset="utf-8"></script>

    <![endif]-->
</head>

<body>
<div class="weadmin-body">
    <form class="layui-form" method="post" action="${pageContext.request.contextPath}/test/admin/add">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>管理员名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="aname"   lay-verify="required|nikename" autocomplete="off" class="layui-input">
                
            </div>

            <div class="layui-form-mid layui-word-aux">
                至少3个字符
            </div>

        </div>

        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="we-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="apwd" lay-verify="required|pass" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="we-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass"   lay-verify="required|repass" autocomplete="off" class="layui-input">
            </div>
        </div>
        
        
        <div class="layui-form-item">
            <button class="layui-btn" lay-filter="formDemo" lay-submit=""  >确定</button>
        </div>
    </form>
</div>
<script type="text/javascript">


    layui.use(['form', 'jquery','layer'], function() {
        var form = layui.form,
            $ = layui.jquery,
            admin = layui.admin,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            nikename: function(value) {
                if(value.length < 2) {
                    return '昵称至少得2个字符啊';
                }
            },
            pass: [/(.+){6,20}$/, '密码必须3到20位'],
            repass: function(value) {
                if($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });

    });
</script>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return true;
        });
    });
</script>
</body>

</html>