$(document).ready(function () {
	function myDebug(str){
		console.log(str);
	}
	
	// 验证输入合理性
	function check_AddPerson_form(){
		//myDebug('test');
		// 判断姓名是否为空
		if( $("#name").val().trim() == ""){
			$("div#tip").text("姓名不能为空");
			$("div#tip").show();
			return false;
		}
		
		// 判断银行卡账号是否为空
		if( $("#ic_card").val().trim() == ""){
			$("div#tip").text("银行卡账号不能为空，不知道你可以用0代替着先");
			$("div#tip").show();
			return false;
		}
		
		// 判断工号是否为空
		if( $("#job_no").val().trim() == ""){
			$("div#tip").text("工号不能为空，不知道你可以用0代替着先");
			$("div#tip").show();
			return false;
		}
		
		// 判断身份证是否为空
		var id_card = $("#id_card").val().trim();
		if( id_card == ""){
			$("div#tip").text("银行卡不能为空，不知道你可以用0代替着先");
			$("div#tip").show();
			return false;
		}
		if( id_card.length != 18){
			$("div#tip").text("身份证不是18位");
			$("div#tip").show();
			return false;
		}
		
		var birthday = $("#birthday").val().trim();
		// 出来掉 - 字符
		var new_birth = birthday.replace(/-/g, "");
		//myDebug(new_birth);
		
		var id_birth = id_card.substring(6,14);
		//myDebug(id_birth);
		if( new_birth === id_birth){
			;
		}else{
			$("div#tip").text("身份证与出生日期不对应");
			$("div#tip").show();
			return false;
		}
		
		return true;
	}; // end check_AddPerson_form()
	
	$("div#tip").hide();
	$("form#addPerson_form").submit(function(){
		$("div#tip").hide();
		if(check_AddPerson_form()){
			var data = $('#addPerson_form').serialize();
			data = decodeURIComponent(data,true);
			myDebug(data);
			// post
			$.post("person/add",data,
                function(data) {
                    if (data.ok == true) {
                    	$("div#tip").text("录入成功");
            			$("div#tip").show();
                        //location.href = "user";
                    } else {
                    	$("div#tip").text("录入失败，系统出错，请重试");
            			$("div#tip").show();
                    }
                }
            );
		}else{
			return false;
		}
		return false;
	});
	
	
	
	
});// end
