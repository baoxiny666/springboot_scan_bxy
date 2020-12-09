<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript">
        var path = "<%=path%>";
        var basePath = "<%=basePath%>";
    </script>
    <script src="./js/jquery-1.11.1.min.js" ></script>
    <title></title>
</head>
<body>
    <form action="" method="post" id="bxyform">
        配置ID<input name="id" type="text"/>
        区域编号<input name="area_no" type="text"/>
        检查项名称<input name="item_name" type="text"/>
    </form>
    <a href="javascript:void(0)"  id="submitForm" onclick="submitForm()" style="width:80px">提交</a>
</body>
<script>
    function submitForm(){
        var isValid = true;
        form = new FormData(document.getElementById("bxyform"));
        if(isValid){
            var fields = $('#bxyform').serializeArray();
            var obj = {}; //声明一个对象
            $.each(fields, function(index, field) {
                obj[field.name] = field.value; //通过变量，将属性值，属性一起放到对象中
            })

            console.log(JSON.stringify(obj));
            var dats=JSON.stringify(obj);
            $.ajax({
                type: "post",
                url: basePath+"/scanconfig/add",
                contentType:"application/json;charset=utf-8",
                async: true,
                dataType: 'JSON',
                data:dats, //将对象转为json字符串
                success: function(res) {
                    console.log("增加了一条");
                }
            });
        }
    }

</script>
</html>
