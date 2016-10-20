<%@ page language="java"
	import="java.util.*,net.kjk.nutzbook.bean.MyMenu" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'getMenu.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
        String s2_url = "\"><a href=\"",s3_name = "\">",s4_end = "</a></li>";
        String s5_ul1 = "<ul>",s6_ul2 = "</ul>";
        String s7_ul_first = "<ul show=\"true\">";
     %>
	<% MyMenu m = (MyMenu)(session.getAttribute("menus")); %>
	<div class="st_tree">
		<ul>
			<%-- 显示顶级菜单 ,先写屎吧--%>
			<%  
			    String t = s1_nor_id + m.getId() + s2_url + m.getUrl() + s3_name + m.getName() + s4_end 
			               + s7_ul_first;
			    out.write(t);
                for(int i = 0; i < m.getChildSize();i++)
                {
                   MyMenu c = m.getChildById(i);
                   // 判断是否有子菜单
                   if(c.isHasChild())
                   {
                       //有的话，顶级菜单
                       //t = s1_nor_id + c.getId() + s2_url + c.getUrl() + s3_name + c.getName() + s4_end 
			           //    + s5_ul1;
			           t = s1_nor_id + c.getId() + s2_url + "#" + s3_name + c.getName() + s4_end 
			               + s5_ul1;
			           out.write(t);
			           for(int k = 0; k < c.getChildSize();k++)
                       {
                          MyMenu c2 = c.getChildById(k);
                          // 判断是否有子菜单
                          if(c2.isHasChild())
                          {
                              //有的话，顶级菜单
                              //t = s1_nor_id + c2.getId() + s2_url + c2.getUrl() + s3_name + c2.getName() + s4_end 
			                  //    + s5_ul1;
			                  t = s1_nor_id + c2.getId() + s2_url + "#" + s3_name + c2.getName() + s4_end 
			                      + s5_ul1;
			                  out.write(t);
			                  // 遍历四级菜单
			                  for(int j = 0; j < c2.getChildSize();j++)
                              {
                                 MyMenu c3 = c2.getChildById(k);
                                 // 判断是否有子菜单
                                 if(c3.isHasChild())
                                 {
                                     //有的话，顶级菜单
                                     //t = s1_nor_id + c3.getId() + s2_url + c3.getUrl() + s3_name + c3.getName() + s4_end 
			                         //    + s5_ul1;
			                         t = s1_nor_id + c3.getId() + s2_url + "#" + s3_name + c3.getName() + s4_end 
			                             + s5_ul1;
			                         out.write(t);
                                 }
                                 else
                                 {
                                     t = s1_nor_id + c3.getId() + s2_url + c3.getUrl() + s3_name + c3.getName() + s4_end;
                                     out.write(t);
                                 }
                                 if(c3.isHasChild())
                                 {
                                     //有的话，顶级菜单,补上
                                     out.write(s6_ul2);
                                 }
                              }  // 遍历完四级菜单
                          }
                          else
                          {
                              t = s1_nor_id + c2.getId() + s2_url + c2.getUrl() + s3_name + c2.getName() + s4_end;
                              out.write(t);
                          }
                          if(c2.isHasChild())
                          {
                              //有的话，顶级菜单,补上
                              out.write(s6_ul2);
                          }
                       } // 遍历第三级菜单
                   } // 如果存在三级菜单
                   else
                   {
                       t = s1_nor_id + c.getId() + s2_url + c.getUrl() + s3_name + c.getName() + s4_end;
                       out.write(t);
                   } // 对于二级菜单中，不是作为父节点的那些
                   if(c.isHasChild())
                   {
                       //有的话，顶级菜单,补上
                       out.write(s6_ul2);
                   } // 补上二级菜单中是父节点的
                }
                out.write(s6_ul2);
			 %>
		</ul>
	</div>
</body>
</html>
