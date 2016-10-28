$(document).ready(function () {
	
	function myDebug(str){
		console.log(str);
	}
	
	function showTip(str)
	{
		$("#workTip").text(str);
    	$("#workTip").fadeIn("slow",function(){
    		$("#workTip").fadeOut("slow");
    	});
	}
	
	$("#person_in_btn").click(function(){
		var fileName = $("#input_file").val();
		var reg = /\.xl(s|s[xmb]|t[xm]|am)$/;
		showTip(fileName);
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