<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="js/jquery.min.js"></script>
<script src="js/user.js"></script>

</head>

<body>
    <!-- 分为两块 -->
	<div id="content">
	    <div id="head" class="head">
	        <h3>人员管理</h3>
	    </div>
	    <!--  辅助区域   -->
	    <div id="assit" class="hashr">
	        <table class="support">
	        <tr>
	            <td>
	                 <input type="radio" name="cates" value="0" class="cate-radio"/>全部
	                 <input type="radio" name="cates" value="1" class="cate-radio" checked="checked"/>在职
	                 <input type="radio" name="cates" value="2" class="cate-radio" />退休
	                 <input type="radio" name="cates" value="3" class="cate-radio" />遗嘱
	                 <input type="radio" name="cates" value="4" class="cate-radio" />聘用制
	           </td>
	        </tr>
	        <tr>
	            <td>
	                 <label for="shai_xuan">筛选: </label>
		             <select name="shai_xuan_sel" id="shai_xuan_sel">
		                 <option value ="name" selected="selected">姓名</option>
		                 <option value ="sex">性别</option>
		                 <option value ="id_card">身份证</option>
		                 <option value ="grade">职别</option>
		                 <option value ="6">办事员</option>
		             </select>&nbsp;
		             <input type="text" name="shai_xuan_text" id="shai_xuan_text" />&nbsp;
		             <input type="button" name="shai_xuan_btn" id="shai_xuan_btn" value="检索"/>
		        </td>
	        </tr>
	    </table>
	    </div>
	    <!--  显示区域 -->
	    <div id="show">
	    <table class="hovertable">
	        <tr>
	            <th>工号</th>
	            <th>姓名</th>
	            <th>科室</th>
	            <th>级别</th>
	            <th>工龄</th>
	            <th></th>
	        </tr>
	        <tr>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>详情 删除 启用状态</td>
	        </tr>
	        <tr>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>详情 删除 启用状态</td>
	        </tr>
	        <tr>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>hello</td>
	            <td>详情 删除 启用状态</td>
	        </tr>
	        <tr>
	            <td colspan="6">
	                <input type="button" id="pre_page_btn" value="上一页" />
	                <b id="cur_page">1</b>&nbsp;/&nbsp;
	                <b id="last_page">10</b>
	                <input type="button" id="next_page_btn" value="下一页" />&nbsp;&nbsp;
	                <input type="text" id="jump_page" />&nbsp;
	                <input type="button" id="jump_page_btn" value="跳转" />
	            </td>
	        </tr>
	    </table>
	    </div>
	</div>
</body>
</html>
