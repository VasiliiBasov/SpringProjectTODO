<%@ page contentType="text/html;charset=windows-1251" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoList</title>
    <link href="../resources/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta id="root" about="${pageContext.request.contextPath}">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/jq.js">
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.3.1-dist/js/bootstrap.js">
    </script>
    <script type="text/javascript"><%@include file="../resources/scripts.js" %></script>
</head>

<body onload="loadContent();">
<h1 style="text-align: center; margin-top: 50px; margin-bottom: 30px">Список задач</h1>

<select style="float: right" onchange="loadContent()" id="limit"
        class="form-control-sm">
    <option>1</option>
    <option selected>3</option>
    <option>5</option>
    <option>10</option>
    <option>20</option>
</select>
<label style="float: right; margin-right: 10px" for="limit">Tasks in a page: </label>

<table id="excelDataTable" border="1" class="table">
</table>

<h5 id="count" style="float: right; margin-right: 20px"></h5>
<div>
    <ul id="pagging-bar" class="pagination pagination-sm justify-content-center">

    </ul>
</div>

</body>

<script src="<c:url value="/resources/jquery-3.6.0.min.js"/>"></script>
</html>
