
<%@page import="models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Profile</title>

<jsp:include page="_head.html"></jsp:include>
</head>

<body id="page-top">
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return; // Add this line to exit the JSP

	}
	User user = (User) session.getAttribute("user");
	%>
	<div id="wrapper">

		<jsp:include page="admin_sidebar.html"></jsp:include>

		<div class="d-flex flex-column" id="content-wrapper">

			<div id="content">

				<jsp:include page="admin_header.jsp"></jsp:include>

				<div class="container-fluid">

					<div class="d-flex justify-content-between align-content-center align-items-center">
						<h3 class="text-dark mb-4">Profile</h3>
						<%
						Object update_profile_error = request.getAttribute("update_profile_error");
						if (update_profile_error != null) {
						%>
						<p class="text-danger text-center"><%=update_profile_error.toString()%></p>
						<%
						}
						%>
					</div>
					<div class="row mb-3">

						<div class="col-lg-4">
							<div class="card mb-3">
								<div class="card-body text-center shadow">
									<img class="rounded-circle mb-3 mt-4"
										src="assets/img/avatars/user.png" width="160" height="160">
									<div class="mb-3">
										<button class="btn btn-primary btn-sm" type="button">Change
											Photo</button>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-8">

							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">User Settings</p>
										</div>
										<div class="card-body">
											<form action="UpdateProfile?id_user=<%=user.getId_user()%>&role=<%=user.getRole()%>" method="post">
												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="first_name"><strong>Prenom</strong></label>
															<input class="form-control" type="text" id="first_name"
																value="<%=user.getPrenom()%>" placeholder="var_value"
																name="prenom">
														</div>
													</div>
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="last_name"><strong>Nom</strong></label>
															<input class="form-control" type="text" id="last_name"
																value="<%=user.getNom()%>" placeholder="var_value"
																name="nom">
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="tel"><strong>Tel</strong></label>
															<input class="form-control" type="text" id="tel"
																value="<%=user.getTel()%>" placeholder="var_value"
																name="tel">
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="email"><strong>Email</strong></label>
															<input class="form-control" type="email" id="email"
																value="<%=user.getEmail()%>"
																placeholder="user@example.com" name="email">
														</div>
													</div>
													<div class="col">
														<div class="mb-3">
															<label class="form-label" for="pass"><strong>Password</strong></label>
															<input class="form-control" type="password" id="pass"
																value="<%=user.getPassword()%>"
																placeholder="type if u want to change" name="password">
														</div>
													</div>
												</div>
												<div class="mb-3">
													<div
														class="d-flex justify-content-between align-items-center">
														<button type="submit" class="btn btn-primary"
															>Soumettre</button>
														<button type="reset"
															class="btn btn-block btn-outline-primary">Remmettre</button>
													</div>
												</div>
											</form>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>

			</div>

		</div>
	</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>