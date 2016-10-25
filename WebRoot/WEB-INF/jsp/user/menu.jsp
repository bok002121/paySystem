<%@ page language="java" import="java.util.*,net.kjk.nutzbook.bean.MyMenu" pageEncoding="UTF-8"%>
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
    
    <%!
        String s1_nor_id = "<li id=";
        String s2_url = "\"><a target=\"content\" href=\"",s3_name = "\">",s4_end = "</a></li>";
        String s5_ul1 = "<ul>",s6_ul2 = "</ul>";
        String s7_ul_first = "<ul show=\"true\">";
        
        String showMenu(MyMenu m)
        {
            String rs = "";
            String t = "";
		    // 判断是否有子菜单
		    if( m.isHasChild())
		    {
		       //t = s1_nor_id + m.getId() + s2_url + m.getUrl() + s3_name + m.getName() + s4_end; 
		       t = s1_nor_id + m.getId() + s2_url + "#" + s3_name + m.getName() + s4_end; 
		       if(m.getName().equals("欢迎使用"))
		       {
		    	  t = t + s7_ul_first;
		       }
			   else
			   {
			       t = t + s5_ul1;
			   }
			   rs = rs + t;
			   // 用的话，孩子遍历
			   for(int i = 0;i<m.getChildSize();i++)
			   {
			       rs = rs + showMenu(m.getChildById(i));
			   }
			   rs = rs + s6_ul2;
			}
			else
			{
			    t = s1_nor_id + m.getId() + s2_url + m.getUrl() + s3_name + m.getName() + s4_end;
			    rs = rs + t;
			}
			
            return rs;
        }
     %>
	<% MyMenu m = (MyMenu)(session.getAttribute("menus")); %>
	<div class="st_tree">
		<ul>
			<%
			    if( m!= null)
			        out.write(showMenu(m));
			    else
			        out.write("session menu is not exist");
			 %>
		</ul>
	</div>
</body>
</html>
