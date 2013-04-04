/**
 * 通用的js事件方法
 */
$(document).ready(function(){
	// ========== 验证码更换事件
	$("img.valicodeimg").click(function(){
		$(this).attr('src', PATH+'/Kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
	});
	
	
	
	
});


// 检查注册事件
function checkSign(){
	$('#sign-form').submit();
}

// 检查登录事件
function checkLogin(){
	$('#login-form').submit();
}
