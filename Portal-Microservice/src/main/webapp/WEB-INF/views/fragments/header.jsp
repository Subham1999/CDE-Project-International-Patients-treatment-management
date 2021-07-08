<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="header">

	<!------------------------------------ header------------------------>
	<div class="navbar navbar-expand-lg navbar-light ">
		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6">
			<a class="navbar-brand" href="#"> IPMS </a>
			<%
			if (session.getAttribute("userName") != null) {
			%>
			<a class="navbar-brand disabled" href="#">${userName}</a>
			<%
			}
			%>
		</div>


		<div class="col-lg-6 col-md-6 col-xs-6 col-sm-6"></div>

	</div>
</div>
