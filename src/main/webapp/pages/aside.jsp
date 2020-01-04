<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user6-128x128.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>	<security:authentication property="principal.username"></security:authentication></p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>个人业务</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/user/money.do"> <i
							class="fa fa-circle-o"></i> 查询余额
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/pages/add-money.jsp"> <i
							class="fa fa-circle-o"></i> 存款
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/pages/take-money.jsp">
							<i class="fa fa-circle-o"></i> 取款
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/pages/transer.jsp"> <i
							class="fa fa-circle-o"></i> 转账
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>操作记录</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/sysLog/findAll.do">
							<i class="fa fa-circle-o"></i>记录详情
					</a></li>
				</ul></li>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>