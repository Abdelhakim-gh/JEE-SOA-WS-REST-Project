
<%@page import="models.dao.MatiereDAO"%>
<%@page import="models.Matiere"%>
<%@page import="models.Section"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Ajouter Prof</title>

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
							Professeurs<br>
						</h3>
						<%
						Object add_user_error = request.getAttribute("add_user_error");
						if (add_user_error != null) {
						%>
						<p class="text-danger"><%=add_user_error.toString()%></p>
						<%
						}
						%>
						<%
						Object add_prof_error = request.getAttribute("add_prof_error");
						if (add_prof_error != null) {
						%>
						<p class="text-danger"><%=add_prof_error.toString()%></p>
						<%
						}
						%>
					</div>

					<div class="row mb-3">
						<div class="col">
							<div class="card shadow">
								<div class="card-header py-3">
									<p class="text-primary m-0 fw-bold">Ajouter&nbsp;Professeur&nbsp;</p>
								</div>

								<form method="post" action="AddProf" class="was-validated">
									<div class="card-body">

										<div class="row">
											<div class="col">
												<div class="row">
													<div class="col">
														<div class="input-group mb-3">
															<input type="text" class="form-control" name="nom"
																placeholder="Nom*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="text" class="form-control" name="prenom"
																placeholder="Prenom*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="number" class="form-control" name="salaire"
																placeholder="salaire">
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col">
														<div class="input-group mb-3">
															<input type="email" class="form-control" name="email"
																placeholder="Email*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="password" class="form-control"
																name="password" placeholder="password*" required>
														</div>
													</div>
													<div class="col">
														<div class="input-group mb-3">
															<input type="tel" class="form-control" placeholder="Tel"
																name="tel">
														</div>
													</div>
												</div>
												<div class="row mb-2">
													<div class="col">
														<div class="card">
															<a
																class="card-header btn text-success btn-sm d-none d-sm-inline-block fw-bold"
																href="#collapseItem4" aria-controls="collapseItem4"
																aria-expanded="true" role="button"
																data-bs-toggle="collapse"> <i
																class="fas fa-plus-square fa-sm text-success"
																style="color: var(--bs-dark);"></i> <span
																class="text-success">Enseigner les Matieres</span>
															</a>
															<%
															MatiereDAO matiereDAO = new MatiereDAO();
															ArrayList<Matiere> matieres = matiereDAO.readSortedMatieres();
															%>
															<div class="card-body">
																<div class="col-lg-4 collapse" id="collapseItem4"
																	data-parent="accordionSidebar"
																	aria-labelledby="headingPages">
																	<div class="bg-white ms-4 collapse-inner rounded">
																		<div class="table-responsive  table mt-2"
																			id="dataTable-1" role="grid"
																			aria-describedby="dataTable_info">
																			<table class="table my-0" id="dataTable">
																				<thead>
																					<tr>
																						<th></th>
																						<th class="text-capitalize ">label</th>
																						<th class="text-capitalize ">Section<br></th>
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
																						<td><%=item.getSection().getLabel()%></td>
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

											<!-- -------------------- -->

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