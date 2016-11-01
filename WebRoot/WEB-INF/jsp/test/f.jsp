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
<link rel="stylesheet" type="text/css" href="css/uploadify.css">
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.custom.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.min.js"></script> 
<script>
$(document).ready(function(){
    function showTip(str)
	{
		$("#workTip").text(str);
    	$("#workTip").fadeIn("slow",function(){
    		$("#workTip").fadeOut("slow");
    	});
	}
	
    $("#uploadify").uploadify({  
        uploader : '#',  
        swf : 'swf/uploadify.swf',  
        buttonText : '选择文件',
        queueID : 'fileQueue',  
        auto : false,  
        fileTypeDesc : '*.xls',
        fileTypeExts : '*.xls',
        fileObjName : 'Filedata',
        overrideEvents : [ 'onDialogClose', 'onUploadError', 'onSelectError' ],
        onSelectError: function(file, errorCode, errorMsg) {
        	showTip('The file ' + file.name + ' 上传错误，当前只允许上传 .xls文件.');
        },
        onUploadSuccess: function(file, data, response){
            //var alldata = eval('(' + data + ')');
            var alldata = jQuery.parseJSON(data);
            showTip(alldata.msg);
        }
    }); 
    
	$("#person_in_btn").click(function(){
		//if(checkFile()){
			$('#uploadify').uploadify('settings','uploader','../test2/inPerson');
			$('#uploadify').uploadify('upload','*');
		//}
	});
	
	$("#person_update_btn").click(function(){
		//if(checkFile()){
			$('#uploadify').uploadify('setting','uploader','../test2/iUpdatenPerson');
			$('#uploadify').uploadify('upload','*');
		//}
	});
	
	$("#person_out_btn").click(function(){
		showTip("out click");
	});
});
</script>
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
	                <input type="button" id="uploadify">
	                <input type="button" id="person_in_btn" value="导入" />
	                <input type="button" id="person_update_btn" value="更新导入" />
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
	        <tr>
	            <td>
	                <div id="fileQueue"></div>
	            </td>
	        </tr>
	    </table>
	    </div>
	</div>
</body>
</html>
