<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<table class="table table-striped">
						<thead>
							<tr>
								<th rowspan="2">#</th>
								<th rowspan="2">Ailment</th>
								<th colspan="4" class="left-border center">Package Details</th>
							</tr>
							<tr>
								<th class="left-border">Name</th>
								<th>Test detail</th>
								<th>Cost</th>
								<th>Duration in weeks</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach items="${ipTreatmentPackagekageName}" var="p"
								varStatus="loop">
								<tr>
									<td>${loop.index+1}</td>
									<td>${p.getAilmentCategory()}</td>
									<td>${p.getPackageDetail().getTreatmentPackageName()}</td>
									<td>${p.getPackageDetail().getTestDetails()}</td>
									<td>${p.getPackageDetail().getCost()}</td>
									<td>${p.getPackageDetail().getTreatmentDuration()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>

		<%@ include file="fragments/footer.jsp"%>
		
	</div>
</body>

</html>