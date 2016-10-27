$(document).ready(function () {
	function myDebug(str){
		console.log(str);
	}
// --------------  start person/manage ----------------------------------------------
	// 上一页，下一页, 跳转
	$("#pre_page_btn").click(function(){
		myDebug("判断是否第一页，上一页啦");
	});
	
	$("#next_page_btn").click(function(){
		myDebug("判断是否最后一页，下一页啦");
	});

	$("#jump_page_btn").click(function(){
		myDebug("判断是否当前页，和在范围内，跳转啦");
	});
	
	// 绑定 删除对话框
	
	$( "#person_delete_dialog" ).dialog({
	      resizable: false,
	      autoOpen: false,
	      width: 350,
	      modal: true,
	      buttons: {
	        "确定": function() {
	        	// 这里的话，就是去删除。采用同步吗? 应该用同步的感觉，不然你确定以后还没有删除。
	        	if($("#person_select_assis").val() == ""){
	        		// 说明出错了。
	        		myDebug("id 没设置好");
	        	}else{
	        		// 异步操作
	        	}
	        	
	          $( this ).dialog( "close" );
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	 });
	
	
	// 处理那3个链接点击事件
	$("a#person_delete").click(function(){
		// 获取对应id
		var h = $(this).prevAll("input[type=hidden]");
		// 设置到person_select_assis 中
		$("#person_select_assis").val(h.attr("id"));
		
		myDebug(h.attr("id") + "号:弹出个对话框 选择  delete");
		
		$( "#person_delete_dialog" ).dialog( "open" );
		
		$("#person_select_assis").val("");
		return false;
	});
	
	$("a#person_details").click(function(){
		myDebug("获取参数，进去信息页面 ");
		return false;
	});
	
	$( "#person_change_dialog" ).dialog({
	      resizable: false,
	      autoOpen: false,
	      width: 350,
	      modal: true,
	      buttons: {
	        "确定": function() {
	        	// 这里的话，就是去删除。采用同步吗? 应该用同步的感觉，不然你确定以后还没有删除。
	        	if($("#person_select_assis").val() == ""){
	        		// 说明出错了。
	        		myDebug("id 没设置好");
	        	}else{
	        		// 异步操作
	        	}
	        	
	          $( this ).dialog( "close" );
	        },
	        Cancel: function() {
	          $( this ).dialog( "close" );
	        }
	      }
	 });
	
	$("a#person_change_status").click(function(){
		var h = $(this).prevAll("input[type=hidden]");
		// 设置到person_select_assis 中
		$("#person_select_assis").val(h.attr("id"));
		
		myDebug(h.attr("id") + "号:弹出对话框，启动or禁用 ");
		
		$( "#person_change_dialog" ).dialog( "open" );
		
		$("#person_select_assis").val();
		return false;
	});
	
	$(":radio.cate-radio").click(function(){
		myDebug($(this).val());
		
		// 接着 post 获取项目
		
	});
// --------------  end person/manage ----------------------------------------------
	
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
