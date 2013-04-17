<%@page import="com.moodpo.domain.User"%>
<%@page import="com.moodpo.utils.OtherConstants"%>
<%@page import="com.moodpo.utils.CookieUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	if(session.getAttribute(OtherConstants.CURRENT_USER) != null){
		User user = (User)session.getAttribute(OtherConstants.CURRENT_USER);
		if(OtherConstants.ADMIN_GROUP_ID.equals(user.getAuth())){
			response.sendRedirect(path + "/content/manage/order!queryTodayOrder.do");
		}else{
			response.sendRedirect(path + "/content/secure/ordering!todayOrdering.do");
		}
	}else{
		boolean isCookie = CookieUtils.isHaveCookie(request);
		if(isCookie){
			response.sendRedirect(path + "/content/public/user!cookieLogin.do");
		}
	}
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>订餐系统 - 首页</title>
<link rel="shortcut icon" href="<%=path %>/favicon.ico" />
<link rel="stylesheet" href="<%=path %>/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/style/css/base.css" />
<script type="text/javascript" src="<%=path %>/common/modernizr.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- header =======================================================-->
		<header id="page-header">
			<div class="navbar navbar-fixed-top">
				<div class="navbar-inner">
					<!-- nav -->
					<div class="container">
						<a class="brand" href="<%=path %>">订餐系统</a>
						<ul class="nav">
							<li class="active"><a href="<%=path %>">首页</a></li>
							<li></li>
							<s:if test="#session.current_user_obj != null">
								<s:set name="user" value="#session.current_user_obj" />
								<li><a href="<%=path %>/content/secure/ordering!todayOrdering.do">订餐</a></li>
								<li><a href="<%=path %>/content/secure/order!queryOrder.do">订单</a></li>
								<s:if test="#user.auth > 1">
									<li><a href="<%=path %>/content/manage/dishes.jsp">菜品管理</a></li>
									<li><a href="<%=path %>/content/manage/ordering.jsp">订餐管理</a></li>
									<li><a href="<%=path %>/content/manage/order!queryAllOrder.do">订单管理</a></li>
									<li><a href="<%=path %>/content/manage/user.jsp">用户管理</a></li>
								</s:if>
							</s:if>
						</ul>
						<ul class="nav pull-right">
							<!-- 未登录 -->
							<s:if test="#session.current_user_obj == null">
								<li><a href="<%=path %>/content/public/login.jsp">登录</a></li>
								<li><a href="<%=path %>/content/public/sign.jsp">注册</a></li>
							</s:if>
							<!-- 已登录 -->
							<s:if test="#session.current_user_obj != null">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<s:property value="#user.loginName"/><b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="<%=path %>/content/secure/userInfo.jsp">修改个人信息</a></li>
										<li class="divider"></li>
										<li><a href="<%=path %>/content/public/user!logout.do">退出</a></li>
									</ul>
					            </li>
							</s:if>
							<li class="divider-vertical"></li>
							<li><a href="<%=path %>/content/public/help.jsp">帮助</a></li>
							<li><a href="mailto:yangxiaoxiehaha@gmail.com">问题反馈</a></li>
						</ul>
					</div>
					<!-- /nav -->
				</div>
			</div>
		</header>
		<!-- /header -->
		
		<!-- container =======================================================-->
		<div class="container" id="page-content">
			<div class="row">
				<div class="span12 main-show">
					<!-- show -->
					<div class="row">
						<!-- left img span -->
						<div class="span5">
							<div id="left-text">
								<h1>订餐系统</h1>
								<p>当今社会生活节奏越来越快，特别是企业或团体，项目时间紧，人员众多而繁杂时，为节省时间，企业或团体都会进行集体订餐。但由于工作繁忙无法抽出时间去统计每个人想要吃什么，吃那家的饭菜，更无心力去计算每次的详细费用及情况，因此就产生了快捷订餐的需求。</p>
								<p>本订餐系统使用Java和数据库技术，利用计算机超强的计算能力，通过导入每日菜单并订餐，对每次订餐进行统计汇总，并可输出订餐表；此外还可对订餐人员进行管理，以及账户充值等，最终实现快捷订餐功能。</p>
								<div><a class="btn btn-success btn-large"  href="<%=path %>/content/public/sign.jsp">立即注册</a> <a class="btn btn-large" href="<%=path %>/content/public/login.jsp">登录</a></div>
							</div>
						</div>
						<!-- /left img span -->
						<!-- right text span -->
						<div class="span7">
							<div id="right-img">
								<img alt="美食3" src="<%=path %>/style/img/03.jpg">
							</div>
						</div>
						<!-- /right text span -->
					</div>
					<hr class="soften">
					<!-- feature list -->
					<div class="row">
						<!-- 菜品管理 -->
						<div class="span4">
							<div class="feature-inner">
								<h3>方便的菜品管理</h3>
								<p>每个地区，每家店面的饭菜，甚至每天的食物都是不一样的，能够方便的导入菜品是订餐的第一步。本订餐系统不仅提供了方便的导入功能(包括菜品、单价以及详细内容等)，并能对导入的菜品进行有效的管理；此外还提供了每日菜单公告的功能，进行额外的公告提醒。</p>
								<div><a class="btn" href="<%=path %>/content/public/help.jsp">查看详情 &rarr;</a></div>
							</div>
						</div>
						<!-- 订单管理 -->
						<div class="span4">
							<div class="feature-inner">
								<h3>快捷的订餐方式</h3>
								<p>在每天的某个时刻，由有管理权限的人员激活订餐，注册者即可开始快捷订餐之旅。在订餐页面订餐者可方便的选取一份或多份饭菜，然后生成订单；也可在多个订单中生成多份饭菜。无论使用哪种方式，只要提交订单，订餐者所要的饭菜都会被最终统计。</p>
								<div><a class="btn" href="<%=path %>/content/public/help.jsp">查看详情 &rarr;</a></div>
							</div>
						</div>
						<!-- 用户管理 -->
						<div class="span4">
							<div class="feature-inner">
								<h3>实用的订单统计</h3>
								<p>订单的统计是本系统的核心功能。当管理者停止今日订餐后，可对今日订单进行统计，并能输出个性化的统计内容，例如今日订餐有什么多少份还有什么多少份等。此外还可对订单进行各种使用操作处理以及导出今日订单到word方便打印发给某餐馆或某供应商。</p>
								<div><a class="btn" href="<%=path %>/content/public/help.jsp">查看详情 &rarr;</a></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /container -->
		
		
		<!-- footer =======================================================-->
		<footer id="page-footer">
			<div>
			&copy; 2013. Create by <a title="Twitter Bootstrap" href="http://twitter.github.com/bootstrap/" target="_blank">Twitter Bootstrap</a> &amp;
			 <a title="心情订单" href="http://moodpo.com/" target="_blank">Moodpo</a>. All Rights Reserved. 
			</div>
		</footer>
	</div>
<!-- javascript
================================================================ -->
<script type="text/javascript" src="<%=path %>/common/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/common/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>