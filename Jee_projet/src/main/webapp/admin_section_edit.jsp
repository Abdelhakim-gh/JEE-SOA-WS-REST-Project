	<%@page import="models.Section"%>
<%@page import="models.dao.SectionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Modéfie Section</title>

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

                    <h3 class="text-dark mb-4"><strong>Section</strong><br></h3>

                    <div class="row">
                        <div class="col">
                            <div class="card shadow mb-3">
                                <div class="card-header py-3">
                                    <p class="text-primary m-0 fw-bold">Modifie le Section</p>
                                </div>
                                <%
                                	SectionDAO sectionDAO = new SectionDAO();
                                	int id = Integer.parseInt(request.getParameter("id_section"));
                                	Section section = sectionDAO.getSection(id);
                                
                                %>
                                <form method="post" action="UpdateSection?id_section=<%=section.getId_section()%>" class="">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col">
                                                <div class="input-group mb-3">
                                                    <input type="text" value="<%=section.getNiveau()%>" name="niveau" class="form-control" placeholder="nouvelle niveau">
                                                    <input type="text" value="<%=section.getFiliere()%>" name="filiere" class="form-control" placeholder="nouvelle filiere">
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="card-footer d-flex justify-content-between align-items-center">
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
	
	<a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
	
	<jsp:include page="_scripts.html"></jsp:include>
</body>

</html>