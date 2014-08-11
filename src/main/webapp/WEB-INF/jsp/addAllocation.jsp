<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Allocation</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>

<body>
	<%@include file="navlinks.jsp"%>
	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;">
			<h2>Add New Allocation</h2>
			<form:form method="post" action="insertAllocation"
				modelAttribute="allocation">
				<table>
					<tr>
						<td>
							<div class="centerContent">
								<strong>Employee : *</strong>
									<form:select path="employee.id" required="required">
										<form:options items="${employees}" var="employee" itemValue="value" itemLabel="key"/>
									</form:select>
								<br /> <strong>Project Name : *</strong>
									<form:select path="project.id" required="required">
										<form:options items="${projects}" var="project" itemValue="value" itemLabel="key"/>
									</form:select>
								<br /> <strong>Percent : *</strong>
								<form:input path="percent" required="required" />
								<br /> <strong>Start Date : *</strong>
								<form:input path="start_date" type="date" required="required" placeholder="09-09-2014"/>
								<br /> End Date :
								<form:input path="end_date" type="date" placeholder="10-29-2015"/>
								<br />
								<hr />
								<input type="submit" value="Save" /><br />
								<br /> <strong><em>* = required fields</em></strong><br />
								<hr />
								<a href="viewAllocationList"><img
									alt="View Allocation Table" width="150" height="37.5"
									src="<c:url value="/resources/images/viewAlloc.png"/>"
									class="viewAlloclink" /></a>
							</div>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>