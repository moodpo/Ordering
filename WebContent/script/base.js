/**
 * 
 */
$(document).ready(function(){
	// ========== 验证码
	$("img.valicodeimg").click(function(){
		$(this).attr('src', PATH+'/Kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
	});
	
	
	
	
});


// 检查注册事件
function checkSign(){
	var mail = $("#usermail").val();
	if(mail == ''){
		
	}
	
	$("#usermail").val();
	
	$("#userpwd").val();
	
	$("#valicode").val();
	
}


