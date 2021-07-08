<%@page import="com.cts.portal.model.IPTreatmentPackage"%>
<%@page import="java.util.List"%>
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

			<%
			String role = (String) session.getAttribute("roles");
			if (role != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
			%>
			<%@ include file="admin-fragments/admin-sidebar.jsp"%>
			<%
			} else if (role != null && role.equalsIgnoreCase("ROLE_USER")) {
			%>
			<%@ include file="user-fragments/side-navbar.jsp"%>
			<%
			}
			%>
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
								<%
								if (role != null && role.equalsIgnoreCase("ROLE_ADMIN")) {
								%>
								<tr>
									<td colspan="6">
										<!-- Button trigger modal -->
										<button type="button" class="btn btn-sm btn-outline-primary"
											style="width: 20%" data-toggle="modal"
											data-target="#${p.getTreatmentPackageId()}modal">Update
											Package</button>
									</td>
								</tr>
								<%
								}
								%>
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

<%
List<IPTreatmentPackage> ipTreatmentPackages = (List<IPTreatmentPackage>) request
		.getAttribute("ipTreatmentPackagekageName");

if (ipTreatmentPackages != null) {

	for (IPTreatmentPackage ipTreatmentPackage : ipTreatmentPackages) {
%>

<%-- <%=ipTreatmentPackages%> --%>
<div class="modal fade"
	id="<%=ipTreatmentPackage.getTreatmentPackageId()%>modal" tabindex="-1"
	role="dialog"
	aria-labelledby="<%=ipTreatmentPackage.getTreatmentPackageId()%>modalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title"
					id="<%=ipTreatmentPackage.getTreatmentPackageId()%>modalLabel">Modal
					title</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="/portal/updateTreatmentPackage" method="post">
					<table class="table">
						<tr>
							<td>Treatment Package Id</td>
							<td class="text-mute"><input type="number"
								readonly="readonly" name="treatmentPackageId"
								value="<%=ipTreatmentPackage.getTreatmentPackageId()%>"></td>
						</tr>
						<tr>
							<td>Ailment Category</td>
							<td class="text-mute"><input type="text"
								name="ailmentCategory" readonly="readonly"
								value="<%=ipTreatmentPackage.getAilmentCategory().toString()%>">
							</td>
						</tr>
						<tr>
							<td>package id</td>
							<td class="text-mute"><input type="number" name="pid"
								readonly="readonly"
								value="<%=ipTreatmentPackage.getPackageDetail().getPid()%>">
							</td>
						</tr>
						<tr>
							<!-- Can be changed -->
							<td>package test duration (weeks)</td>
							<td class="text-mute"><input type="number"
								name="treatmentDuration" max="15" min="1"
								value="<%=ipTreatmentPackage.getPackageDetail().getTreatmentDuration()%>">
							</td>
						</tr>
						<tr>
							<td>package test package name</td>
							<td class="text-mute"><input type="text"
								name="treatmentPackageName" readonly="readonly"
								value="<%=ipTreatmentPackage.getPackageDetail().getTreatmentPackageName()%>"></td>
						</tr>
						<tr>
							<td>package test details</td>
							<td class="text-mute"><input type="text" name="testDetails"
								readonly="readonly"
								value="<%=ipTreatmentPackage.getPackageDetail().getTestDetails()%>"></td>
						</tr>
						<tr>
							<!-- Can be changed -->
							<td>package test cost</td>
							<td class="text-mute"><input type="number" max="150000"
								min="100" name="cost"
								value="<%=ipTreatmentPackage.getPackageDetail().getCost()%>">
							</td>
						</tr>
					</table>
					<button type="submit" class="btn btn-sm btn-outline-danger"
						name="button">change</button>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<%
}
}
%>
