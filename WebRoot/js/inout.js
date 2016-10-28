$(document).ready(function () {
	
	function myDebug(str){
		console.log(str);
	}
	
	$("#person_in_btn").click(function(){
		var fileName = $("#input_file").val();
		var reg = /\.xl(s[xmb]|t[xm]|am)$/;
		var reg2 = /\.xls$/;
		myDebug(fileName);
		if(reg.test(reg)){
			myDebug("excel file");
		}else{
			myDebug("not excel file");
		}
	});
	
	$("#person_out_btn").click(function(){
		myDebug("out click");
	});
	
});