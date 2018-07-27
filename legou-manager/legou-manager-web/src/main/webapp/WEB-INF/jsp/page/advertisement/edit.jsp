<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>资料编辑-后台管理系统-Admin 1.0</title>
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
    <form class="layui-form" method="post" action="${pageContext.request.contextPath}/test/adver/edit">
        <div class="layui-form-item">

            <div class="layui-input-inline">
                <span class="we-red">*</span>内容标题
                <input type="text" id="L_username" name="title" value="${tbContent.title}" lay-verify="required|nikename" autocomplete="off" class="layui-input">
            </div>

        </div>


        <div class="layui-form-item">

            <div class="layui-input-inline">
                <span class="we-red">*</span>图片描述
                <input type="text" id="L_phone" name="phone" value="${tbContent.titleDesc}" lay-verify="required|phone" autocomplete="" class="layui-input">
            </div>
        </div>




        <div class="layui-form-item">

            <label class="layui-form-label">商品图片</label>
            <div class="layui-input-block">
                <button type="button" class="layui-btn" id="upload1">上传图片</button>
                <input type="hidden" id="img_url" name="pic" value=""/>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" src="${tbContent.pic}" width="200px" height="150px" id="demo1"/>
                    <p id="demoText"></p>
                </div>
            </div>

        </div>



        </div>

        <div class="layui-form-item">

            <button type="submit" class="layui-btn" lay-filter="formDemo" lay-submit="">确定</button>
            <button class="layui-btn" type="button"  onclick="history.back()" >返回</button>
            <input type="hidden" name="id"  value="${tbContent.id}" />
            <input type="hidden" name="categoryId"  value="${tbContent.categoryId}" />
        </div>

    </form>
</div>
<script type="text/javascript">
    layui.use(['form', 'jquery','layer'], function() {
        var form = layui.form,
            $ = layui.jquery,
            admin = layui.admin,
            layer = layui.layer;

        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            /*
            {"uname":"阳台123","sex":"男","phone":"15755082312","mailbox":"42769721@qq.com","address":"安徽省滁州市","uid":"1"}
             */
            return true;
        });
    });
</script>

<script type="text/javascript" th:inline="javascript">
    layui.use('upload', function(){
        var upload = layui.upload
            , $ = layui.jquery;
        var uploadInst = upload.render({
            elem: '#upload1' //绑定元素
            ,url: '${pageContext.request.contextPath}/test/upload/img'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                //alert("上传成功"+res.url);
                document.getElementById("img_url").value = res.url;

            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });
    });
</script>

</body>

</html>