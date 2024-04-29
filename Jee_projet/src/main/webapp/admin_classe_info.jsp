
<%@page import="models.dao.ClasseDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Classe"%>
<%@page import="models.Etudiant"%>
<%@page import="models.dao.EtudiantDAO"%>
<%@page import="models.Matiere"%>
<%@page import="models.Prof"%>
<%@page import="models.dao.ProfDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Classe info</title>

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
						class="d-sm-flex justify-content-between align-items-center ">
						<h3 class="text-dark mb-4">
							Classe<br>
						</h3>
						<%
						Object delete_etudiant_error = request.getAttribute("delete_etudiant_error");
						if (delete_etudiant_error != null) {
						%>
						<p class="text-danger"><%=delete_etudiant_error.toString()%></p>
						<%
						}
						%>
					</div>

					<%
					ClasseDAO classeDAO = new ClasseDAO();
					int id = Integer.parseInt(request.getParameter("id_classe"));
					Classe classe = classeDAO.getClasse(id);
					EtudiantDAO etudiantDAO = new EtudiantDAO();
					ArrayList<Etudiant> etudiants = etudiantDAO.getEtudiantByClass(id);
					%>
					<div class="row mb-3">
						<div class="col-lg-3">
							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">Classe info</p>
										</div>
										<div class="card-body">
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Classe
														</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=classe.getSection().getLabel()%>/<%=classe.getLable()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Etudiants
														</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiantDAO.nombreEtudiantsClasse(classe.getId_classe())%></strong></label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<div class="row">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center">
											<p class="text-primary m-0 fw-bold">Liste des Etudiantes</p>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="table-responsive table mt-2" id="dataTable-1"
										role="grid" aria-describedby="dataTable_info">
										<table class="table my-0" id="dataTable">
											<thead>
												<tr>
													<th class="text-capitalize ">Nom &amp; Prenom</th>
													<th class="text-capitalize ">CNE</th>
													<th class="text-capitalize ">prix</th>
													<th class="text-capitalize text-center">Action</th>

												</tr>
											</thead>
											<tbody>

												<%
												for (Etudiant item : etudiants) {
												%>
												<tr>
													<td><%=item.getUser().getNom()%> <%=item.getUser().getPrenom()%></td>
													<td><%=item.getCne()%></td>
													<td><%=item.getPrix()%></td>
													<td class="text-center"><a
														class="btn btn-block btn-danger" role="button"
														href="#">
															<i class="fas fa-trash"></i>
													</a><br></td>
												</tr>
												<%
												}
												%>

											</tbody>
											<tfoot>
											</tfoot>
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

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>