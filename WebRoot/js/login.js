$(document).ready(function () {
	
	// 判断是否为空
	function check_login_form(){

		var username = $("#username");
		var password = $("#password");
        
		if(username.val().length == 0){
			$("div#tip").text("用户名或密码没填");
			$("div#tip").show();
			
			username.focus();
		    return false;
		}
		
		if(password.val().length == 0){
			$("div#tip").text("用户名或密码没填");
			$("div#tip").show();
			
			password.focus();
			return false;
		}
		$("div#tip").hide();
		return true;
	};
	
	$("div#tip").hide();
	$("form#form_login").submit(function(){
		if(check_login_form()){
			// post
			$.post("user/login",
                {username:$("#username").val(),
                	  password:$("#password").val()
                },
                function(data) {
                    if (data.ok == true) {
                    	$("div#tip").text("登录成功");
            			$("div#tip").show();
                        location.href = "user";
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
});