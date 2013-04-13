<%@page import="com.moodpo.utils.CookieUtils"%>
<%@page import="com.moodpo.core.SysConfig"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String mailSuffix = SysConfig.getConfig(SysConfig.EMAIL_SUFFIX);
	boolean isCookie = CookieUtils.isHaveCookie(request);
	if(isCookie){
		response.sendRedirect(path + "/content/public/user!cookieLogin");
	}
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>订餐系统 - 找回密码</title>
<link rel="shortcut icon" href="<%=path %>/favicon.ico" />
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
						</ul>
						<ul class="nav pull-right">
							<!-- 未登录 -->
							<li class="active"><a href="<%=path %>/content/public/login.jsp">登录</a></li>
							<li><a href="<%=path %>/content/public/sign.jsp">注册</a></li>
							<li class="divider-vertical"></li>
							<li><a href="<%=path %>/content/public/help.jsp">帮助</a></li>
							<li><a href="mailto:yangxiaoxiehaha@gmail.com">问题反馈</a></li>
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
				<div class="span6 main-show" id="login-span">
					<div class="box-top">
					    <h3>找回密码</h3>
				  	</div>
					<div class="box-body">
						<s:form action="user!findPwd.do" id="findPwd-form" method="post" cssClass="form-horizontal">	
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="usermail">公司邮箱：</label>
									<div class="controls">
										<div class="input-append">
											<s:textfield name="user.email" cssClass="input-medium" id="usermail" /><span class="add-on"><%=mailSuffix %></span>
	              						</div>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="valicode">验证码：</label>
									<div class="controls">
										<s:textfield name="validateCode" id="valicode" cssClass="input-small"/>
										<img title="点击更换验证码" class="valicodeimg" src="<%=path %>/Kaptcha.jpg" width="100">
									</div>
								</div>
								<s:if test="msg != null">
									<div class="alert">
										<a class="close">&times;</a>
										<span class="alert-text"><strong>警告！</strong> <s:property value="msg"/></span>
									</div>
								</s:if>
								<s:if test="msg == null">
									<div class="alert">
										<a class="close">&times;</a>
										<span class="alert-text"><strong>提示！</strong> 请输入您注册的邮箱地址，系统将对其密码进行重置并将密码发送到此邮箱。</span>
									</div>
								</s:if>
								<div class="form-actions">
						        	<button type="button" class="btn btn-success btn-large submitFindPwd">找回密码</button>
						        </div>
							</fieldset>
						</s:form>
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