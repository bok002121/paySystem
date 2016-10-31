<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'f.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/uploadify.css" rel="stylesheet" type="text/css" /> 
	<script src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.uploadify.js"></script>  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

   <script type="text/javascript">  
            $(document).ready(function() {  
                $("#uploadify").uploadify({  
                    'uploader'       : 'inPerson',  
                    'swf'            : 'swf/uploadify.swf',  
                    'buttonText'     : '选择文件',
                    'queueID'        : 'fileQueue',  
                    'auto'           : false,  
                }); 
                
                $("#start").click(function(){
                    //alert("start");
                    $('#uploadify').uploadify('upload','*');
                }); 
                
                $("#stop").click(function(){
                    $('#uploadify').uploadify('cancel','*');
                }); 
            });  
        </script>  
    </head>  
    <body>  
        <table style="width: 90%;">  
            <tr>  
                <td style="width: 100px;">  
                    <input type="file" name="uploadify" id="uploadify" />  
                </td>  
                <td align="left">  
                    <input type="button" id="start" value="文件上传" />
                    <input type="button" id="stop" value="取消上传" />
                </td>  
            </tr>  
        </table>  
        <div id="fileQueue" class="uploadify-queue"></div>  
    </body>  
</html>  
