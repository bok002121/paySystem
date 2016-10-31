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
	
	$("#in_form").submit(function(){
//		var fileName = $("#input_file").val();
//		var reg = /\.xl(s|s[xmb]|t[xm]|am)$/;
//		showTip(fileName);
//		
//		if(reg.test(fileName)){
//			myDebug("excel file");
//		}else{
//			myDebug("not excel file");
//		}
	});
	
	$("#person_in_btn").click(function(){
		//$("#in_form").attr("action","person/inPerson");
		$("#in_form").attr("action","test/inPerson");
	});
	
	$("#person_update_btn").click(function(){
		$("#in_form").attr("action","person/inUpdatePerson");
	});
	
	$("#person_out_btn").click(function(){
		showTip("out click");
	});
	
});