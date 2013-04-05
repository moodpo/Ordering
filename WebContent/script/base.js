/**
 * 通用的js事件方法
 */
$(document).ready(function(){
	// 邮箱正则
	var mainRex = /^\w+$/;
	var cnNameRex = /^[\u4e00-\u9fa5]+$/;
	var pwdRex = /^\w{3,12}$/;
	
	// ========== 警告条关闭事件 login/sign/findPwd.jsp
	$('.alert>a.close').click(function(){
		$(this).parent().fadeOut();
	});
	
	// ========== 验证码更换事件 login/sign/findPwd.jsp
	$('img.valicodeimg').click(function(){
		$(this).attr('src', PATH+'/Kaptcha.jpg?' + Math.floor(Math.random()*100) ); 
	});
	
	// ========== 检查注册事件 sign.jsp
	$('button.submitSign').click(function(){
		var mail = $.trim($('#usermail').val());
		var name = $.trim($('#loginName').val());
		var code = $.trim($('#valicode').val());
		if(mail == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不能为空！");
		} else if(!mainRex.test(mail)){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不正确，请输入有效邮箱地址！！");
		} else if(name == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 中文名不能为空！");
		} else if(!cnNameRex.test(name)){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 请输入中文字符！");
		} else if(code == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 请输入验证码！");
		} else if(code.length != 5){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 您输入的验证码不正确，验证码为5位！");
		} else{
			$('#sign-form').submit();
		}
	});
	
	// ========== 检查登录事件 login.jsp
	$('button.submitLogin').click(function(){
		var mail = $.trim($('#usermail').val());
		var pwd = $.trim($('#userpwd').val());
		var code = $.trim($('#valicode').val());
		if(mail == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不能为空！");
		} else if(!mainRex.test(mail)){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不正确，请输入有效邮箱地址！");
		} else if(pwd == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 密码不能为空！");
		} else if(!pwdRex.test(pwd)){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 密码长度为3-12位字母、数字或下划线！");
		} else if(code == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 请输入验证码！");
		} else if(code.length != 5){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 您输入的验证码不正确，验证码为5位！");
		} else{
			$('#login-form').submit();
		}
	});
	
	// ========== 检查找回密码事件 findPwd.jsp
	$('button.submitFindPwd').click(function(){
		var mail = $.trim($('#usermail').val());
		var code = $.trim($('#valicode').val());
		if(mail == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不能为空！");
		} else if(!mainRex.test(mail)){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 邮箱地址不正确，请输入有效邮箱地址！");
		} else if(code == ''){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 请输入验证码！");
		} else if(code.length != 5){
			$('.alert').fadeIn();
			$('.alert-text').html("<strong>警告！</strong> 您输入的验证码不正确，验证码为5位！");
		} else{
			$('#findPwd-form').submit();
		}
	});
	
	// ========== 修改中文名事件
	$('button.alterName').click(function(){
		var name = $.trim($('#username').val());
		if(name == ''){
			$('#username-text').html("中文名不能为空！");
		} else if(!cnNameRex.test(name)){
			$('#username-text').html("请输入中文字符！");
		} else {
			$('#alertName-form').submit();
		}
	});
	
	// 修改密码事件
	$('button.alertPwd').click(function(){
		var oldPwd = $.trim($('#oldpwd').val());
		var newPwd = $.trim($('#newpwd').val());
		var rePwd = $.trim($('#repwd').val());
		var code = $.trim($('#valicode').val());
		$('.help-inline').html('');
		if(oldPwd == ''){
			$('#oldpwd-text').html("旧密码不能为空！");
		} else if(!pwdRex.test(oldPwd)){
			$('#oldpwd-text').html("密码应是3到12位的字母、数字或下划线。");
		} else if(newPwd == ''){
			$('#newpwd-text').html("新密码不能为空！");
		} else if(!pwdRex.test(newPwd)){
			$('#newpwd-text').html("密码应是3到12位的字母、数字或下划线。");
		} else if(rePwd == ''){
			$('#repwd-text').html("请重复输入密码！");
		} else if(rePwd != newPwd){
			$('#repwd-text').html("两次输入的密码不同！");
		} else if(code == ''){
			$('#code-text').html("请输入验证码！");
		} else if(code.length != 5){
			$('#code-text').html("您输入的验证码不正确，验证码为5位！");
		} else{
			$('#alertPwd-form').submit();
		}
		
	});
	
	
	
	// ========== 订餐页面提交一行数据 ordering.jsp
	$('a.submitPrice').click(function(){
		
	});
	
	// ========== 订餐页面取消一行数据 ordering.jsp
	$('a.cancelPrice').click(function(){
		
	});
	
	
});