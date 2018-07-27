//引入admin.js(webapp/static/js/admin.js)
//memberlist.js(webapp/static/js/memberlist.js)


// http://localhost:81/manager/page/member/list
/*layui.extend({
    admin: '{/}../../static/js/admin'
});*/
//按需加载admin.js
layui.use(['jquery', 'table'], function () {
    //初始化变量
    var $ = layui.jquery,
        table = layui.table;
    //对表格进行渲染
    table.render({
        //什么是表格属性：page,elem,url,cols
        //什么是列属性：type,field,title
        //开启分页
        page: true,
        //渲染的容器是谁
        elem: '#articleList',
        //异步提交请求给后台返回JSON
        url: '../../test/admins/show',
        //要显示的表头是什么
        cols: [[
            {type: 'checkbox'},
            {field: 'aname', title: '管理员名'},
            {field: 'aid', title: '操作', templet: '#operateTpl'}
        ]]
        
    });

    //
    var active = {
        getCheckData: function () {
            var data=table.checkStatus("articleList").data;
            if(data.length > 0){
                var aids =[];
                for (var i= 0;i<data.length ;i++){
                    aids.push(data[i].aid);
                }

                $.post(
                    '../../test/admins/delete',
                    {'aids[]':aids},
                    function (data) {
                        if(data.data>0){
                            $('.layui-laypage-btn').click();
                            layer.msg("GG~删除成功！", {icon: 1});
                        }
                    }

                )
            }else{
                layer.msg("至少你TM选择一行再删啊!",{'icon':2});
            }


        },


        reload:function(){
            //模糊查询，提交一个异步请求到后台 {title}
            //val() 文本框 单选按钮 复选按钮
            //text()  html()

            var title = $("#aname").val();
            //不为空
            if($.trim(title).length > 0 ){
                //文本框中有内容，表格重载
                table.reload("articleList",{
                    page:{curr:1},
                    where:{aname:title}
                });
            }else{
                layer.msg("输入内容查询!",{'icon':6});
            }

        }

    };

    /*
    批量删除的监听事件
     */
    $(".demoTable .layui-btn-danger").on('click',function () {

        var type=$(this).data("type");
        active[type] ? active[type].call(this) :'';
    });


    //点击"模糊查询"按钮触发的事件
    $(".weadmin-body .layui-btn").click(function () {
        //为了获取按钮的data-type属性
        var type = $(this).data("type");//reload
        //判断active变量中是否具有reload属性
        active[type] ? active[type].call(this) : '';
    });

});