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
										<td width="70px"> <!-- <a class="btn btn-mini btn-success">付款</a> --> 
											<s:if test="orderState == '01'">
												<s:form action="order!cancelOrder" method="post" style="margin:0;padding:0;">
													<s:hidden value="%{id}" name="order.id"></s:hidden>
													<a class="btn btn-mini btn-danger cancelOrder">取消订单</a>
												</s:form>
											</s:if>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<s:form action="order!queryOrder" method="post" id="queryOrder-form">
						<!-- pagination -->
						<div class="well mypagination">
							<s:if test="#request.pageInfo != null">
								<s:push value="#request.pageInfo">
								<s:hidden value="%{pageCount}" cssClass="pageCount"></s:hidden>
						    	<ul>
						    		<s:if test="currentPage > 1">
						    			<li class="page-first"><a href="javascript:void(0)">首页</a></li>
						    			<li class="page-prev"><a href="javascript:void(0)">上一页</a></li>
						    		</s:if>
						    		<s:else>
						    			<li class="page-disable">首页</li>
						    			<li class="page-disable">上一页</li>
						    		</s:else>
						    		<li>
						    			<span> 第 </span> <s:textfield cssClass="small-span currentPage" name="pagination.currentPage" value="%{currentPage}"/> <span> 页 </span>
						    		</li>
						    		<s:if test="currentPage < pageCount">
						    			<li class="page-next"><a href="javascript:void(0)">下一页</a></li>
						    			<li class="page-last"><a href="javascript:void(0)">尾页</a></li>
						    		</s:if>
						    		<s:else>
						    			<li class="page-disable">下一页</li>
						    			<li class="page-disable">尾页</li>
						    		</s:else>
						    		<li></li>
						    		<li>
						    			<span> 每页显示 </span>
						    			<s:select list="{'5','10','20','50'}" name="pagination.pageSize" cssClass="span1 page-size" value="%{pageSize}"/>
						    			<span> 条 </span>
						    		</li>
						    	</ul>
					    		<div class="mypageinfo">第 <s:property value="currentPage"/>页 <s:property value="startRow"/>
					    		 - <s:property value="endRow"/>条, 共 <s:property value="pageCount"/>页 <s:property value="count"/>条数据</div>
					    		</s:push>
					    	</s:if>
						</div>
						<!-- /pagination -->
						</s:form>
						<!-- 弹出提示 -->
						<div id="tipModal" class="modal hide">
				        	<div class="modal-header">
				            	<a class="close" data-dismiss="modal">×</a>
				              	<h3>提示</h3>
				            </div>
				            <div class="modal-body">
				              	<p></p>
				            </div>
				            <div class="modal-footer">
				              	<a class="btn" data-dismiss="modal">关闭</a>
				            </div>
				    	</div>
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