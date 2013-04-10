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
<title>订餐系统 - 订单</title>
<link rel="shortcut icon" href="<%=path %>/favicon.ico" />
<link rel="stylesheet" href="<%=path %>/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/style/css/base.css" />
<script type="text/javascript" src="<%=path %>/common/modernizr.js"></script>
<script type="text/javascript">var PATH = '<%=path %>';</script>
</head>
<body>
	<s:set name="user" value="#session.current_user_obj" />
	<s:action name="order!queryOrder" executeResult="false"/>
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
							<li class="active"><a href="<%=path %>/content/secure/order.jsp">订单</a></li>
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
				<!-- 订单列表及详细订单 -->
				<div class="span9 main-show">
					<div class="main-inner">
						<div class="legend">订单列表</div>
						<!-- 订单列表 -->
						<table class="table table-striped table-bordered">
							<thead>
								<tr>
									<th width="40px">用户名</th>
									<th width="50px">金额(元)</th>
									<th width="80px">时间</th>
									<th width="50px">状态</th>
									<th>详细信息</th>
									<th width="70px">操作</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.current_order_list" status="itStatus">
									<tr>
										<td width="40px"><s:property value="#user.loginName"/></td>
										<td><code><s:property value="orderTotle"/></code></td>
										<td width="80px"><s:property value="orderDate"/></td>
										<td width="50px">
											<s:if test="orderState == '01'">
												未结帐
											</s:if>
											<s:if test="orderState == '00'">
												已结帐
											</s:if>
										</td>
										<td>
											<s:iterator value="details">
											    <div style="padding: 1px; line-height: 20px; font-size: 12px;">
												    <code><s:property value="orderDetailNum"/></code> 份 <code><s:property value="price.priceValue"/></code> 元
													<s:property value="price.priceName"/>（<s:property value="price.dicName"/>）; 
												</div>
											</s:iterator>
										</td>
										<td width="70px"> <!-- <a class="btn btn-mini btn-success">付款</a> --> <a class="btn btn-mini btn-danger">取消订单</a> </td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<!-- pagination -->
						<div class="well mypagination">
					    	<ul>
					    		<li class="page-first"><a href="#">首页</a></li>
					    		<li class="page-prev"><a href="#">上一页</a></li>
					    		<li>
					    			<span> 第 </span> <input type="text" class="small-span" value="1"> <span> 页 </span>
					    		</li>
					    		<li class="page-next"><a href="#">下一页</a></li>
					    		<li class="page-last"><a href="#">尾页</a></li>
					    		<li></li>
					    		<li>
					    			<span> 每页显示 </span>
					    			<select class="span1">
					    				<option>10</option>
					    				<option>20</option>
					    				<option>50</option>
					    			</select>
					    			<span> 条 </span>
					    		</li>
					    	</ul>
					    	<div class="mypageinfo">第 1页 1 - 8条, 共 1页 8条数据</div>
						</div>
						<!-- /pagination -->
					</div>
				</div>
				<!-- 提示信息 -->
				<div class="span3">
					<div class="well notify-info">
						<p></p>
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