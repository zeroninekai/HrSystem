<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
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
<center>
<table class="sortable" id="allocPage" style="width: 65%;">
    <thead>
    <tr>
        <th>Project Name</th>
        <th>Headcount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="project" items="${map.projectList}">
        <tr>
            <td>${project.project_name}</td>
            <td>${project.plannedHeadCount}</td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
    <tr><th class="sorttable_nosort" colspan="9">  </th></tr>
    </tfoot>
</table>
</center>
    <%@include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
