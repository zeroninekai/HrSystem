<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<%--Set currency type to Philippine peso for formatting data--%>
<fmt:setLocale value="fil_PH" scope="session"/>
<head>
    <title>Home - Resource Management System</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" ></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.min.js"></script>

    <script src="<c:url value="/resources/scripts/jPaginate.js"/>"></script>
    <script src="<c:url value="/resources/scripts/sorttable.js"/>"></script>
    <link rel="stylesheet"
          href="<c:url value="/resources/css/dashboard.css"/>" />
    <link rel="stylesheet"
          href="<c:url value="/resources/css/imageClasses.css"/>" />
    <link rel="stylesheet"
          href="<c:url value="/resources/css/jPaginate-default.css"/>" />
</head>
<body>
<div id="title">Home - Resource Management System</div>
<%@include file="/WEB-INF/jsp/navlinks.jsp"%>
<br />
<div class="center">
    <h1>Welcome!</h1>
    <h2>
        You are currently on the Resource Management System Home page.
        <br />If you wish to get started, click on one of the tabs above.
    </h2>
</div>
<br />
<div class="pListboard">
    <img src="/viewChart" class="m" id="iChart"/>

    <table class="m" id="projectListCost" cellspacing="0">
    <thead>
    <tr>
        <th class="leftSide">Project Name</th>
        <th class="rightSideOfProjs">Total Cost</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${map.projectList}">
        <tr>
            <td class="leftSide">${project.project_name}</td>
            <td class="rightSideOfProjs"><fmt:formatNumber value="${project.dailyCost}" type="currency"/></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr><th class="sorttable_nosort" colspan="9">  </th></tr>
    </tfoot>
</table>

<table class="m" id="projectListAlloc" cellspacing="0">
    <thead>
    <tr>
        <th class="leftSide">Project Name</th>
        <th class="rightSideOfProjs">Allocations</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${map.projectListAlloc}">
        <tr>
            <td class="leftSide">${project.project_name}</td>
            <td class="rightSideOfProjs">${project.totalAllocation}</td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr><th class="sorttable_nosort" colspan="9">  </th></tr>
    </tfoot>
</table>
</div>

<div style="text-align: center;">
<table class="m" id="employeeExceededAllocs" cellspacing="0">
    <thead>
    <tr>
        <th class="leftSide">Employee</th>
        <th class="rightSideOfProjs">Allocation</th>
        <%--   <th>Controls</th>--%>
    </tr>
    </thead>
    <tbody>
    <c:if test="${empty map.employeeExceededList}">
        <tr><td colspan="3">No data available</td></tr>
    </c:if>
    <c:forEach var="employee" items="${map.employeeExceededList}">
        <tr>
            <td class="leftSide"><a href="searchAlloc?searchquery=${employee.fullName}">${employee.fullName}</a></td>
            <td class="rightSideOfProjs">${employee.totalPercentAllocated}</td>
                <%--IMAGE for search  <td><a href="searchAlloc?searchquery=${employee.fullName}">
                      <img
                          alt="Edit" width="85" height="29"
                          src="<c:url value="/resources/images/srchBut.png"/>"
                          class="srchlink" /></a>--%>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr><th class="sorttable_nosort" colspan="9">  </th></tr>
    </tfoot>
</table>
</div>

    <%@include file="/WEB-INF/jsp/footer.jsp"%>
<br/>
</body>
</html>
