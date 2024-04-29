
<%@page import="models.User"%>
<%@page import="models.dao.ClasseDAO"%>
<%@page import="models.dao.SectionDAO"%>
<%@page import="controlers.section.Sections"%>
<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.dao.ProfDAO"%>
<%@page import="models.dao.EtudiantDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Espace</title>

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

					<div
						class="d-sm-flex justify-content-between align-items-center mb-4">
						<h3 class="text-dark mb-0">
							Tableau de bord<br>
						</h3>
					</div>
					<%
					EtudiantDAO etudiantDAO = new EtudiantDAO();
					ProfDAO profDAO = new ProfDAO();
					MatiereDAO matiereDAO = new MatiereDAO();
					SectionDAO sectionDAO = new SectionDAO();
					ClasseDAO classeDAO = new ClasseDAO();
					%>
					<div class="row">
						<div class="col">
							<div class="row">
								<div class="col-md-6 mb-4">
									<div
										class="card shadow border-start-primary py-2 border-left-primary">
										<div class="card-body">
											<div class="row align-items-center no-gutters">
												<div class="col me-2">
													<div
														class="text-uppercase text-primary fw-bold text-xs mb-1">
														<span><strong>ETUDIANTS</strong><br></span>
													</div>
													<div class="text-dark fw-bold h5 mb-0">
														<span><%=etudiantDAO.nombreEtudiants()%> / <span
															class="badge bg-success"><%=etudiantDAO.nombreEtudiantPay()%>
																Payé</span> <span class="badge bg-danger"><%=etudiantDAO.nombreEtudiantNonPay()%>
																Non Payé</span></span>
													</div>
												</div>
												<div class="col-auto">
													<a href="admin_etudiants.jsp"><i
														class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 mb-4">
									<div
										class="card shadow border-start-success py-2 border-left-success">
										<div class="card-body">
											<div class="row align-items-center no-gutters">
												<div class="col me-2">
													<div
														class="text-uppercase text-success fw-bold text-xs mb-1">
														<span><strong>PROFESSEURS</strong><br></span>
													</div>
													<div class="text-dark fw-bold h5 mb-0">
														<span><%=profDAO.nombreProfs()%> / <span
															class="badge bg-success"><%=profDAO.nombreProfsPay()%>
																Payé</span> <span class="badge bg-danger"><%=profDAO.nombreProfsNonPay()%>
																Non Payé
														</span></span>
													</div>
												</div>
												<div class="col-auto">
													<a href="admin_profs.jsp"><i
														class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col mb-4">
									<div
										class="card shadow border-start-warning py-2 border-left-warning">
										<div class="card-body">
											<div class="row align-items-center no-gutters">
												<div class="col me-2">
													<div
														class="text-uppercase text-warning fw-bold text-xs mb-1">
														<span><strong>Matières</strong><br></span>
													</div>
													<div class="text-dark fw-bold h5 mb-0">
														<span><%=matiereDAO.nombreMatieres()%></span>
													</div>
												</div>
												<div class="col-auto">
													<a href="admin_matieres.jsp"><i
														class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col  mb-4">
									<div
										class="card shadow border-start-warning py-2 border-left-secondary">
										<div class="card-body">
											<div class="row align-items-center no-gutters">
												<div class="col me-2">
													<div
														class="text-uppercase text-warning fw-bold text-xs mb-1">
														<span class="text-secondary"><strong>sections</strong><br></span>
													</div>
													<div class="text-dark fw-bold h5 mb-0">
														<span><%=sectionDAO.nombreSections()%></span>
													</div>
												</div>
												<div class="col-auto">
													<a href="admin_sections.jsp"><i
														class="fas fa-chevron-circle-right fa-2x text-gray-300"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col mb-4">
									<div
										class="card shadow border-start-warning py-2 border-left-info">
										<div class="card-body">
											<div class="row align-items-center no-gutters">
												<div class="col me-2">
													<div
														class="text-uppercase text-warning fw-bold text-xs mb-1"
														style="color: var(--bs-pink);">
														<span class="text-info"><strong>CLASSES</strong><br></span>
													</div>
													<div class="text-dark fw-bold h5 mb-0">
														<span><%=classeDAO.nombreClasses()%></span>
													</div>
												</div>
												<div class="col-auto">
													<a href="admin_classes.jsp"><i
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

		</div>
	</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>