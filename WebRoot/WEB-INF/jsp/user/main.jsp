<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="shortcut icon" href="image/favicon.ico" >
	<link rel="stylesheet" type="text/css" href="my.css">
    <title>main</title>
    <style>
    frameset{
        border:1 gray;
    }
    </style>
</head>
<frameset rows="10%,*">
    <frame src="user/header" id="header" scrolling="no">
    <frameset cols="18%,*" id="bodyFrame">
        <frame src="user/menu" name="menu" id="menu" scrolling="auto">
        <frame src="welcome.jsp" name="content" id="content" scrolling="auto">
    </frameset>
    <!-- 后期再来补充就好  -->
    <!--<frame src="footer.html" id="footer" scrolling="no" noresize="noresize">-->
</frameset>
</html>
