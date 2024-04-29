<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.dao.EtudiantDAO"%>
<%@page import="models.dao.ClasseDAO"%>
<%@page import="java.awt.event.ItemEvent"%>
<%@page import="models.Section"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Les Sections</title>

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
							Sections<br>
						</h3>
						<%
						Object delete_section_error = request.getAttribute("delete_section_error");
						if (delete_section_error != null) {
						%>
						<p class="text-danger"><%=delete_section_error.toString()%></p>
						<%
						}
						%>
						<a
							class="btn btn-primary btn-sm d-none d-sm-inline-block collapsed"
							role="button" href="#collapseItem3" aria-controls="collapseItem3"
							aria-expanded="false" role="button" data-bs-toggle="collapse">
							<i class="fas fa-plus-square fa-sm text-white-50"
							style="color: var(--bs-orange);"></i>&nbsp;Ajouter
							un&nbsp;Sections<br>
						</a>
					</div>

					<div class="row mb-3 collapse" id="collapseItem3"
						data-parent="accordionSidebar" aria-labelledby="headingPages">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Ajouter&nbsp;Section
										&nbsp;</p>
								</div>
								<form method="post" action="AddSection" class="was-validated">
									<div class="card-body">
										<div class="row">
											<div class="col">
												<div class="input-group mb-3">
													<input name="niveau" type="text" class="form-control"
														placeholder="lable Niveau scolaire*" required>
												</div>
											</div>
											<div class="col">
												<div class="input-group mb-3">
													<input name="filiere" type="text" class="form-control"
														placeholder="lable Filiere*" required>
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

					<div class="row">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<div class="row">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center">
											<p class="text-primary m-0 fw-bold">Liste des Sections</p>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="row ">
										<div
											class="col d-flex justify-content-between align-content-center align-items-center mb-3">

											<!-- 											<div class="">
												<form action="Sections" method="post">
												<div class="text-md-end dataTables_filter"
													id="dataTable_filter">
													<label class="form-label"> 
													<input type="search" name="rech"
														class="form-control form-control" 
														aria-controls="dataTable" placeholder="Rechereche">
													</label> <a href="#" class="btn btn-dark"> <i
														class="fas fa-search"></i>
													</a>
												</div>
												</form>
											</div> -->

										</div>
									</div>

									<%
									SectionDAO sectionDAO = new SectionDAO();
									ArrayList<Section> sections = sectionDAO.readSections();

									ClasseDAO classDAO = new ClasseDAO();
									EtudiantDAO etudiantDAO = new EtudiantDAO();
									MatiereDAO matierDAO = new MatiereDAO();
									%>
									<div class="table-responsive table mt-2" id="dataTable-1"
										role="grid" aria-describedby="dataTable_info">
										<table class="table my-0" id="dataTable">
											<thead>
												<tr>
													<th class="text-capitalize text-center">Niveau/Filiere</th>
													<th class="text-capitalize text-center">Classes</th>
													<th class="text-capitalize text-center">Etudiants</th>
													<th class="text-capitalize text-center">Les Matieres<br></th>
													<th class="text-capitalize text-center">action</th>
												</tr>
											</thead>
											<tbody>
												<%
												for (Section item : sections) {
													if (item.getId_section() != 20) {
													int nb_etuds = etudiantDAO.nombreEtudiantsSection(item.getId_section());
													int nb_classes = classDAO.nombreClassesSection(item.getId_section());
													int nb_matieres = matierDAO.nombreMatieresSection(item.getId_section());
												%>
												<tr>
													<td class="text-center"><%=item.getLabel()%></td>
													<td class="text-center"><%=nb_classes%></td>
													<td class="text-center"><%=nb_etuds%></td>
													<td class="text-center"><%=nb_matieres%></td>
													<td class="text-center"><a
														class="btn btn-block btn-warning m-1 " role="button"
														href="admin_section_edit.jsp?id_section=<%=item.getId_section()%>">
															<i class="fas fa-edit" style="color: #ffffff;"></i>
													</a> <a class="btn btn-block btn-danger m-1 " role="button"
														href="DeleteSection?id_section=<%=item.getId_section()%>&nb_classes=<%=nb_classes%>&nb_etuds=<%=nb_etuds%>&nb_matieres=<%=nb_matieres%>">
															<i class="fas fa-trash"></i>
													</a><br></td>
												</tr>
												<%
												}
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

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>