
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.MatiereDAO"%>
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
<title>Etudiant info</title>

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
	EtudiantDAO etudiantDAO = new EtudiantDAO();
	int nb_etuds = etudiantDAO.nombreEtudiantsClasse(etudiant.getClasse().getId_classe());
	%>
	<div id="wrapper">

		<jsp:include page="etudiant_sidebar.html"></jsp:include>

		<div class="d-flex flex-column" id="content-wrapper">

			<div id="content">

				<jsp:include page="etudiant_header.jsp"></jsp:include>

				<div class="container-fluid">
					<h3 class="text-dark mb-4">
						Etudiant<br>
					</h3>
					<div class="row mb-3">
						<div class="col">
							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">Mes info</p>
										</div>

										<div class="card-body">
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Nom</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getUser().getNom()%>
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
														<label class="form-label" for="last_name"><strong><%=etudiant.getUser().getPrenom()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>CNE</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getCne()%></strong></label>
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
 if (etudiant.getPayement() == 0) {
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
														<label class="form-label" for="first_name"><strong>Prix
														</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getPrix()%>
																DH</strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Classe
														</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getSection().getLabel()%>/<%=etudiant.getClasse().getLable()%></strong></label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="first_name"><strong>Nombre
																des Etudiants </strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=nb_etuds%>
														</strong></label>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="card shadow mb-3">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">
										Matières Inscrit<br>
									</p>
								</div>
								<div class="card-body">
									<div class="row">
										<div class="col">
											<div class="bg-white ms-4 collapse-inner rounded">
												<div class="table-responsive  table " id="dataTable-1"
													role="grid" aria-describedby="dataTable_info">
													<table class="table my-0" id="dataTable">
														<thead>
															<tr>
																<th class="text-capitalize ">label</th>
																<th class="text-capitalize ">prix<br></th>
															</tr>
														</thead>
														<tbody>
															<%
															if (etudiant.getMatieres() != null) {
																for (Matiere item : etudiant.getMatieres()) {
															%>
															<tr>
																<td><%=item.getLable()%></td>
																<td><%=item.getPrix()%></td>
															</tr>
															<%
															}
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
					<div class="row">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<div class="row">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center">
											<p class="text-primary m-0 fw-bold">Matières de Section</p>
										</div>
									</div>
								</div>
								<div class="card-body">
									<%
									MatiereDAO matiereDAO = new MatiereDAO();
									ArrayList<Matiere> matieres = matiereDAO.getMatieresbySection(etudiant.getSection().getId_section());
									%>
									<div class="row">
										<div class="col">
											<div class="table-responsive  table mt-2" id="dataTable-1"
												role="grid" aria-describedby="dataTable_info">
												<table class="table my-0" id="dataTable">
													<thead>
														<tr>
															<th class="text-capitalize ">label</th>
															<th class="text-capitalize ">Prix<br></th>
														</tr>
													</thead>
													<tbody>
														<%
														for (Matiere item : matieres) {
														%>
														<tr>

															<td><%=item.getLable()%></td>
															<td><%=item.getPrix()%></td>
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

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>