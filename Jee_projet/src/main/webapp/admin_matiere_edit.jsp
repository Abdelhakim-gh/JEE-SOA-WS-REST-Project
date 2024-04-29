
<%@page import="models.Prof"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Matiere"%>
<%@page import="models.dao.MatiereDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Modifie Matiére</title>

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
						<strong>Matiéres</strong><br>
					</h3>

					<div class="row">
						<div class="col">
							<div class="card shadow mb-3">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Modifie la Matière</p>
								</div>
								<%
								MatiereDAO matiereDAO = new MatiereDAO();
								int id_matiere = Integer.parseInt(request.getParameter("id_matiere"));
								Matiere matiere = matiereDAO.getMatiere(id_matiere);
								%>
								<form method="post"
									action="UpdateMatiere?id_matiere=<%=id_matiere%>" class="">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="mb-3">
													<label class="form-label" for="first_name"><strong>Label</strong><br></label>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<input type="text" class="form-control" name="label"
														value="<%=matiere.getLable()%>"
														placeholder="Lable de Matière">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="mb-3">
													<label class="form-label" for="first_name"><strong>Prix</strong><br></label>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<input type="number" class="form-control" name="prix"
														value="<%=matiere.getPrix()%>"
														placeholder="Prix de Matière DH">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="mb-3">
													<label class="form-label" for="first_name"><strong>Section</strong><br></label>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<input disabled="disabled" type="text" class="form-control"
														value="<%=matiere.getSection().getLabel()%>">
												</div>
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
</body>

</html>