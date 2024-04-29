
<%@page import="models.Matiere"%>
<%@page import="models.Prof"%>
<%@page import="models.dao.ProfDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Prof info</title>

<jsp:include page="_head.html"></jsp:include>
</head>

<body id="page-top">
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
		return; // Add this line to exit the JSP
	}
	%>
	<div id="wrapper">

		<jsp:include page="admin_sidebar.html"></jsp:include>

		<div class="d-flex flex-column" id="content-wrapper">

			<div id="content">

				<jsp:include page="admin_header.jsp"></jsp:include>

				<div class="container-fluid">
					<h3 class="text-dark mb-4">
						Professeurs<br>
					</h3>
					<div class="row mb-3">
						<div class="col-lg-4">
							<div class="card mb-3">
								<div class="card-body text-center shadow">
									<img class="rounded-circle mb-3 mt-4"
										src="assets/img/avatars/user.png" width="160" height="160">
									<div class="mb-3">

										<button class="btn btn-primary btn-sm" type="submit">Change
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
											<p class="text-primary m-0 fw-bold">Prof info</p>
										</div>
										<%
										ProfDAO profDAO = new ProfDAO();
										int id = Integer.parseInt(request.getParameter("id_prof"));
										Prof prof = profDAO.getProf(id);
										%>
										<div class="card-body">
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Nom</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getUser().getNom()%>
														</strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Prenom</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getUser().getPrenom()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Tel</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getUser().getTel()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Email</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getUser().getEmail()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Password</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getUser().getPassword()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Statut</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"> <%
 if (prof.getPayement() == 0) {
 %> <span class="badge bg-danger">Non Payé</span> <%
 } else {
 %> <span class="badge bg-success">Payé</span> <%
 }
 %>
														</label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Salaire
																mensuel</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=prof.getSalaire()%>
																DH</strong></label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col">
							<div class="card shadow mb-3">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">
										Matières enseigne<br>
									</p>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col">
											<div class="bg-white ms-4 collapse-inner rounded">
												<div class="table-responsive  table mt-2" id="dataTable-1"
													role="grid" aria-describedby="dataTable_info">
													<table class="table my-0" id="dataTable">
														<thead>
															<tr>
																<th class="text-capitalize ">label</th>
																<th class="text-capitalize ">Section<br></th>
															</tr>
														</thead>
														<tbody>
															<%
															for (Matiere item : prof.getMatieres()) {
															%>
															<tr>
																<td><%=item.getLable()%></td>
																<td><%=item.getSection().getLabel()%></td>
															</tr>
															<%
															}
															%>
														</tbody>
													</table>
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
		</div>
	</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>