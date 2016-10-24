$(document).ready(function () {
	
	// 验证输入合理性
	function check_AddPerson_form(){

		
		return true;
	}; // end check_AddPerson_form()
	
	$("div#tip").hide();
	$("form#addPerson_form").submit(function(){
		alert($('#addPerson_form').serialize());
		if(check_AddPerson_form()){
			// post
			$.post("test/addPerson",
                $('#addPerson_form').serialize(),
                function(data) {
                    if (data.ok == true) {
                    	$("div#tip").text("登录成功");
            			$("div#tip").show();
                        //location.href = "user";
                    } else {
                    	$("div#tip").text("登录失败，用户名或密码错误");
            			$("div#tip").show();
            			$("#password").focus();
                    }
                }
            );
		}else{
			return false;
		}
		return false;
	});
	
	
	
	
});// end
