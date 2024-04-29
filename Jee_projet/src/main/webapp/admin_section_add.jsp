
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Ajouter Section</title>

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
							Object msg = request.getAttribute("add_section_error");
							if (msg != null) {%>
								<p class="text-danger"><%=msg.toString()%></p>
							<%}
						%>
						
					</div>

					<div class="row mb-3">
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

				</div>

			</div>

		</div>
	</div>

	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>