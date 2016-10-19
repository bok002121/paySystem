<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>menu</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="image/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/SimpleTree.css">
<script src="js/jquery.min.js"></script>
<script src="js/YcTree.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
$(function(){
    $(".st_tree").SimpleTree({
        click:function(a){
        }
    });
});
</script>
</head>
<body>
	<div class="st_tree">
		<ul>
			<li class="tree-top"><a href="#" ref="hyjm">欢迎界面</a>
			</li>
			<li class="tree-top"><a href="#" ref="xtgl">系统管理</a>
			</li>
			<ul show="true">
				<li><a href="index.html" ref="yhgl" target="content">用户管理</a>
				</li>
				<li><a href="header.html" ref="rzck" target="content">日志查看</a>
				</li>
			</ul>
			<li class="tree-top"><a href="#" ref="ckgl">仓库管理</a>
			</li>
			<ul>
				<li><a href="#" ref="kcgl">库存管理</a>
				</li>
				<li><a href="#" ref="shgl">收货管理</a>
				</li>
				<li class="tree-top"><a href="#" ref="fhgl">发货管理</a>
				</li>
				<ul>
					<li><a href="#" ref="yhgl">用户管理</a>
					</li>
					<li><a href="#" ref="rzck">日志查看</a>
					</li>
				</ul>
			</ul>
		</ul>
	</div>
</html>
