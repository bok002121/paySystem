$(document).ready(function () {
	
	function myDebug(str){
		console.log(str);
	}
	
	function showTip(str)
	{
		$("#workTip").text(str);
    	$("#workTip").fadeIn("slow",function(){
    		$("#workTip").fadeOut("5000");
    	});
	}
	
	
	
	function checkFile(){
		var fileName = $("#input_file").val();
		var reg = /\.xl(s|s[xmb]|t[xm]|am)$/;
		showTip(fileName);
		
		if(reg.test(fileName)){
			myDebug("excel file");
			return true;
		}else{
			myDebug("not excel file");
		}
		return false;
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
        }
    }); 
    
	$("#person_in_btn").click(function(){
		//if(checkFile()){
			$('#uploadify').uploadify('settings','uploader','../person/inPerson');
			$('#uploadify').uploadify('upload','*');
		//}
	});
	
	$("#person_update_btn").click(function(){
		//if(checkFile()){
			$('#uploadify').uploadify('setting','uploader','../person/iUpdatenPerson');
			$('#uploadify').uploadify('upload','*');
		//}
	});
	
	$("#person_out_btn").click(function(){
		showTip("out click");
	});
	
});