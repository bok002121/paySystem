<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" type="text/css" href="css/my.css">
	<script src="js/jquery.min.js"></script>
    <script src="js/login.js"></script>
  </head>
  
  <body>
     <div id="div_pane"></div>
     <div id="div_login" >
         <form action="#" id="form_login" method="post">
             <div class="header">
                 <h1>Login</h1>
             </div>
             <div class="form-body">
                 <label for="username">用户名</label>
                 <input type="text" id="username" name="username" class="form-control input-block"/>
                 <label for="password">密码</label>
                 <input type="password" id="password" name="password" class="form-control input-block" />
                 <div id="tip" class="flash flash-error"></div>
                 <input type="submit" value="登录" class="btn btn-effect btn-block" />
             </div> 
         </form>
     </div>
  </body>
</html>
