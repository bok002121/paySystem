$(document).ready(function () {
	function myDebug(str){
		console.log(str);
	}
	
	$(":radio.cate-radio").click(function(){
		myDebug($(this).val());
		
		// 接着 post 获取项目
		
	});
	
	// 楠岃瘉杈撳叆鍚堢悊鎬�
	function check_AddPerson_form(){
		//myDebug('test');
		// 姓名不能为空
		if( $("#name").val().trim() == ""){
			$("div#tip").text("姓名不能为空");
			$("div#tip").show();
			return false;
		}
		
		// 银行卡不能为空
		if( $("#ic_card").val().trim() == ""){
			$("div#tip").text("银行卡不能为空，不知道可用0代替");
			$("div#tip").show();
			return false;
		}
		
		// 工号不能为空
		if( $("#job_no").val().trim() == ""){
			$("div#tip").text("工号不能为空，不知道可用0代替");
			$("div#tip").show();
			return false;
		}
		
		// 身份证不能为空
		var id_card = $("#id_card").val().trim();
		if( id_card == ""){
			$("div#tip").text("身份证不能为空");
			$("div#tip").show();
			return false;
		}
		if( id_card.length != 18){
			$("div#tip").text("身份证是18位");
			$("div#tip").show();
			return false;
		}
		
		var birthday = $("#birthday").val().trim();
		// 鍑烘潵鎺�- 瀛楃
		var new_birth = birthday.replace(/-/g, "");
		//myDebug(new_birth);
		
		var id_birth = id_card.substring(6,14);
		//myDebug(id_birth);
		if( new_birth === id_birth){
			;
		}else{
			$("div#tip").text("身份证上的日期，跟出生年月不同");
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
                    	$("div#tip").text("录入失败，请重试");
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
