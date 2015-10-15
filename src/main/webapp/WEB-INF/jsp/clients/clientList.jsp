<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
  <jsp:include page="../fragments/bodyHeader.jsp"/>
      <c:if test="${param.success eq true}">
     <div class="alert alert-success">new client has been added</div>
      </c:if>

  <h2>Clients</h2>

  <datatables:table id="clients" data="${clients.clientList}" row="client" theme="bootstrap2" cssClass="table table-striped" pageable="false" info="false">
      <datatables:column title="ID">
          <c:out value="${client.id} "></c:out>
      </datatables:column>
      <datatables:column title="Name" cssStyle="width: 150px;" display="html">
      <spring:url value="/clients/{clientId}.html" var="clientUrl">
          <spring:param name="clientId" value="${client.id}"/>
      </spring:url>
      <a href="${fn:escapeXml(clientUrl)}"><c:out value="${client.firstName} ${client.lastName}"/></a>
      </datatables:column>
      <datatables:column title="Address">
          <c:out value="${client.address}"></c:out>
      </datatables:column>
      <datatables:column title="Age">
          <c:out value="${client.age}"></c:out>
      </datatables:column>
      <datatables:column title="Total Money">
          <c:out value="$ ${client.totalMoney}"></c:out>
      </datatables:column>

   </datatables:table>

  <div class="container">


  <a href='<spring:url value="/clients/new" htmlEscape="true"/>'>Add Client</a>

  <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
