<%@ page language="java" import="java.util.*,net.kjk.nutzbook.bean.UserInfo" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>addPerson</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="image/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.custom.css">
<link rel="stylesheet" type="text/css" href="css/my.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.custom.js"></script>
<script src="js/user.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<script>
  $(function() {
     $(".datepicker" ).datepicker();
     $(".datepicker" ).datepicker( "option", "dateFormat","yy-mm-dd");
  });
  </script>
<body>

<div id="addPerson_div">
	<form action="#" method="post" id="addPerson_form">
		<label for="name">姓名: </label>
		<input type="text" name="name" id="name" /> <br /> 
		
		<label>性别: </label>
		<label for="male">男 </label> <input type="radio" name="sex" id="male" value="男" checked="true"/>
        <label for="female">女 </label> <input type="radio" name="sex" id="female" value="女" /><br />
        
        <label for="birthday">出生年月: </label>
        <input type="text" class="datepicker" name="birthday" id="birthday"/><br />
        
        <label for="id_card">身份证: </label>
		<input type="text" name="idCard" id="id_card" "/> <br />
		
		 <label for="join_time">参加工作时间: </label>
         <input type="text" class="datepicker" name="joinTime" id="join_time" /><br />
		 
		 <label for="grade_id">职别: </label>
		 <select name="gradeId" id="grade_id">
		     <option value ="1">正处</option>
		     <option value ="2">副处</option>
		     <option value ="3">正科</option>
		     <option value ="4">副科</option>
		     <option value ="5" selected="selected">科员</option>
		     <option value ="6">办事员</option>
		 </select>
		 <br />
		 
		 <label for="cate_id">类别: </label>
		 <select name="cateId" id="cate_id">
		     <option value ="1" selected="selected">在职</option>
		     <option value ="2">退休</option>
		     <option value ="3">遗属</option>
		     <option value ="4">聘用制</option>
		 </select>
		 <br />
		 
		 <label for="ic_card">银行卡账号: </label>
		 <input type="text" name="icCard" id="ic_card"/><br />
		 
		 <label for="dep_id">部门: </label>
		 <select name="depId" id="dep_id">
		     <option value="1" selected="selected">行领导</option>
		     <option value="2">办公室</option>
		     <option value="3" selected="selected">科技科</option>
		 </select>
		 <br />
		 
		 <label for="job_no">工号: </label>
		 <input type="text" name="jobNo" id="job_no"/><br />
		 
		 <div id="tip" class="flash flash-error"></div>
		 
		 <input type="submit" value="提交"/>&nbsp;&nbsp;
		 <input type="reset" value="重置"/>
	</form>
</div>
</body>
</html>
