
<%@page import="models.Matiere"%>
<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.Etudiant"%>
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
<title>Effuctue</title>

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
					<%
					Etudiant etudiant = (Etudiant) request.getAttribute("etud_info");
					%>

					<div class="row mb-3">
						<div class="col-lg-8">
							<div class="row">
								<div class="col">
									<div class="card shadow mb-3">
										<div class="card-header py-3">
											<p class="text-primary m-0 fw-bold">Etudiant info</p>
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
														<label class="form-label" for="first_name"><strong>Tel</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getUser().getTel()%></strong></label>
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
														<label class="form-label" for="last_name"><strong><%=etudiant.getUser().getEmail()%></strong></label>
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
														<label class="form-label" for="first_name"><strong>Section</strong><br></label>
													</div>
												</div>
												<div class="col">
													<div class="mb-3">
														<label class="form-label" for="last_name"><strong><%=etudiant.getSection().getLabel()%></strong></label>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

					<div class="row mb-3 ">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Effectue&nbsp;Classe&nbsp;&&nbsp;Matieres</p>
								</div>
								<%
								ClasseDAO classDAO = new ClasseDAO();
								ArrayList<Classe> classes = classDAO.getClassesBySectionId(etudiant.getSection().getId_section());

								MatiereDAO matiereDAO = new MatiereDAO();
								ArrayList<Matiere> matieres = matiereDAO.getMatieresbySection(etudiant.getSection().getId_section());
								
								%>
								<form method="post"
									action="EtudiantAssigning?etud_id=<%=etudiant.getId_etud()%>">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="input-group mb-3">
													<!-- Sections dropdown -->
													<select class="form-select" id="sectionsDropdown"
														name="classe_id" aria-label="Default select example"
														required>
														<option disabled selected>Class inconnu*</option>
														<%
														for (Classe classe : classes) {
														%>
														<option value="<%=classe.getId_classe()%>"><%=etudiant.getSection().getLabel()%>/<%=classe.getLable()%></option>
														<%
														}
														%>
													</select>
												</div>
											</div>
											<div class="col">
												<div class="table-responsive  table mt-2" id="dataTable-1"
													role="grid" aria-describedby="dataTable_info">
													<table class="table my-0" id="dataTable">
														<thead>
															<tr>
																<th></th>
																<th class="text-capitalize ">label</th>
																<th class="text-capitalize ">Prix<br></th>
															</tr>
														</thead>
														<tbody>
															<%
															for (Matiere item : matieres) {
															%>
															<tr>
																<td><input class="form-check-input" name="matieres"
																	value="<%=item.getId_matiere()%>" type="checkbox" id=""></td>
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