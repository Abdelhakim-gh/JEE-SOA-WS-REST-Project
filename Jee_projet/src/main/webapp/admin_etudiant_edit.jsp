
<%@page import="models.Matiere"%>
<%@page import="models.Etudiant"%>
<%@page import="models.dao.EtudiantDAO"%>
<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.Classe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.ClasseDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Modifie Etudiant</title>

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
						<strong>Etudiant</strong><br>
					</h3>
					<%
					EtudiantDAO etudiantDAO = new EtudiantDAO();
					int id = Integer.parseInt(request.getParameter("id_etud"));
					Etudiant etudiant = etudiantDAO.getEtudiant(id);
					%>
					<div class="row">
						<div class="col">
							<div class="card shadow mb-3">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Modifie l'Etudiant</p>
								</div>
								<form method="post" action="UpdateEtudiant?id_etud=<%=id%>"
									class="">
									<div class="card-body">

										<div class="row">
											<div class="col">
												<div class="mb-3">
													<label class="form-label" for="first_name"><strong>Payement</strong><br></label>
												</div>
											</div>
											<div class="col">
												<div class="btn-group mb-3" role="group"
													aria-label="payement">
													<%
													if (etudiant.getPayement() == 0) {
													%>
													<input type="radio" class="btn-check" name="payement"
														value="1" id="h" autocomplete="off"> <label
														class="btn btn-outline-primary" for="h">Payé</label> <input
														type="radio" class="btn-check" name="payement" value="0"
														id="f" autocomplete="off" checked> <label
														class="btn btn-outline-primary" for="f">Non payé</label>
													<%
													} else {
													%>
													<input type="radio" class="btn-check" name="payement"
														value="1" id="h" autocomplete="off" checked> <label
														class="btn btn-outline-primary" for="h">Payé</label> <input
														type="radio" class="btn-check" name="payement" value="0"
														id="f" autocomplete="off"> <label
														class="btn btn-outline-primary" for="f">Non payé</label>
													<%
													}
													%>
												</div>
											</div>
										</div>

										<div class="row mb-2">
											<div class="col">
												<div class="card">
													<a
														class="card-header btn text-primary btn-sm d-none d-sm-inline-block fw-bold"
														href="#collapseItem4" aria-controls="collapseItem4"
														aria-expanded="true" role="button"
														data-bs-toggle="collapse"> <i
														class="fas fa-plus-square fa-sm text-primary"
														style="color: var(--bs-dark);"></i> <span
														class="text-primary">Effectue</span>
													</a>
													<div class="card-body">
														<div class="col collapse" id="collapseItem4"
															data-parent="accordionSidebar"
															aria-labelledby="headingPages">
															<div class="bg-white ms-4 collapse-inner rounded">

																<%
																ClasseDAO classDAO = new ClasseDAO();
																ArrayList<Classe> classes = classDAO.getClassesBySectionId(etudiant.getSection().getId_section());

																MatiereDAO matiereDAO = new MatiereDAO();
																ArrayList<Matiere> matieres = matiereDAO.getMatieresbySection(etudiant.getSection().getId_section());
																%>
																<div class="row">
																	<div class="col">
																		<div class="input-group mb-3">
																			<!-- Sections dropdown -->
																			<select class="form-select" id="sectionsDropdown"
																				name="classe_id" aria-label="Default select example"
																				required="required">
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
																		<div class="table-responsive  table mt-2"
																			id="dataTable-1" role="grid"
																			aria-describedby="dataTable_info">
																			<table
																				class="table my-0 table-bordered	border-primary"
																				id="dataTable">
																				<thead>
																					<tr>
																						<th class="text-capitalize ">label</th>
																						<th class="text-capitalize ">Prix<br></th>
																					</tr>
																				</thead>
																				<tbody>
																					<%
																					for (Matiere item : etudiant.getMatieres()) {
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
																	<div class="col">
																		<div class="table-responsive  table mt-2"
																			id="dataTable-1" role="grid"
																			aria-describedby="dataTable_info">
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
																						<td><input class="form-check-input"
																							name="matieres" value="<%=item.getId_matiere()%>"
																							type="checkbox" id=""></td>
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