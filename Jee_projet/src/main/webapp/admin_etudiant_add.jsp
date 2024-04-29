
<%@page import="models.Section"%>
<%@page import="models.dao.SectionDAO"%>
<%@page import="models.Classe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.ClasseDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Ajouter Etudiant</title>

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
							Etudiants<br>
						</h3>
					</div>

					<div class="row mb-3 ">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Ajouter&nbsp;Etudiant&nbsp;</p>
								</div>
								<%
								SectionDAO sectionDAO = new SectionDAO();
								ArrayList<Section> sections = sectionDAO.readSections();
								%>
								<form method="post" action="AddEtudiant" class="was-validated">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="row">
													<div class="col">
														<div class="input-group mb-3">
															<input type="text" name="nom" class="form-control"
																placeholder="Nom*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="text" name="prenom" class="form-control"
																placeholder="Prenom*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="text" name="cne" class="form-control"
																placeholder="CNE*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<!-- Sections dropdown -->
															<select class="form-select" id="sectionsDropdown"
																name="section_id" aria-label="Default select example"
																required="required">
																<option disabled selected>Section inconnu*</option>
																<%
																for (Section section : sections) {
																%>
																<option value="<%=section.getId_section()%>"><%=section.getLabel()%></option>
																<%
																}
																%>
															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col">
														<div class="input-group mb-3">
															<input type="email" name="email" class="form-control"
																placeholder="Email*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="password" name="password"
																class="form-control" placeholder="Password*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="tel" name="tel" class="form-control"
																placeholder="Tel">
														</div>
													</div>
												</div>
												<!-- -------------------- -->
												
											</div>
										</div>
									</div>
									<div
										class="card-footer d-flex justify-content-between align-items-center">
										<button type="submit" class="btn btn-primary">Soumettre</button>
										<button type="reset" class="btn btn-block btn-outline-primary">Remmettre</button>
									</div>
								</form>
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
	
</script>
	
	
</body>

</html>