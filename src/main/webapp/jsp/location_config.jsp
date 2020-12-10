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
        经度<input name="lon" type="text"/>
        纬度<input name="lat" type="text"/>
    </form>
    <a href="javascript:void(0)"  id="submitForm" onclick="submitForm()" style="width:80px">提交</a>
</body>
<script>
    function submitForm(){

            var fields = $('#bxyform').serializeArray();
            var obj = {};
            $.each(fields, function(index, field) {
                obj[field.name] = field.value;
            })

            console.log(JSON.stringify(obj));
            var dats=JSON.stringify(obj);
            $.ajax({
                type: "post",
                url: basePath+"/location/select",
                contentType:"application/json;charset=utf-8",
                async: true,
                dataType: 'JSON',
                data:dats, //将对象转为json字符串
                success: function(res) {
                    debugger;
                    console.log(res);
                }
            });

    }

</script>
</html>
