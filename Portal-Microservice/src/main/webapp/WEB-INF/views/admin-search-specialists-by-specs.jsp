<%@page import="com.cts.portal.model.SpecialistDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>specialists details</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>International Patients Management System</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Pinyon+Script&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="/css/style-admin.css">
<link rel="stylesheet" href="/css/style-table.css">
</head>
<body>
	<%
	List<SpecialistDetail> specialistDetails = (List<SpecialistDetail>) request.getAttribute("list");
	int n = specialistDetails.size();
	%>
	<div class="main-container-register">
		<%@ include file="fragments/header.jsp"%>
		<div class="section grid">
			<%@ include file="admin-fragments/admin-sidebar.jsp"%>
			<div class="content">
				<h1>Specialists</h1>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Years of Exp.</th>
							<th scope="col">Phone Number</th>
							<th scope="col">Area</th>
							<!-- <th scope="col">Action</th> -->
						</tr>
					</thead>
					<tbody>
						<%
						for (SpecialistDetail detail : specialistDetails) {
						%>
						<tr>
							<td><%=detail.getSpecialistId()%></td>
							<td><%=detail.getName()%></td>
							<td><%=detail.getExperienceInYears()%></td>
							<td><%=detail.getContactNumber()%></td>
							<td><%=detail.getAreaOfExpertise()%></td>
						</tr>
						<tr>
							<td colspan="5">
								<form class="container row" action="/portal/removeSpecialist"
									method="post">
									<button class="btn btn-sm btn-outline-danger" name="id"
										value="<%=detail.getSpecialistId()%>">Remove
										specialist</button>
								</form> <!-- <input type="button" class="btn btn-sm btn-outline-danger" name="id" value="delete"> -->
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<%@ include file="fragments/footer.jsp"%>
	</div>
</body>
</html>