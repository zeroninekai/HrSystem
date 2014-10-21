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
<div id="title">Edit Allocation Details</div>
	<%@include file="navlinks.jsp"%>
<!-- 	<div style="width: 100%; text-align: center;">
		<div style="display: inline-block;"> -->
			<form:form method="post" action="updateAllocation"
				modelAttribute="allocation">
							<div class="optionElements">
								<strong>Employee Name :</strong><strong style="padding-left:100px;">Project Name :</strong><strong style="padding-left:160px;">Percent :</strong><br />
								<form:hidden path="employee.id" value="${map.allocation.employee.id}"/>
								<c:out value="${map.allocation.employee_name}"/>

                                <form:hidden path="project.id" value="${map.allocation.project.id}"/>
                                <span style="padding-left: 135px;"><c:out value="${map.allocation.project.project_name}"/></span>

									<span style="padding-left:100px;">
										<form:input path="percent" value="${map.allocation.percent}"
										required="required" />
									</span>
									<br />
								<br /> <strong>Start Date :</strong><strong style="padding-left:140px;">End Date :</strong><br />
								<form:input path="start_date"
									value="${map.allocation.start_date}" type="date"
									required="required" />

								<span style="padding-left:48px;">
									<form:input path="end_date" value="${map.allocation.end_date}"
										type="date" />
								</span>
								<br /><br />
								<input type="submit" value="Save Record" />
								<input type="reset" value="Reset Fields"/>
								<br />
								<a href="viewAllocationList"><img
									alt="View Allocation List" width="150" height="33"
									src="<c:url value="/resources/images/viewAlloc.png"/>"
									class="viewAlloclink" /></a>
							</div>
				<form:hidden path="id" value="${map.allocation.id}" />
			</form:form>
		<!-- </div>
	</div> -->
	<%@include file="footer.jsp"%>
</body>
</html>