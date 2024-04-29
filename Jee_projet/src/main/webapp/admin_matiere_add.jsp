
<%@page import="models.Section"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Ajouter Matiere</title>

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
							Matières<br>
						</h3>
						<%
							Object msg = request.getAttribute("add_matiere_error");
							if (msg != null) {%>
								<p class="text-danger"><%=msg.toString()%></p>
							<%}
						%>
					</div>

					<div class="row mb-3 ">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Ajouter&nbsp;Matière&nbsp;</p>
								</div>
								<%
									SectionDAO sectionDAO = new SectionDAO();
									ArrayList<Section> sections = sectionDAO.readSections();
								%>
								<form method="post" action="AddMatiere" class="was-validated">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="input-group mb-3">
													<input type="text" class="form-control" name="label"
														placeholder="lable Matière*" required>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<select class="form-select" name="id_section"
														aria-label="Default select example" required>
														<option value="" disabled selected>Section
															inconnu*</option>
														<%
														for (Section item : sections) {
														%>
															<option value="<%=item.getId_section()%>"><%=item.getLabel()%></option>
														<%
														}
														%>
													</select>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<input type="number" class="form-control" name="prix"
														placeholder="Prix de Matière DH*" required>
												</div>
											</div>
										</div>
										<!-- -------------------- -->

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
</body>

</html>