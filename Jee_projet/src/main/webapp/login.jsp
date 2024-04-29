<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Login - Brand</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
<link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
<link rel="stylesheet"
	href="assets/fonts/fontawesome5-overrides.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.2/css/theme.bootstrap_4.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet"
	href="assets/css/Ludens---1-Index-Table-with-Search--Sort-Filters-v20.css">
</head>

<body
	class="bg-gradient-light d-flex align-items-center justify-content-center text-white"
	style="height: 100vh; margin: 0; --bs-bg-opacity: .5;">

	<div class="container justify-content-center col-md-6 ">
	<h2 class="text-center text-primary mb-3 fw-bold">Bienvenue au centre de soutien scolaire</h2>
		<div class="card shadow border-left-primary ">
			<div class="card-header text-center">
				<h4 class="text-center text-primary m-0 fw-bold ">Se Connecte</h4>
			</div>
			<form action="Login" method="post">
				<div class="card-body ">

					<div class="row ">
						<div class="col ">
							<!-- Email Input -->
							<div class="input-group mb-3 ">
								<span class="input-group-text " id="basic-addon1 "> <svg
										xmlns="http://www.w3.org/2000/svg " width="16 " height="16 "
										fill="currentColor " class="bi bi-envelope "
										viewBox="0 0 16 16 ">
                                        <path
											d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64
    3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z ">
                                        </path>
                                    </svg>
								</span> <input type="email" name="email" class="form-control "
									placeholder="Email* " aria-label="Input group example "
									aria-describedby="basic-addon1" required>
							</div>
						</div>
					</div>

					<div class="row ">
						<div class="col ">
							<!-- Password Input -->
							<div class="input-group mb-3 ">
								<span class="input-group-text " id="basic-addon1 "> <svg
										xmlns="http://www.w3.org/2000/svg " width="16 " height="16 "
										fill="currentColor " class="bi bi-key " viewBox="0 0 16 16 ">
                                        <path
											d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1
    8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z ">
                                        </path>
                                        <path
											d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z "></path>
                                    </svg>
								</span> <input type="password" name="password" class="form-control "
									placeholder="Password* " aria-label="Input group example "
									aria-describedby="basic-addon1 " required>
							</div>
						</div>
					</div>
					<%
					Object user_not_found = request.getAttribute("user_not_found");
					if (user_not_found != null) {
					%>
					<p class="text-danger text-center"><%=user_not_found.toString()%></p>
					<%
					}
					%>
					<%
					Object etudiant_not_found = request.getAttribute("etudiant_not_found");
					if (etudiant_not_found != null) {
					%>
					<p class="text-danger text-center"><%=etudiant_not_found.toString()%></p>
					<%
					}
					%>
					<%
					Object prof_not_found = request.getAttribute("prof_not_found");
					if (prof_not_found != null) {
					%>
					<p class="text-danger text-center"><%=prof_not_found.toString()%></p>
					<%
					}
					%>
				</div>
				<div
					class="card-footer d-flex justify-content-between align-items-center ">
					<button type="submit" class="btn btn-primary ">Soumettre</button>
					<button type="reset" class="btn btn-block btn-outline-primary ">Remmettre</button>
				</div>
			</form>
		</div>
	</div>

	<script src="assets/bootstrap/js/bootstrap.min.js "></script>
	<script src="assets/js/bs-init.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.2/js/jquery.tablesorter.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.2/js/widgets/widget-filter.min.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.2/js/widgets/widget-storage.min.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js "></script>
	<script
		src="assets/js/Ludens---1-Index-Table-with-Search--Sort-Filters-v20-1.js "></script>
	<script
		src="assets/js/Ludens---1-Index-Table-with-Search--Sort-Filters-v20.js "></script>
	<script src="assets/js/theme.js "></script>
</body>

</html>