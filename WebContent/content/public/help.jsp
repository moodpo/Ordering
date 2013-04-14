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
<title>订餐系统 - 帮助</title>
<link rel="shortcut icon" href="<%=path %>/favicon.ico" />
<link rel="stylesheet" href="<%=path %>/common/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=path %>/style/css/base.css" />
<script type="text/javascript" src="<%=path %>/common/modernizr.js"></script>
<style>
.nav-list .nav-header{
	font-size: 14px;
}

.nav-list li a{
	font-size: 12px;
}

.pimg{
	text-align: center;
}

.pimg img{
	height: auto;
	max-width: 90%;
	vertical-align: middle;
	-ms-interpolation-mode: bicubic;
	box-shadow: 0 0 10px gray;
	margin: 20px auto;
	display: block;
}

</style>
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
							<li><a href="<%=path %>">首页</a></li>
							<li></li>
							<s:if test="#session.current_user_obj != null">
								<s:set name="user" value="#session.current_user_obj" />
								<li><a href="<%=path %>/content/secure/ordering!todayOrdering.do">订餐</a></li>
								<li><a href="<%=path %>/content/secure/order!queryOrder.do">订单</a></li>
								<s:if test="#user.auth > 1">
									<li><a href="<%=path %>/content/manage/dishes.jsp">菜品管理</a></li>
									<li><a href="<%=path %>/content/manage/ordering.jsp">订餐管理</a></li>
									<li><a href="<%=path %>/content/manage/order.jsp">订单管理</a></li>
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
							<li class="active"><a href="<%=path %>/content/public/help.jsp">帮助</a></li>
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
				<!-- 内容 -->
				<div class="span9 main-show">
					<div class="main-inner">
						<div class="legend">订餐系统帮助和说明</div>
						<h3>关于系统</h3>
						<p>订餐系统是一个帮助团队或者公司订餐的工具。对于订餐的整个流程来说，我们只关注中间的部分，即员工订餐到统计的过程。我们的目标是实现快速订餐并统计结果以发给供应商打包送餐，当然也包括一些基本的对菜品的导入和订单查询。</p>
						<p><strong>具体使用场景：</strong><span style="color:#f00;">使用场景决定了需求，如果你所在的场景不是如下描述，请谨慎使用本系统。</span></p>
						<ol>
							<li>供应商提供菜单</li>
							<li>负责人将菜单导入订餐系统</li>
							<li>负责人开启订餐</li>
							<li>成员注册系统</li>
							<li>成员开始订餐并生成订单</li>
							<li>负责人统计今日订单</li>
							<li>负责人将订单导出发给供应商</li>
						</ol>
						<h3>开始使用</h3>
						<h4>注册订餐系统</h4>
						<p>只有注册用户才能有使用本系统的权限，所以如果您想使用本系统，请务必注册。中文名称有助于订单导出时确认到哪个人，因此请尽量输入正确名字。 </p>
						<p class="pimg"><img src="<%=path %>/style/img/help/sign.png" /></p>
						<p>注册后系统将发送一串随机的密码到您的注册邮箱，可使用此密码及邮箱进行登录。如果您没有收到密码请确认注册邮箱的有效性或者使用问题反馈。</p>
						<h4>登录/退出系统</h4>
						<p>如果您是首次登录，请从注册邮箱中取出密码；在登录页面使用密码及注册邮箱进行登录。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/login.png" /></p>
						<p>如果在登录时您勾选了“下次自动登录”，在下次进入系统时将会自动登录。默认自动登录的有效时间是一周，此时间内除非您进行了退出操作，否则系统一直会自动登录。</p>
						<p>登录系统后可使用退出菜单退出系统，同时系统也会取消“下次自动登录”功能。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/logout.png" /></p>
						<h4>密码找回</h4>
						<p>在登录页面，如果您忘记了密码，可点击“忘记密码”链接跳转到密码找回页面，使用注册时的邮箱，系统将重置您的密码并将密码发送给您。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/findPwd.png" /></p>
						<h3>帐号设置</h3>
						<h4>密码修改</h4>
						<p>在修改个人信息页面的修改密码标签页下，可填写旧密码和新密码进行密码修改。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/alterPwd.png" /></p>
						<h4>中文名的修改</h4>
						<p>在修改个人信息页面的基本信息标签页下，可修改您的中文名称。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/alterInfo.png" /></p>
						<h3>订餐功能</h3>
						<h4>选择菜品</h4>
						<p>在订餐页面，您将看到今日订餐公告以及菜品列表。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/todayOrdering.png" /></p>
						<p>在菜品列表中，您可以选择想要的饭菜并通过点击提交按钮来选择，以准备生成订单。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/orderingList.png" /></p>
						<p>已经提交的选择将会显示在下面的已选菜品列表中，其中您也可以取消已经选择的菜品。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/orderingSelect.png" /></p>
						<h4>生成订单</h4>
						<p>当您选择完菜品后，点击页面最下方的生成订单按钮就可以生成订单。生成订单成功后将自动跳转到订单页面，此页面分页显示了所有的订单信息。</p>
						<p class="pimg"><img src="<%=path %>/style/img/help/order.png" /></p>
						
						
						<!-- <p>不同的供应商可能提供的菜单不尽相同，但是都可以通过变通的方式去使用导入功能。例如下面的菜单是使用系统最佳的，它通过价格和类型对菜单进行了分类，每种分类中又包含了多种具体的菜品：</p>
						<ul>
							<li>6元套餐：干炸鲳鱼/西红柿炒鸡蛋/香菜豆腐皮/炒藕片</li>
							<li>8元套餐：辣子肉片/干炸鲳鱼/西红柿炒鸡蛋/超藕片</li>
							<li>10元套餐：红烧鸡翅/辣子肉片/干炸鲳鱼/西红柿炒鸡蛋/炒藕片</li>
							<li>4元盖浇饭：土豆丝/卤豆腐</li>
							<li>5元盖浇饭：西红柿鸡蛋/红烧狮子头</li>
							<li>6元盖浇饭：红烧茄子/辣子鸡/宫保鸡丁/木须肉/鱼香肉丝/酱鸡腿/雪菜红烧肉/回锅肉</li>
							<li>7元盖浇饭：红烧鲅鱼</li>
							<li>8元盖浇饭：酱鸡翅</li>
						</ul>
						<p>在系统中的呈现方式将是如下：</p>
						
						<p>当然，如果没有按照价格和类型分类，系统也能轻松应对。例如小豆面馆推出的菜单可只通过价格来分类导入：</p> -->
					</div>
				</div>
				<!-- 内容导航 -->
				<div class="span3">
					<div class="well" style="padding: 8px 0;">
						<ul class="nav nav-list">
							<li class="active"><a href="#" style="font-size:14px;">关于系统</a></li>
							<li class="divider"></li>
							<li class="nav-header">开始使用</li>
							<li><a href="#">注册订餐系统</a></li>
							<li><a href="#">登录/退出系统</a></li>
							<li><a href="#">密码找回</a></li>
							<li class="divider"></li>
							<li class="nav-header">帐号设置</li>
							<li><a href="#">密码修改</a></li>
							<li><a href="#">中文名的修改</a></li>
						    <li class="divider"></li>
						    <li class="nav-header">订餐功能</li>
							<li><a href="#">选择菜品</a></li>
							<li><a href="#">生成订单</a></li>
						    <li><a href="#">查找订单</a></li>
						    <li class="divider"></li>
						    <li class="nav-header">菜品管理</li>
						    <li><a href="#">添加今日菜品公告</a></li>
						    <li><a href="#">添加类型及单价</a></li>
						    <li><a href="#">添加菜品或主食</a></li>
						    <li><a href="#">菜品的导入</a></li>
						    <li class="divider"></li>
						    <li class="nav-header">订餐管理</li>
						    <li><a href="#">开始和停止订餐</a></li>
						    <li class="divider"></li>
						    <li class="nav-header">订单管理</li>
						    <li><a href="#">今日订单</a></li>
						    <li><a href="#">生成word文档</a></li>
						    <li><a href="#">历史订单</a></li>
						    <li class="divider"></li>
						    <li class="nav-header">用户管理</li>
						    <li><a href="#">用户查询</a></li>
						    <li><a href="#">用户设置</a></li>
						    <li><a href="#">用户充值</a></li>
						</ul>
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