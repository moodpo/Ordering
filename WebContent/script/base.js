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
	$('#sign-form').submit();
}


