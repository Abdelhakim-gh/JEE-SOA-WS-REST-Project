<%@page import="models.User"%>
<%@page import="models.Prof"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav
	class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
	<div class="container-fluid">
		<button class="btn btn-link d-md-none rounded-circle me-3"
			id="sidebarToggleTop" type="button">
			<i class="fas fa-bars"></i>
		</button>
		<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		if (session.getAttribute("prof") == null) {
			response.sendRedirect("login.jsp");
			return; // Add this line to exit the JSP

		}
		Prof prof = (Prof) session.getAttribute("prof");
		%>

		<ul class="navbar-nav flex-nowrap ms-auto">
			<div class="d-none d-sm-block topbar-divider"></div>
			<li class="nav-item dropdown no-arrow">
				<div class="nav-item dropdown no-arrow">
					<a class="dropdown-toggle nav-link text-capitalize fw-bold" aria-expanded="false"
						data-bs-toggle="dropdown" href="#"><span
						class="d-none d-lg-inline me-2 text-gray-600 small"><%=prof.getUser().getNom()%>
							<%=prof.getUser().getPrenom()%></span><img
						class="border rounded-circle img-profile"
						src="assets/img/avatars/user.png"></a>
					<div
						class="dropdown-menu shadow dropdown-menu-end animated--grow-in">
						<a class="dropdown-item" href="prof_profile.jsp"><i
							class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Profile</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="Logout"><i
							class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>&nbsp;Logout</a>
					</div>
				</div>
			</li>
		</ul>
	</div>
</nav>