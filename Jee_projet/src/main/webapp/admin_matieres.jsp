
<%@page import="java.awt.event.ItemEvent"%>
<%@page import="models.Matiere"%>
<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.Section"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Les Matieres</title>

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
						<a
							class="btn btn-primary btn-sm d-none d-sm-inline-block collapsed"
							role="button" href="#collapseItem3" aria-controls="collapseItem3"
							aria-expanded="false" role="button" data-bs-toggle="collapse">
							<i class="fas fa-plus-square fa-sm text-white-50"
							style="color: var(--bs-orange);"></i>&nbsp;Ajouter
							un&nbsp;Matiers<br>
						</a>
					</div>

					<div class="row mb-3 collapse" id="collapseItem3"
						data-parent="accordionSidebar" aria-labelledby="headingPages">
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

					<div class="row">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<div class="row">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center">
											<p class="text-primary m-0 fw-bold">Liste des Matières</p>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="row ">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center mb-3">

											<!-- 											<div class="">
												<div class="text-md-end dataTables_filter"
													id="dataTable_filter">
													<label class="form-label"> <input type="search"
														class="form-control form-control"
														aria-controls="dataTable" placeholder="Search">
													</label> <a href="#" class="btn btn-dark"> <i
														class="fas fa-search"></i>
													</a>
												</div>
											</div> -->

										</div>

										<%
										MatiereDAO matiereDAO = new MatiereDAO();
										ArrayList<Matiere> matieres = matiereDAO.readMatieres();
										%>

										<div class="table-responsive  table mt-2" id="dataTable-1"
											role="grid" aria-describedby="dataTable_info">
											<table class="table my-0" id="dataTable">
												<thead>
													<tr>
														<th class="text-capitalize ">label</th>
														<th class="text-capitalize text-center">Section<br></th>
														<th class="text-capitalize text-center">Prix<br></th>
														<th class="text-capitalize text-center">Enseigneres<br></th>
														<th class="text-capitalize text-center">Inscriptions<br></th>
														<th class="text-capitalize text-center">action</th>
													</tr>
												</thead>
												<tbody>
													<%
													for (Matiere item : matieres) {
													%>
													<tr>
														<td class=""><%=item.getLable()%></td>
														<td class="text-center"><%=item.getSection().getLabel()%></td>
														<td class="text-center"><%=item.getPrix()%> DH</td>
														<td class="text-center"><%=matiereDAO.nombreEnseigneres(item.getId_matiere())%></td>
														<td class="text-center"><%=matiereDAO.nombreInscriptions(item.getId_matiere())%></td>

														<td class="text-center"><a
															class="btn btn-block btn-warning m-1 " role="button"
															href="admin_matiere_edit.jsp?id_matiere=<%=item.getId_matiere()%>">
																<i class="fas fa-edit" style="color: #ffffff;"></i>
														</a> <a class="btn btn-block btn-danger m-1 " role="button"
															href="DeleteMatiere?id_matiere=<%=item.getId_matiere()%>">
																<i class="fas fa-trash"></i>
														</a><br></td>
													</tr>
													<%
													}
													%>
												</tbody>
												<tfoot>
													<tr></tr>
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
	</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>