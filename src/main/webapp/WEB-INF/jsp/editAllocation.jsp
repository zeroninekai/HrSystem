<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Allocation</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/imageClasses.css"/>" />
</head>
<body>
	<%@include file="navlinks.jsp"%>
	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;">
			<h2>Edit Allocation Details</h2>
			<form:form method="post" action="updateAllocation"
				modelAttribute="allocation">
				<table>
					<tr>
						<td>
							<div class="centerContent">
								<form:hidden path="employee.id" value="${map.empID}"/>
								<c:out value="${map.allocation.employee_name}"/>

								<br /> <strong>Project Name :</strong>
								<form:select path="project.id" required="required">
										<form:options items="${projects}" var="project" itemValue="value" itemLabel="key"/>
								</form:select>
								
								<%-- <form:input path="project_id"
									value="${map.allocation.project_id}" required="required" /> --%>
								<br /> <strong>Percent :</strong>
								<form:input path="percent" value="${map.allocation.percent}"
									required="required" />
								<br /> <strong>Start Date :</strong>
								<form:input path="start_date"
									value="${map.allocation.start_date}" type="date"
									required="required" />
								<br /> End Date :
								<form:input path="end_date" value="${map.allocation.end_date}"
									type="date" />
								<br />
								<hr />
								<input type="submit" value="Save" />
							</div>
						</td>
					</tr>
				</table>
				<form:hidden path="id" value="${map.allocation.id}" />
			</form:form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>