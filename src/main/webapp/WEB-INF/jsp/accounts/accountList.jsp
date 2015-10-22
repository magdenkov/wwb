<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
  <jsp:include page="../fragments/bodyHeader.jsp"/>


  <c:if test="${param.success eq true}">

  <div class="alert alert-success">new client has been added</div>
  </c:if>

  <h2>Accounts</h2>

  <datatables:table id="accounts" data="${accounts}" row="account" theme="bootstrap2" cssClass="table table-striped" pageable="false" info="false">
  <datatables:column title="ID">
    <c:out value="${account.id}"></c:out>
  </datatables:column>
  <datatables:column title="Name">
      <c:out value="${account.client.firstName} ${account.client.lastName}"></c:out>
    </datatables:column>
    <datatables:column title="money ammount">
    <fmt:formatNumber type="currency"   value="${account.moneyAmount}" />

    </datatables:column>
  </datatables:table>

  <div class="container">


    <jsp:include page="../fragments/footer.jsp"/>
  </div>
</body>

</html>
