<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>会员资料编辑-后台管理系统-Admin 1.0</title>
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
    <form class="layui-form" method="post" action="${pageContext.request.contextPath}/test/user/edit">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="we-red">*</span>用户名
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="uname" value="${tbUser.uname}" lay-verify="required|nikename" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                请设置至少5个字符，请谨慎对用户操作
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_sex" class="layui-form-label">性别</label>
            <div class="layui-input-block" id="L_sex">
                <input type="radio" name="gender" value="男"  title="男" ${tbUser.gender=='男'?"checked='checked'":""} >
                <input type="radio" name="gender" value="女" title="女" ${tbUser.gender=='女'?"checked='checked'":""}>
                <input type="radio" name="gender" value="未知" title="未知" ${tbUser.gender==''?"checked='checked'":""}>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_phone" name="phone" value="${tbUser.phone}" lay-verify="required|phone" autocomplete="" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="mailbox" value="${tbUser.mailbox}" lay-verify="email" autocomplete="off" class="layui-input">
            </div>

        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="we-red">*</span>会员级别
            </label>
            <div class="layui-input-inline">
                <select name="membership" lay-verify="">
                    <option value="0" ${tbUser.membership=='0'?"selected='selected'":""} >非会员</option>
                    <option value="1" ${tbUser.membership=='1'?"selected='selected'":""}>初级会员</option>
                    <option value="2" ${tbUser.membership=='2'?"selected='selected'":""}>中级会员</option>
                    <option value="3" ${tbUser.membership=='3'?"selected='selected'":""}>高级会员</option>
                </select>
            </div>

        </div>
        <div class="layui-form-item">
            <label for="L_address" class="layui-form-label">
                <span class="we-red"></span>地址
            </label>
            <div class="layui-input-inline">
                <input type="p" id="L_address" name="address" value="${tbUser.address}" autocomplete="off" class="layui-input">
            </div>

        </div>
      <%--  <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="we-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="upwd" value="${tbUser.upwd}" lay-verify="required|pass" autocomplete="off" class="layui-input">
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
                <input type="password" id="L_repass" name="repass" lay-verify="required|repass" autocomplete="off" class="layui-input">
            </div>
        </div>--%>
        <div class="layui-form-item">
           <%-- <label for="L_repass" class="layui-form-label">
            </label>--%>
            <button class="layui-btn" lay-filter="formDemo" lay-submit=""  >确定</button>

            <button class="layui-btn" type="button"  onclick="history.back()" >返回</button>
            <input type="hidden" name="uid"  value="${tbUser.uid}" />
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
            pass: [/(.+){3,20}$/, '密码必须3到20位'],
            repass: function(value) {
                if($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            }
        });


        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            /*
            {"uname":"阳台123","sex":"男","phone":"15755082312","mailbox":"42769721@qq.com","address":"安徽省滁州市","uid":"1"}
             */
            return true;
        });

    });
</script>
</body>

</html>