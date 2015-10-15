<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Client Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${client.firstName} ${client.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Address</th>
            <td><c:out value="${client.address}"/></td>
        </tr>
        <tr>
            <th>Age</th>
            <td><c:out value="${client.age}"/></td>
        </tr>
        <tr>
            <th>Total money on all accounts</th>
            <td><c:out value="${client.totalMoney}"/></td>
        </tr>

         <tr>
            <td> 
            	<%--<spring:url value="{clientId}/edit.html" var="editUrl">
                    <spring:param name="clientId" value="${client.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Edit Client</a></td>--%>
            <td>
            	<spring:url value="{clientId}/accounts/new.html" var="addUrl">
                    <spring:param name="clientId" value="${client.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(addUrl)}"  class="btn btn-success">Add New Account</a></td>
        </tr>
    </table>

    <h2>Accounts</h2>

        <table class="table" style="width:600px;">
            <tr>
            <th>Account id</th>
            <th>Savings</th>
            </tr>
            <c:forEach var="account" items="${client.accounts}">
                <tr>
                    <td valign="top" style="width: 120px;">
                        <c:out value="${account.id}"/>
                    </td>
                    <td valign="top">
                        <c:out value="$ ${account.moneyAmount}"/>
                    </td>
                </tr>
        </c:forEach>
        </table>


    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
