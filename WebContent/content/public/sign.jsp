<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>订餐系统 - 登录</title>
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" href="<%=path %>/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/style/css/base.css" />
<script type="text/javascript" src="<%=path %>/common/modernizr.js"></script>
<script type="text/javascript">var PATH = '<%=path %>';</script>
</head>
<body>
	<div id="wrapper">
		<!-- header =======================================================-->
		<header id="page-header">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<div class="container">
						<a class="brand" href="<%=path %>">订餐系统</a>
						<ul class="nav">
							<li><a href="<%=path %>">首页</a></li>
							<li></li>
							<li><a href="ordering.html">订餐</a></li>
							<li><a href="order.html">订单</a></li>
						</ul>
						<ul class="nav pull-right">
							<!-- 未登录 -->
							<li><a href="login.html">登录</a></li>
							<li class="active"><a href="sign.html">注册</a></li>
							<li class="divider-vertical"></li>
							<li><a href="help.html">帮助</a></li>
							<li><a href="feedback.html">问题反馈</a></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<!-- /header -->
		
		<!-- container =======================================================-->
		<div class="container" id="page-content">
			<div class="row" style="margin: 60px auto;">
				<div class="span3">&nbsp;</div>
				<!-- 注册 -->
				<div class="span6 main-show" id="sign-span">
					<div class="box-top">
					    <h3>用户注册</h3>
				  	</div>
					<div class="box-body">
						<form class="form-horizontal" action="user!sign" method="post">
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="usermail">公司邮箱：</label>
									<div class="controls">
										<div class="input-append">
											<input name="user.email" type="text" class="input-medium" id="usermail"><span class="add-on">@ultrapower.com.cn</span>
	              						</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="userpwd">密码：</label>
									<div class="controls">
										<input name="user.userPWD" type="password" class="input-medium" id="userpwd">
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="valicode">验证码：</label>
									<div class="controls">
										<input name="valicode" id="valicode" class="input-small" type="text">
										<img title="点击更换验证码" class="valicodeimg" src="<%=path %>/Kaptcha.jpg" width="100">
									</div>
								</div>
								<!-- <div class="alert fade in">
									<a class="close" data-dismiss="alert" href="#">×</a>
									<strong>警告！</strong> 您输入的邮箱地址不正确。
								</div> -->
								<div class="form-actions">
						        	<button type="button" class="btn btn-success btn-large" onclick="checkSign()">注册</button>
						        </div>
							</fieldset>
						</form>
					</div>
					<div class="box-bottom">
					</div>
				</div>
				<div class="span3">&nbsp;</div>
			</div>
		</div>
		<!-- /container -->
		
		
		<!-- footer =======================================================-->
		<footer id="page-footer">
			<div>
			&copy; 2013. Create by <a title="Twitter Bootstrap" href="http://twitter.github.com/bootstrap/">Twitter Bootstrap</a> &amp;
			 <a title="心情订单" href="http://moodpo.com/">Moodpo</a>. All Rights Reserved. 
			</div>
		</footer>
	</div>
<!-- javascript
	================================================================ -->
<script type="text/javascript" src="<%=path %>/common/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/common/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/script/base.js"></script>
</body>
</html>