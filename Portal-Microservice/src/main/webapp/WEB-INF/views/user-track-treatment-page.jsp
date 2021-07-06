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
<html lang="en">

<head>
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
<link rel="stylesheet" href="/css/style-table.css">
<link rel="stylesheet" href="/css/style-admin.css">
</head>
<body>
	<div class="main-container-register">
		<%@ include file="fragments/header.jsp"%>
		<div class="section grid">
			<%@ include file="admin-fragments/admin-sidebar.jsp"%>
			<div class="content list-container">
				<h1>Treatment Plan</h1>
				<div class="container">
					<c:choose>
						<c:when test="${not empty error}">
							<div class="error">${error}</div>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${not empty plans}">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>#</th>
												<th>Name</th>
												<th>Age</th>
												<th>Package Name</th>
												<th>Test Details</th>
												<th>Cost</th>
												<th>Specialist</th>
												<th>COmmencementDate</th>
												<th>End Date</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${plans}" var="plan" varStatus="loop">
												<tr>
													<td>${loop.index+1}</td>
													<td>${plan.getPatientDetails().getName()}</td>
													<td>${plan.getPatientDetails().getAge()}</td>
													<td>${plan.getPackageName()}</td>
													<td>${plan.getTestDetails()}</td>
													<td>${plan.getCost()}</td>
													<td>${plan.getSpecialist()}</td>
													<td>${plan.getTreatmentCommenceDate()}</td>
													<td>${plan.getTreatmentEndDate()}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<%@ include file="fragments/footer.jsp"%>
	</div>
</body>
</html>