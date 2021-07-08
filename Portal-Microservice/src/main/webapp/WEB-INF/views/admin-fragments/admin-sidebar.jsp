<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="components.jsp"%>
<div class="side-navbar">
	<ul class="list-group" id="pageSubmenu">
		<li class="list-group-item"><a href="/portal/specialists">Our
				Specialists</a></li>

		<!-- ////////////////////////////////////////////////////////////////////////////  -->
		<!-- TODO -->
		<li class="list-group-item"><button class="btn"
				data-toggle="modal" data-target="#searchSpecialistsByExpertise">
				<b>Search specialists by specialization</b>
			</button></li>
		<!-- <button type="button" class="btn btn-primary" >Launch demo modal</button> -->

		<!-- TODO -->
		<li class="list-group-item">
			<button class="btn" data-toggle="modal"
				data-target="#addNewSpecialist">Add new specialist</button>
		</li>
		<!-- ////////////////////////////////////////////////////////////////////////////  -->

		<li class="list-group-item"><a href="/portal/ipTreatmentPackages">Our
				Service Packages</a></li>
		<li class="list-group-item"><a
			href="/portal/ipTreatmentPackageByName">Search Package</a></li>
		<li class="list-group-item"><a href="/portal/treatmentRegister">Register
				Patient</a></li>
		<li class="list-group-item"><a href="/portal/trackTreatment">View
				Treatment Plan</a></li>
		<li class="list-group-item"><a href="/portal/adminViewActivePage">View
				Active Users</a></li>
		<li class="list-group-item"><a
			href="/portal/adminViewInactivePage">View InActive Users</a></li>
		<li class="list-group-item"><a href="/portal/getAllInsurerDetail">View
				Insurers</a></li>
		<li class="list-group-item"><a href="/portal/logout">Logout</a></li>

		<!-- ////////////////////////////////////////////////////////////////////////////  -->
		<!-- TODO -->
		<li class="list-group-item"><a
			href="/portal/updateTreatmentPackage">Update Treatment Package</a></li>

	</ul>
</div>


<!-- ///////////////////////////////////////////////////////////////////////////////////  -->
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="searchSpecialistsByExpertise" tabindex="-1"
	role="dialog" aria-labelledby="searchSpecialistsByExpertiseLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="searchSpecialistsByExpertiseLabel">search
					specialist</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="/portal/searchSpecialistsByExpertise" method="post">
					<select name="areaOfExpertise">
						<option>Orthopaidics</option>
						<option>Urology</option>
					</select> <input type="submit" value="SEARCH">
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>




<!-- Modal -->
<div class="modal fade" id="addNewSpecialist" tabindex="-1"
	role="dialog" aria-labelledby="addNewSpecialistLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="addNewSpecialistLabel">Add new
					specialist</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form action="/portal/addSpecialist" method="post">
					<table class="table">
						<tr>
							<td>Name</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td>Phone Number</td>
							<td><input type="number" min="1111111111" max="9999999999"
								name="contactNumber"></td>
						</tr>
						<tr>
							<td>Exp in Years.</td>
							<td><input type="number" min="1" max="20"
								name="experienceInYears"></td>
						</tr>
						<tr>
							<td>Area of Expertise</td>
							<td><select class="custom-select" name="areaOfExpertise">
									<option>ORTHOPAIDICS</option>
									<option>UROLOGY</option>
							</select></td>
						</tr>
						<tr>
							<td><button type="submit" class="btn btn-success">submit</button></td>
							<td><button type="reset" class="btn btn-primary">reset</button></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
