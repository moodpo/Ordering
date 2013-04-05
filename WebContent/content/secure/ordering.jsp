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
<title>订餐系统 - 订餐</title>
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
							<li class="active"><a href="<%=path %>/content/secure/ordering.jsp">订餐</a></li>
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
							<li class="dropdown">
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
				<!-- 菜品列表 -->
				<div class="span9 main-show">
					<div class="main-inner">
						<div class="legend">菜品选择</div>
						<!-- 菜品列表 -->
						<!-- 初始化菜品列表 -->
						<s:action name="ordering!todayOrdering" executeResult="true"/>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="100px">类型</th>
									<th width="50px">单价(元)</th>
									<th>饭菜详细信息</th>
									<th width="50px">份数</th>
									<th width="50px">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.today_ordering_list">
									<tr>
										<td><s:property value="priceName"/></td>
										<td><s:property value="priceValue"/></td>
										<td><s:radio list="dics" listKey="id" listValue="dicName" name="%{id}"/></td>
										<td>
											<s:select list="#request.num_list" name="priceNum" cssClass="span1"/>
	              						</td>
										<td> <a class="btn btn-mini btn-success submitPrice">提交</a> </td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						
						<div class="legend">已选菜品</div>
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="100px">类型</th>
									<th width="50px">单价(元)</th>
									<th>饭菜详细信息</th>
									<th width="50px">份数</th>
									<th width="50px">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.selected_today_ordering_list">
									<tr>
										<td><s:property value="priceName"/></td>
										<td><s:property value="priceValue"/></td>
										<td>
											<s:iterator value="dics">
												<s:property value="dicName"/>&nbsp;&nbsp;	
											</s:iterator>
										</td>
										<td><s:property value="priceNum"/></td>
										<td> <a class="btn btn-mini btn-success cancelPrice">取消</a> </td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 提示信息 -->
				<div class="span3">
					<div class="well notify-info">
						<p><s:property value="#request.today_ordering_info"/></p>
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