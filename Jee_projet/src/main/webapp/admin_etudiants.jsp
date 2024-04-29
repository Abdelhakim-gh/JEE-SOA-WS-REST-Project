
<%@page import="models.Etudiant"%>
<%@page import="models.dao.EtudiantDAO"%>
<%@page import="models.Section"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Les Etudiants</title>

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
						<%
						Object effuctue_etudiant_error = request.getAttribute("effuctue_etudiant_error");
						if (effuctue_etudiant_error != null) {
						%>
						<p class="text-danger"><%=effuctue_etudiant_error.toString()%></p>
						<%
						}
						%>

						<%
						Object update_etudiant_error = request.getAttribute("update_etudiant_error");
						if (update_etudiant_error != null) {
						%>
						<p class="text-danger"><%=update_etudiant_error.toString()%></p>
						<%
						}
						%>

						<a
							class="btn btn-primary btn-sm d-none d-sm-inline-block collapsed"
							role="button" href="#collapseItem3" aria-controls="collapseItem3"
							aria-expanded="false" role="button" data-bs-toggle="collapse">
							<i class="fas fa-plus-square fa-sm text-white-50"
							style="color: var(--bs-orange);"></i>&nbsp;Ajouter un étudiant<br>
						</a>
					</div>

					<div class="row mb-3 collapse" id="collapseItem3"
						data-parent="accordionSidebar" aria-labelledby="headingPages">
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

				<div class="row p-2">
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
								<div class="row ">
									<div
										class="col d-flex justify-content-between align-content-center align-items-center mb-3">

										<div class=""></div>

									</div>
								</div>
								<%
								EtudiantDAO etudiantDAO = new EtudiantDAO();
								ArrayList<Etudiant> etudiants = etudiantDAO.readEtudiants();
								%>
								<div class="table-responsive table mt-2" id="dataTable-1"
									role="grid" aria-describedby="dataTable_info">
									<table class="table my-0" id="dataTable">
										<thead>
											<tr>
												<th class="text-capitalize ">Nom &amp; Prenom</th>
												<th class="text-capitalize ">CNE</th>
												<th class="text-capitalize ">email</th>
												<th class="text-capitalize ">classe</th>
												<th class="text-capitalize ">prix</th>
												<th class="text-capitalize ">statut</th>
												<th class="text-capitalize ">Matieres</th>
												<th class="text-capitalize text-center">action</th>
											</tr>
										</thead>
										<tbody>

											<%
											for (Etudiant item : etudiants) {
											%>
											<tr>
												<td><%=item.getUser().getNom()%> <%=item.getUser().getPrenom()%></td>
												<td><%=item.getCne()%></td>
												<td><%=item.getUser().getEmail()%></td>
												<td><%=item.getSection().getLabel()%>/<%=item.getClasse().getLable()%>
												</td>
												<td><%=item.getPrix()%></td>
												<%
												if (item.getPayement() == 0) {
												%>
												<td><span class="badge bg-danger">Non Payé</span></td>
												<%
												} else {
												%>
												<td><span class="badge bg-success">Payé</span></td>
												<%
												}
												%>
												<td class="text-center"><%=item.getMatieres().size()%></td>
												<td class="text-center"><a
													class="btn btn-block btn-primary m-1 " role="button"
													href="admin_etudiant_info.jsp?id_etud=<%=item.getId_etud()%>">
														<i class="fas fa-eye"></i>
												</a> <a class="btn btn-block btn-warning m-1 " role="button"
													href="admin_etudiant_edit.jsp?id_etud=<%=item.getId_etud()%>">
														<i class="fas fa-edit" style="color: #ffffff;"></i>
												</a> <a class="btn btn-block btn-danger m-1 " role="button"
													href="DeleteEtudiant?id_etud=<%=item.getId_etud()%>"> <i
														class="fas fa-trash"></i>
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