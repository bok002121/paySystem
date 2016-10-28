$(document).ready(function () {
	
	function myDebug(str){
		console.log(str);
	}
	
	$("#person_in_btn").click(function(){
		var fileName = $("#input_file").val();
		var reg = /\.xl(s|s[xmb]|t[xm]|am)$/;
		myDebug(fileName);
		if(reg.test(fileName)){
			myDebug("excel file");
		}else{
			myDebug("not excel file");
		}
	});
	
	$("#person_out_btn").click(function(){
		myDebug("out click");
	});
	
});