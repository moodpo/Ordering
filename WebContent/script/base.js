/**
 * 通用的js事件方法
 */
$(document).ready(function(){
	// 邮箱正则
	var mainRex = /^\w+$/;
	var cnNameRex = /^[\u4e00-\u9fa5]+$/;
	var pwdRex = /^\w{3,12}$/;
	var numRex = /^[0-9]*$/;
	
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
	
	// =================== 分页事件
	$('#page-form1 .page-first').click(function(){
		// 首页
		$('#page-form1 .currentPage').val('1');
		$('#page-form1').submit();
	});
	
	$('#page-form1 .page-prev').click(function(){
		// 上一页
		$('#page-form1 .currentPage').val($('#page-form1 .currentPage').val()*1 - 1);
		$('#page-form1').submit();
	});
	
	$('#page-form1 .page-next').click(function(){
		// 下一页
		$('#page-form1 .currentPage').val($('#page-form1 .currentPage').val()*1 + 1);
		$('#page-form1').submit();
	});
	
	$('#page-form1 .page-last').click(function(){
		// 尾页
		$('#page-form1 .currentPage').val($('#page-form1 .pageCount').val());
		$('#page-form1').submit();
	});
	
	$('#page-form1 .currentPage').keydown(function(event){
		if(event.keyCode == 13){
			var cp = $.trim($(this).val());
			if(cp == '' || !numRex.test(cp)){
				$('#tipModal .modal-body > p').html('请输入正确的页数！');
				$('#tipModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
				return false;
			} else if(cp <= 0 || cp > $('#page-form1 .pageCount').val()){
				$('#tipModal .modal-body > p').html('请输入1-'+$('#page-form1 .pageCount').val()+'之间的数字！');
				$('#tipModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
				return false;
			} else { // 第几页
				$('#page-form1').submit();
			}
		}
	});
	
	$('#page-form1 .page-size').change(function(){
		// 每页条数
		$('#page-form1').submit();
	});
	
	// ================== 取消订单
	$('.cancelOrder').click(function(){
		$(this).parent().submit();
	});
	
});

// ========== 选择饭菜数量
function changeNum(index,obj){
	$('#priceNum-'+index).val($(obj).val());
}

// ========== 订餐页面提交一行数据 ordering.jsp
function submitPrice(index){
	var dicName = "";
	// 检查是否选择一份
	var flag = false;
	$('input[name="dic-'+index+'"]').each(function(num){
		if($(this).attr('checked') == 'checked'){
			$('#dicID-'+index).val($(this).val());
			dicName = $(this).next().text();
			flag = true;
		}
	});
	if(!flag){
		$('#tipModal .modal-body > p').html('请在饭菜详细信息中至少选择一种再提交！');
		$('#tipModal').modal({
		    backdrop:true,
		    keyboard:true,
		    show:true
		});
		return false;
	}
	
	var priceName = $('#priceName-'+index).val();
	var priceValue = $('#priceValue-'+index).val();
	var priceNum = $('#priceNum-'+index).val();
	var priceID = $('#priceID-'+index).val();
	var dicID = $('#dicID-'+index).val();
	// 异步提交选择的饭菜
	var toUrl = PATH + '/content/secure/ordering!selectOrdering';
	$.post(toUrl,{'price.id':priceID,'dic.id':dicID,'price.priceNum':priceNum},
		function(data){
			if(data.indexOf('success') > -1){
				var submitIndex = data.split(':')[1];
				var tr = '<tr>';
				tr = tr + '<td><span class="label label-info">'+priceName+'</span></td>';
				tr = tr + '<td>'+priceValue+'</td>';
				tr = tr + '<td>'+dicName+'&nbsp;&nbsp;</td>';
				tr = tr + '<td>'+priceNum+'</td>';
				tr = tr + '<td> <a class="btn btn-mini btn-danger" onclick=cancelPrice("'+submitIndex+'",this)>取消</a> </td>';
				$('#select-tbody').append(tr);
			}else if(data.indexOf('Timeout') > -1){
				$('#tipModal .modal-body > p').html('未能提交订餐，登录超时，请重新登陆！');
				$('#tipModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
			}else{
				$('#tipModal .modal-body > p').html('未能提交订餐，发生未知错误请联系管理员！');
				$('#tipModal').modal({
				    backdrop:true,
				    keyboard:true,
				    show:true
				});
			}
		},
	'text');
};

// ========== 订餐页面取消一行数据 ordering.jsp
var submitIndex = 1;
var cancelRow; // 取消行
var cancelIndex; // 取消索引
function cancelPrice(index,obj){
	cancelRow = obj;
	cancelIndex = index;
	// 弹出确认对话框
	$('#commitModal').modal({
	    backdrop:true,
	    keyboard:true,
	    show:true
	});
}

function commitCancel(){
	$('#commitModal').modal('hide');
	doCancelPrice();
}

// ========== 执行取消选择操作
function doCancelPrice(){
	// 异步取消选择的饭菜
	var toUrl = PATH + '/content/secure/ordering!cancelOrdering';
	$.post(toUrl,{'index':cancelIndex},
			function(data){
				if(data == 'success'){
					$(cancelRow).parent().parent().fadeIn();
					$(cancelRow).parent().parent().remove();
				} else if(data.indexOf('Timeout') > -1){
					$('#tipModal .modal-body > p').html('未能取消订餐，登录超时，请重新登陆！');
					$('#tipModal').modal({
					    backdrop:true,
					    keyboard:true,
					    show:true
					});
				} else {
					$('#tipModal .modal-body > p').html('未能取消订餐，发生未知错误请联系管理员！');
					$('#tipModal').modal({
					    backdrop:true,
					    keyboard:true,
					    show:true
					});
				}
			},
	'text');
}
