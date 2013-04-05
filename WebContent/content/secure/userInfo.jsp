<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>订餐系统 - 修改个人信息</title>
<link rel="shortcut icon" href="<%=path %>/favicon.ico" />
<link rel="stylesheet" href="<%=path %>/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/style/css/base.css" />
<script type="text/javascript" src="<%=path %>/common/modernizr.js"></script>
<script type="text/javascript">var PATH = '<%=path %>';</script>
</head>
<body>
	<s:set name="user" value="#session.current_user_obj" />
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
							<li><a href="<%=path %>/content/secure/ordering.jsp">订餐</a></li>
							<li><a href="<%=path %>/content/secure/order.jsp">订单</a></li>
							<s:if test="#user.auth > 1">
								<li><a href="<%=path %>/content/manage/dishes.jsp">菜品管理</a></li>
								<li><a href="<%=path %>/content/manage/ordering.jsp">订餐管理</a></li>
								<li><a href="<%=path %>/content/manage/order.jsp">订单管理</a></li>
								<li><a href="<%=path %>/content/manage/user.jsp">用户管理</a></li>
							</s:if>
						</ul>
						<ul class="nav pull-right">
							<!-- 已登录 -->
							<li class="dropdown active">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<s:property value="#user.loginName"/><b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="<%=path %>/content/secure/userInfo.jsp">修改个人信息</a></li>
									<li class="divider"></li>
									<li><a href="<%=path %>/content/public/user!logout">退出</a></li>
								</ul>
				            </li>
							<li class="divider-vertical"></li>
							<li><a href="<%=path %>/content/public/help.jsp">帮助</a></li>
							<li><a href="<%=path %>/content/public/feedback.jsp">问题反馈</a></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<!-- /header -->
		
		<!-- container =======================================================-->
		<div class="container" id="page-content">
			<div class="row">
				<!-- 用户信息 -->
				<div class="span9 main-show">
					<div class="main-inner">
						<div class="legend">修改个人信息</div>
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<s:if test="infoFlag == null || infoFlag == 0">
									<li class="active"><a href="#baseinfo-div" data-toggle="tab">基本信息</a></li>
									<li><a href="#alterpwd-div" data-toggle="tab">修改密码</a></li>
								</s:if>
								<s:if test="infoFlag == 1">
									<li><a href="#baseinfo-div" data-toggle="tab">基本信息</a></li>
									<li class="active"><a href="#alterpwd-div" data-toggle="tab">修改密码</a></li>
								</s:if>
								
							</ul>
							<div class="tab-content">
							
								<s:if test='infoFlag == null || infoFlag == "0"'>
								<div class="tab-pane fade active in" id="baseinfo-div">
									<s:form action="user!alterName" cssClass="form-horizontal" id="alertName-form" method="post">
										<s:hidden name="user.id" value="%{#user.id}"/>
										<fieldset>
											<div class="control-group">
												<label class="control-label" for="username">中文名：</label>
												<div class="controls">
													<s:textfield name="user.loginName" value="%{#user.loginName}" cssClass="input-medium" id="username"/>
													<span class="help-inline" id="username-text"><s:property value="msg"/></span>
												</div>
											</div>
											<div class="form-actions">
									        	<button type="button" class="btn btn-success alterName">修改</button>
									        </div>
										</fieldset>
									</s:form>
								</div>
								<div class="tab-pane fade in" id="alterpwd-div">
									<s:form action="user!alterPwd" cssClass="form-horizontal" id="alertPwd-form" method="post">
										<s:hidden name="user.id" value="%{#user.id}"/>
										<fieldset>
											<div class="control-group">
												<label class="control-label" for="oldpwd">旧密码：</label>
												<div class="controls">
													<s:password name="user.userPWD" cssClass="input-medium" id="oldpwd"/>
													<span class="help-inline" id="oldpwd-text">密码是3到12位的字母、数字或下划线。</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="newpwd">新密码：</label>
												<div class="controls">
													<s:password name="newPwd" cssClass="input-medium" id="newpwd"/>
													<span class="help-inline" id="newpwd-text">请输入新的3到12位密码。</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="repwd">确认新密码：</label>
												<div class="controls">
													<input type="password" class="input-medium" id="repwd">
													<span class="help-inline" id="repwd-text">&nbsp;</span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="valicode">验证码</label>
												<div class="controls">
													<s:textfield name="validateCode" id="valicode" cssClass="input-small"/>
													<img title="点击更换验证码" class="valicodeimg" src="<%=path %>/Kaptcha.jpg" width="100">
													<span class="help-inline" id="code-text"><s:property value="msgExt"/></span>
												</div>
											</div>
											<div class="form-actions">
									        	<button type="button" class="btn btn-success alertPwd">修改</button>
									        </div>
										</fieldset>
									</s:form>
								</div>
								</s:if>
								
								<s:if test='infoFlag == "1"'>
								<div class="tab-pane fade in" id="baseinfo-div">
									<s:form action="user!alterName" cssClass="form-horizontal" id="alertName-form" method="post">
										<s:hidden name="user.id" value="%{#user.id}"/>
										<fieldset>
											<div class="control-group">
												<label class="control-label" for="username">中文名：</label>
												<div class="controls">
													<s:textfield name="user.loginName" value="%{#user.loginName}" cssClass="input-medium" id="username"/>
													<span class="help-inline" id="username-text"><s:property value="msg"/></span>
												</div>
											</div>
											<div class="form-actions">
									        	<button type="button" class="btn btn-success alterName">修改</button>
									        </div>
										</fieldset>
									</s:form>
								</div>
								<div class="tab-pane fade active in" id="alterpwd-div">
									<s:form action="user!alterPwd" cssClass="form-horizontal" id="alertPwd-form" method="post">
										<s:hidden name="user.id" value="%{#user.id}"/>
										<fieldset>
											<div class="control-group">
												<label class="control-label" for="oldpwd">旧密码：</label>
												<div class="controls">
													<s:password name="user.userPWD" cssClass="input-medium" id="oldpwd"/>
													<span class="help-inline" id="oldpwd-text"><s:property value="errors.oldpwd[0]"/></span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="newpwd">新密码：</label>
												<div class="controls">
													<s:password name="newPwd" cssClass="input-medium" id="newpwd"/>
													<span class="help-inline" id="newpwd-text"></span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="repwd">确认新密码：</label>
												<div class="controls">
													<input type="password" class="input-medium" id="repwd">
													<span class="help-inline" id="repwd-text"></span>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label" for="valicode">验证码</label>
												<div class="controls">
													<s:textfield name="validateCode" id="valicode" cssClass="input-small"/>
													<img title="点击更换验证码" class="valicodeimg" src="<%=path %>/Kaptcha.jpg" width="100">
													<span class="help-inline" id="code-text"><s:property value="errors.validate_code[0]"/></span>
												</div>
											</div>
											<div class="form-actions">
									        	<button type="button" class="btn btn-success alertPwd">修改</button>
									        </div>
										</fieldset>
									</s:form>
								</div>
								</s:if>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 提示信息 -->
				<div class="span3">
					<div class="well notify-info">
						<p>提供您详细的信息可以让社区其他用户更好的了解你。您的姓名和联系电话仅仅用于我们和您的联系，不会泄露给任何第三方。</p>
					</div>
				</div>
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