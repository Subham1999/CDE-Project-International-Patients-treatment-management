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
				<h1>Our In-patient Services</h1>
				<div class="container">
					<form:form action="/portal/treatmentRegister" method="GET"
						modelAttribute="patientDetails">
						<div class="form-group">
							<form:label path="name">Your Name:</form:label>
							<form:input path="name" class="form-control" id="name"
								type="text" required="required" pattern="[a-zA-Z]+([\s][a-zA-Z]*)*"
								data-error="Please enter a valid Name." />
						</div>
						<div class="form-group">
							<form:label path="age">Your Age:</form:label>
							<form:input path="age" class="form-control" id="age"
								type="number" max="100" min="1" required="required" />
						</div>
						<div class="form-group">
							<form:label path="ailment">Select Ailment:</form:label>
							<form:select path="ailment" class="form-control" id="ailment"
								items="${ailmentList}" required="required" />
						</div>
						<div class="form-group">
							<form:label path="tretmentPackageName">Select Package Name:</form:label>
							<form:select path="tretmentPackageName" class="form-control"
								id="tretmentPackageName" items="${packageList}"
								required="required" />
						</div>
						<div class="form-group">
							<form:label path="treatmentCommencementDate">Commencement Date:</form:label>
							<form:input path="treatmentCommencementDate" class="form-control"
								id="treatmentCommencementDate" type="date" required="required"
								min="${date}" />
						</div>
						<form:button class="btn">Register</form:button>
						<button class="btn btn-reset" onclick="customRegisterReset()">Reset</button>
					</form:form>
				</div>
				<div class="container result-container center border">
					<h3>Registration result</h3>
					<div id="resultBox">
						<c:choose>
							<c:when test="${not empty error}">
								<div class="error">${error}</div>
							</c:when>
							<c:otherwise>

								<c:choose>
									<c:when test="${not empty plan}">
										</br>
										</br>
										</br>
										<c:choose>
											<c:when test="${not empty patientDetails}">
											</c:when>
										</c:choose>
										<span><h5>Patient Name</h5> </span>
										<span> : </span>
										<span>${patientDetails.getName()} </span>
										<br />
										<span><h5>Age</h5></span>
										<span> : </span>
										<span>${patientDetails.getAge()}</span>
										<br />
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Package Name</th>
													<th>Test Details</th>
													<th>Cost</th>
													<th>Specialist</th>
													<th>COmmencementDate</th>
													<th>End Date</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${plan.getPackageName()}</td>
													<td>${plan.getTestDetails()}</td>
													<td>${plan.getCost()}</td>
													<td>${plan.getSpecialist()}</td>
													<td>${plan.getTreatmentCommenceDate()}</td>
													<td>${plan.getTreatmentEndDate()}</td>
												</tr>
											</tbody>
										</table>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="fragments/footer.jsp"%>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/js/script.js"></script>
</body>
</html>