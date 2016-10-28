<%@ page language="java" import="java.util.*,net.kjk.nutzbook.bean.Category" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>人员管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="image/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/my.css">
<link rel="stylesheet" type="text/css" href="css/form.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.custom.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.custom.js"></script>
<script src="js/inout.js"></script>

</head>

<body>
    <!-- 分为两块 -->
	<div id="content">
	    <div id="head" class="head">
	        <h3>人员管理 - 导入导出</h3>
	    </div>
	    <!--  辅助区域   -->
	    <div id="assit" class="hashr">
	        <table class="support">
	        <tr>
	            <td>
	                 <input type="file" id="input_file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"/>
	                 <input type="button" id="person_in_btn" value="导入" />
	           </td>
	        </tr>
	        <tr>
	            <td>
	                 <input type="button" id="person_out_btn" value="导出"/>
		        </td>
	        </tr>
	        <tr>
	            <td>
		             <span id="workTip" style="color:red;"></span>
		        </td>
	        </tr>
	    </table>
	    </div>
	</div>
</body>
</html>
