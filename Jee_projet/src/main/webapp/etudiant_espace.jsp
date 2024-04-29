
<%@page import="models.Etudiant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Espace d'Etudiant</title>

<jsp:include page="_head.html"></jsp:include>
</head>

<body id="page-top">
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	if (session.getAttribute("etudiant") == null) {
		response.sendRedirect("login.jsp");
		return; // Add this line to exit the JSP

	}
	Etudiant etudiant = (Etudiant) session.getAttribute("etudiant");
	%>
	<div id="wrapper">

		<jsp:include page="etudiant_sidebar.html"></jsp:include>

		<div class="d-flex flex-column" id="content-wrapper">

			<div id="content">

				<jsp:include page="etudiant_header.jsp"></jsp:include>

				<div class="container-fluid">

					<div
						class="d-sm-flex justify-content-between align-items-center mb-4">
						<h3 class="text-dark mb-0">
							Tableau de bord<br>
						</h3>
					</div>
					<div class="row">

						<div class="col mb-4">
							<div
								class="card shadow border-start-warning py-2 border-left-primary">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col me-2">
											<div class="text-uppercase text-warning fw-bold text-xs mb-1"
												style="color: var(--bs-pink);">
												<span class="text-primary"><strong>CLASSE</strong><br></span>
											</div>
											<div class="text-dark fw-bold h5 mb-0">
												<span><%=etudiant.getSection().getLabel() %>/<%=etudiant.getClasse().getLable() %></span>
											</div>
										</div>
										<div class="col-auto">
											<a href="etudiant_info.jsp"><i
												class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col mb-4">
							<div
								class="card shadow border-start-primary py-2 border-left-danger">
								<div class="card-body">
									<div class="row align-items-center no-gutters">
										<div class="col me-2">
											<div class="text-uppercase text-danger fw-bold text-xs mb-1">
												<span><strong>statut</strong><br></span>
											</div>
											<div class="text-dark fw-bold h5 mb-0">
												<%
												if (etudiant.getPayement() == 0) {
												%>
												<span class="badge bg-danger">Non Payé</span>
												<%
												} else {
												%>
												<span class="badge bg-success">Payé</span>
												<%
												}
												%>
											</div>
										</div>
										<div class="col-auto">
											<a href="etudiant_info.jsp"><i
												class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
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