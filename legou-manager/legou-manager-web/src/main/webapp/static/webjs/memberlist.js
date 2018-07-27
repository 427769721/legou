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
        url: '../../test/member/show',
        //要显示的表头是什么
        cols: [[
            {type: 'checkbox'},
            {field: 'uname', title: '登录名'},
            {field: 'mailbox', title: '邮箱'},
            {field: 'gender', title: '性别'},
            {field: 'membership', title: '会员等级'},
            {field: 'lasttime', title: '最后登录时间'},
            {field: 'memberstatus', title: '会员状态', templet: '#myTpl'},
            {field: 'uid', title: '操作', templet: '#operateTpl'}

        ]]
        ,done:function(res,curr,count){
            // var $statusCol = $("[data-field='status']");//商品状态这一列
            // console.log($statusCol.children());
            // console.log($("[data-field='status']").children());
            $("[data-field='membership']").children().each(function () {

                if($(this).text()== '0'){
                    $(this).text('非会员');
                } else if ($(this).text()== '1'){
                    $(this).text('初级会员');
                } else if ($(this).text()== '2'){
                    $(this).text('中级会员');
                } else if ($(this).text()== '3'){
                    $(this).text('高级会员');
                }

            });

            $("[data-field='memberstatus']").children().each(function(){
                // this 当前DOM对象
                //$(this) 当前jQuery对象
                // val() text() html() 三个函数都是既可以设值也可以取值
                // val() 一般用于文本框 单选按钮 复选按钮这些的取值
                // text() 一般是用于获取指定控件中的文本 <div><strong>hello</strong></div> text() ====> hello
                // html() 一般是用于获取指定控件中的标签和文本 <div><strong>hello</strong></div> html() ====> <strong>hello</strong>
                // console.log($(this).text());
                if($(this).text() == 0){
                    $(this).text('正常');
                }else if($(this).text() == 1){
                    $(this).text('失效');
                }
            });
        }
    });

    //
    var active = {
        getCheckData: function () {
            var data=table.checkStatus("articleList").data;
            if(data.length > 0){
                var uids =[];
                for (var i= 0;i<data.length ;i++){
                    uids.push(data[i].uid);
                }

                $.post(
                    '../../test/member/deletechance',
                    {'uids[]':uids},
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


            var title = $("#uname").val();
            if($.trim(title).length > 0 ){
                table.reload("articleList",{
                    page:{curr:1},
                    where:{uname:title}
                });
            }else{
                layer.msg("输入内容查询!",{'icon':6});
            }

        }

    };

    /*
    批量删除的监听事件
     */
    $(".demoTable .layui-btn-normal").on('click',function () {

        var type=$(this).data("type");
        active[type] ? active[type].call(this) :'';
    });


    //点击" 查询"按钮触发的事件
    $(".weadmin-body .layui-btn").click(function () {
        var type = $(this).data("type");
        active[type] ? active[type].call(this) : '';
    });

});


function deletemember(uid) {
    layui.use(['jquery', 'table'], function () {
        var layer =layui.layer,
            jquery =layui.jquery;
        layer.confirm("删除会员？",function () {

            location.href="../../test/member/delete?uid="+uid;
        });
    })
}